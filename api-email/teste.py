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



if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)