import sqlite3
import os

db_name = "test_db_creation.db"
# CORREÇÃO AQUI: Adicionado 'r' antes da string do caminho
target_directory = r"D:\Projects\plataforma-eco-denuncia\api-email"
file_path = os.path.join(target_directory, db_name)

# Garante que o script está tentando criar o arquivo no diretório correto
if os.getcwd().lower() != target_directory.lower():
    print(f"AVISO: O diretório de trabalho atual ({os.getcwd()}) é diferente do diretório alvo ({target_directory}).")
    print("Tentando criar o arquivo explicitamente no diretório alvo.")
else:
    print(f"O diretório de trabalho atual é igual ao diretório alvo: {target_directory}")

# Remove qualquer arquivo de teste existente para garantir uma tentativa de criação limpa
if os.path.exists(file_path):
    try:
        os.remove(file_path)
        print(f"Arquivo '{db_name}' existente em '{target_directory}' removido para novo teste.")
    except Exception as e:
        print(f"ERRO ao remover arquivo existente '{db_name}': {e}. Certifique-se de que não está sendo usado por outro programa.")
        exit() # Sair se não conseguir remover o arquivo antigo

print(f"Tentando criar o arquivo '{db_name}' na pasta: {target_directory}")

try:
    # Conecta (cria) o arquivo de banco de dados se ele não existir
    conn = sqlite3.connect(file_path)
    cursor = conn.cursor()
    
    # Cria uma tabela simples para garantir que a estrutura do banco de dados seja gravada
    cursor.execute("CREATE TABLE IF NOT EXISTS test_table (id INTEGER PRIMARY KEY, name TEXT)")
    conn.commit() # Confirma as alterações para o disco
    conn.close() # Fecha a conexão para liberar o handle do arquivo
    
    if os.path.exists(file_path):
        print(f"SUCESSO: Arquivo '{db_name}' foi criado com êxito em '{target_directory}'!")
    else:
        print(f"FALHA: Arquivo '{db_name}' NÃO foi criado, mesmo após tentativa.")

except sqlite3.Error as e:
    print(f"ERRO SQLite durante a criação: {e}")
except Exception as e:
    print(f"ERRO INESPERADO: {e}")

print("Verificação final: o arquivo existe?", os.path.exists(file_path))