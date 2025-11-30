from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
import secrets
import bcrypt
import datetime
import os

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///otp.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

class OTP(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), unique=True, nullable=False)
    hashed_code = db.Column(db.String(128), nullable=False)
    created_at = db.Column(db.DateTime, default=datetime.datetime.now)

    def __repr__(self):
        return f'<OTP {self.email}>'


def generate_otp_code(length=6):
    return ''.join(secrets.choice('0123456789') for _ in range(length))

def hash_code(plain_code):
    hashed = bcrypt.hashpw(plain_code.encode('utf-8'), bcrypt.gensalt())
    return hashed.decode('utf-8')

def verify_hashed_code(plain_code, hashed_code):
    return bcrypt.checkpw(plain_code.encode('utf-8'), hashed_code.encode('utf-8'))



@app.route('/request_code', methods=['POST'])
def request_code():
    data = request.get_json()
    email = data.get('email')

    if not email:
        return jsonify({"message": "E-mail é obrigatório."}), 400

    otp_code = generate_otp_code()
    hashed_otp = hash_code(otp_code)

    otp_entry = OTP.query.filter_by(email=email).first()
    if otp_entry:
        otp_entry.hashed_code = hashed_otp
        otp_entry.created_at = datetime.datetime.now()
    else:
        otp_entry = OTP(email=email, hashed_code=hashed_otp)
        db.session.add(otp_entry)
    
    db.session.commit()

    print(f"DEBUG: Enviando código OTP {otp_code} para {email}")

    return jsonify({"message": "Código OTP enviado com sucesso para o e-mail."}), 200



@app.route('/verify_code', methods=['POST'])
def verify_code():
    data = request.get_json()
    email = data.get('email')
    user_provided_code = data.get('code')

    if not email or not user_provided_code:
        return jsonify({"message": "E-mail e código são obrigatórios."}), 400

    otp_entry = OTP.query.filter_by(email=email).first()

    if not otp_entry:
        return jsonify({"message": "E-mail não encontrado ou código OTP não solicitado."}), 404

    expiration_minutes = 5
    if (datetime.datetime.now() - otp_entry.created_at).total_seconds() > (expiration_minutes * 60):
        db.session.delete(otp_entry)
        db.session.commit()
        return jsonify({"message": "Código OTP expirado. Por favor, solicite um novo."}), 400

    if verify_hashed_code(user_provided_code, otp_entry.hashed_code):
        db.session.delete(otp_entry)
        db.session.commit()
        return jsonify({"message": "Autenticação bem-sucedida!", "authenticated": True}), 200
    else:
        return jsonify({"message": "Código OTP inválido.", "authenticated": False}), 401




@app.route('/get_encrypted_code/<string:email>', methods=['GET')
def get_encrypted_code(email):
    if not email:
        return jsonify({"message": "E-mail é obrigatório."}), 400

    otp_entry = OTP.query.filter_by(email=email).first()

    if otp_entry:
        return jsonify({
            "email": otp_entry.email,
            "hashed_code": otp_entry.hashed_code,
            "created_at": otp_entry.created_at.isoformat()
        }), 200
    else:
        return jsonify({"message": "Nenhum código OTP encontrado para este e-mail."}), 404



if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)