from teste import app, db, VerificationCode
import os

# Verificar se o arquivo do banco de dados já existe e remover se sim (para testes)
if os.path.exists('verification_codes.db'):
    os.remove('verification_codes.db')
    print("Arquivo 'verification_codes.db' existente foi removido.")
else:
    print("Arquivo 'verification_codes.db' não existia antes da criação.")

with app.app_context():
    print("Iniciando criação de todas as tabelas...")
    db.create_all()
    print("Criação de tabelas concluída!")
    if os.path.exists('verification_codes.db'):
        print("--- SUCESSO: Arquivo 'verification_codes.db' agora existe na pasta. ---")
    else:
        print("--- ATENÇÃO CRÍTICA: Arquivo 'verification_codes.db' NÃO FOI CRIADO APESAR DE db.create_all() TER SIDO CHAMADO. ---")
        print("Verifique permissões de escrita para a pasta:", os.getcwd()) # Imprime o diretório atual