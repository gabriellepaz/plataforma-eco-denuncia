from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_mail import Mail, Message
import os
import hashlib
import secrets
import random
from dotenv import load_dotenv

# --- Carrega as variáveis de ambiente do arquivo .env ---
# Certifique-se de que o arquivo .env está na mesma pasta que este script.
load_dotenv()

# --- LINHAS DE DEBUG ADICIONADAS PARA VERIFICAR O .ENV ---
print(f"DEBUG: MAIL_SERVER lido: {os.environ.get('MAIL_SERVER')}")
print(f"DEBUG: MAIL_PORT lido: {os.environ.get('MAIL_PORT')}")
print(f"DEBUG: MAIL_USE_TLS lido: {os.environ.get('MAIL_USE_TLS')}")
print(f"DEBUG: MAIL_USE_SSL lido: {os.environ.get('MAIL_USE_SSL')}") # Adicionado para verificar MAIL_USE_SSL
print(f"DEBUG: MAIL_USERNAME lido: {os.environ.get('MAIL_USERNAME')}")
# Para a senha, vamos apenas verificar se está presente, não exibi-la por segurança
print(f"DEBUG: MAIL_PASSWORD presente: {'Sim' if os.environ.get('MAIL_PASSWORD') else 'Não'}")
# --- FIM DAS LINHAS DE DEBUG ---

app = Flask(__name__)

# --- Configuração do Banco de Dados ---
basedir = os.path.abspath(os.path.dirname(__file__))
app.config['SQLALCHEMY_DATABASE_URI'] = f"sqlite:///{os.path.join(basedir, 'verification_codes.db')}"
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# --- Configurações do Flask-Mail para GMAIL ---
# As configurações são carregadas das variáveis de ambiente definidas no arquivo .env
# Isso garante que suas credenciais de e-mail não fiquem expostas no código.
app.config['MAIL_SERVER'] = os.environ.get('MAIL_SERVER', 'smtp.gmail.com') # Default para Gmail
app.config['MAIL_PORT'] = int(os.environ.get('MAIL_PORT', 587)) # Default para 587
app.config['MAIL_USE_TLS'] = os.environ.get('MAIL_USE_TLS', 'True').lower() in ('true', '1', 't') # Gmail usa TLS
app.config['MAIL_USE_SSL'] = os.environ.get('MAIL_USE_SSL', 'False').lower() in ('true', '1', 't') # Gmail usa TLS, não SSL direto na porta 587
app.config['MAIL_USERNAME'] = os.environ.get('MAIL_USERNAME')
app.config['MAIL_PASSWORD'] = os.environ.get('MAIL_PASSWORD')

mail = Mail(app)

# --- Chave de Encriptação ---
SECRET_KEY_PATH = os.path.join(basedir, 'secret.key')

def load_or_generate_key(path):
    """Carrega uma chave de encriptação existente ou gera uma nova."""
    if os.path.exists(path):
        with open(path, 'rb') as f:
            return f.read()
    else:
        key = secrets.token_bytes(32) # Gera uma chave de 32 bytes (256 bits)
        with open(path, 'wb') as f:
            f.write(key)
        print(f"Chave de encriptação gerada e salva em: {path}")
        return key

ENCRYPTION_KEY = load_or_generate_key(SECRET_KEY_PATH)

# --- Modelagem do Banco de Dados ---
class VerificationCode(db.Model):
    """Modelo para armazenar códigos de verificação encriptados."""
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), unique=True, nullable=False)
    encrypted_code = db.Column(db.String(128), nullable=False) # Armazena o hash do código

    def __repr__(self):
        return f'<VerificationCode {self.email}>'

# --- Funções Auxiliares ---
def generate_random_code():
    """Gera um código de verificação aleatório de 6 dígitos."""
    return str(random.randint(100000, 999999))

def encrypt_data(data):
    """Encripta um dado usando SHA256 com uma chave secreta."""
    hashed_data = hashlib.sha256(ENCRYPTION_KEY + data.encode('utf-8')).hexdigest()
    return hashed_data

# --- Envio REAL de E-mail ---
def send_real_email(to_email, code):
    """Envia um e-mail com o código de verificação usando Flask-Mail."""
    # Verifica se as credenciais de e-mail foram configuradas via .env
    if not app.config['MAIL_USERNAME'] or not app.config['MAIL_PASSWORD']:
        print("!!! ERRO: Credenciais de e-mail (MAIL_USERNAME/MAIL_PASSWORD) não configuradas no .env. !!!")
        raise Exception("Credenciais de e-mail ausentes. Não é possível enviar e-mail.")

    msg = Message(
        subject="Seu Código de Verificação",
        sender=app.config['MAIL_USERNAME'], # O e-mail remetente configurado no .env
        recipients=[to_email]
    )
    msg.body = f"Olá,\n\nSeu código de verificação é: {code}\n\nPor favor, use este código para completar sua ação."
    msg.html = f"<html><body><p>Olá,</p><p>Seu código de verificação é: <strong>{code}</strong></p><p>Por favor, use este código para completar sua ação.</p></body></html>"

    try:
        mail.send(msg)
        print(f"\n--- EMAIL DE VERIFICAÇÃO ENVIADO REALMENTE ---")
        print(f"De: {app.config['MAIL_USERNAME']}")
        print(f"Para: {to_email}")
        print(f"Assunto: Seu Código de Verificação")
        print(f"Código: {code}")
        print(f"------------------------------------------\n")
        return True # Indica sucesso
    except Exception as e:
        print(f"\n!!! ERRO AO ENVIAR E-MAIL REAL PARA: {to_email} !!!")
        print(f"Detalhes do Erro: {e}\n") # Imprime o erro detalhado
        raise # Re-lança a exceção para que a rota possa tratá-la

# --- Rotas da API ---

@app.route('/send-verification-code', methods=['POST'])
def send_verification_code():
    """Rota para gerar e enviar um código de verificação por e-mail."""
    data = request.get_json()
    if not data or 'email' not in data:
        return jsonify({"message": "Email é obrigatório"}), 400

    user_email = data['email']
    code = generate_random_code()
    encrypted_code = encrypt_data(code)

    # --- INÍCIO DEBUG PARA SEND-VERIFICATION-CODE ---
    print(f"\n--- DEBUG SEND-VERIFICATION-CODE ---")
    print(f"Email para envio: {user_email}")
    print(f"Código gerado (para terminal): {code}")
    print(f"Código encriptado para armazenamento (HASH gerado): {encrypted_code}")
    # --- FIM DEBUG ---

    try:
        # CHAMA A FUNÇÃO DE ENVIO REAL DE E-MAIL
        send_real_email(user_email, code)

        existing_entry = VerificationCode.query.filter_by(email=user_email).first()
        if existing_entry:
            old_encrypted_code = existing_entry.encrypted_code
            existing_entry.encrypted_code = encrypted_code
            db.session.commit()
            print(f"Entry EXISTENTE para {user_email} ATUALIZADA no DB.")
            print(f"Hash ANTERIOR no DB: {old_encrypted_code}")
            print(f"Hash NOVO NO DB (após commit): {existing_entry.encrypted_code}\n")
        else:
            new_entry = VerificationCode(email=user_email, encrypted_code=encrypted_code)
            db.session.add(new_entry)
            db.session.commit()
            print(f"NOVA entry para {user_email} ADICIONADA no DB.")
            print(f"Hash ADICIONADO NO DB (após commit): {new_entry.encrypted_code}\n")

        return jsonify({"message": "Código de verificação enviado para seu email."}), 200

    except Exception as e:
        print(f"Erro geral na rota /send-verification-code para {user_email}: {e}\n")
        return jsonify({"message": "Falha ao enviar o código de verificação. Tente novamente mais tarde."}), 500


@app.route('/verify-code', methods=['POST'])
def verify_code():
    """Rota para verificar o código de verificação enviado pelo usuário."""
    data = request.get_json()
    if not data or 'email' not in data or 'code' not in data:
        return jsonify({"message": "Email e código são obrigatórios"}), 400

    user_email = data['email']
    user_code = data['code']

    # --- INÍCIO DEBUG PARA VERIFY-CODE ---
    print(f"\n--- DEBUG VERIFY-CODE ---")
    print(f"Email recebido: {user_email}")
    print(f"Código do usuário recebido: {user_code}")
    # --- FIM DEBUG ---

    encrypted_user_code = encrypt_data(user_code)

    # --- CONTINUAÇÃO DEBUG PARA VERIFY-CODE ---
    print(f"Código do usuário encriptado (do Postman): {encrypted_user_code}")
    # --- FIM DEBUG ---

    verification_entry = VerificationCode.query.filter_by(email=user_email).first()

    if verification_entry:
        # --- CONTINUAÇÃO DEBUG PARA VERIFY-CODE ---
        print(f"Entrada no DB encontrada para {user_email}.")
        print(f"Código encriptado no DB: {verification_entry.encrypted_code}")
        # --- FIM DEBUG ---
        if verification_entry.encrypted_code == encrypted_user_code:
            # Código verificado com sucesso, você pode querer deletar o código aqui para uso único
            db.session.delete(verification_entry)
            db.session.commit()
            # --- CONTINUAÇÃO DEBUG PARA VERIFY-CODE ---
            print(f"Resultado da comparação: SUCESSO - Códigos encriptados correspondem e entry deletada do DB.\n")
            # --- FIM DEBUG ---
            return jsonify({"message": "Código verificado com sucesso."}), 200
        else:
            # --- CONTINUAÇÃO DEBUG PARA VERIFY-CODE ---
            print(f"Resultado da comparação: FALHA - Códigos encriptados NÃO correspondem.\n")
            # --- FIM DEBUG ---
            return jsonify({"message": "Email ou código inválido."}), 400
    else:
        # --- CONTINUAÇÃO DEBUG PARA VERIFY-CODE ---
        print(f"Resultado da busca no DB: FALHA - Nenhuma entrada encontrada no DB para {user_email}.\n")
        # --- FIM DEBUG ---
        return jsonify({"message": "Email ou código inválido."}), 400


# --- Execução da Aplicação ---
if __name__ == '__main__':
    # NÃO coloque db.create_all() aqui. Ele deve ser chamado APENAS pelo create_db.py para evitar recriar tabelas
    app.run(debug=True)