from mysql.connector import connect
import psutil
import platform
import time
import mysql.connector
from datetime import datetime
import ping3
import json
import requests
import pymssql

#alerta = {"text": "alerta"}

webhook = "https://hooks.slack.com/services/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"
#requests.post(webhook, data=json.dumps(alerta))


idRobo = 3

#descomente abaixo quando for ora criar esse arquivo peo kotlin
# idRobo = #${roboId}

def mysql_connection(host, user, passwd, database=None):
    connection = connect(
        host=host,
        user=user,
        passwd=passwd,
        database=database
    )
    return connection

def bytes_para_gb(bytes_value):
    return bytes_value / (1024 ** 3)

def milissegundos_para_segundos(ms_value):
    return ms_value / 1000

connection = mysql_connection('localhost', 'admin', 'admin', 'medconnect')

sqlserver_connection = pymssql.connect(server='52.7.105.138', database='medconnect', user='sa', password='medconnect123');

cursor = connection.cursor()

server_cursor = sqlserver_connection.cursor()


while True:

    processos = psutil.process_iter()
    
    numero_de_processos = len(list(processos))

    print(f"Número total de processos: {numero_de_processos}")

    horarioAtual = datetime.now()

    horarioFormatado = horarioAtual.strftime('%Y-%m-%d %H:%M:%S')
    

    query2 = """
            INSERT INTO registros (fkRoboRegistro,  HorarioDado, dado, fkComponente)
            VALUES (%s, %s, %s, %s)
        """

    values = (idRobo, horarioFormatado, numero_de_processos, 6)

    cursor.execute(query2, values)
    server_cursor.execute(query2, (idRobo, horarioFormatado, numero_de_processos, 19))
    
    connection.commit()
    sqlserver_connection.commit()


    for proc in psutil.process_iter(['pid', 'name', 'status', 'create_time']):
            pid = proc.info['pid']
            nome = proc.info['name']
            processo_status = proc.info['status']
            momento_inicio = datetime.fromtimestamp(proc.info['create_time'])
        

            query = """
                    INSERT INTO Processos (pid, nome, processo_status, momento_inicio, data_hora_captura, fkRobo)
                    VALUES (%s, %s, %s, %s, %s, %s)
                """
            values = (pid, nome, processo_status, momento_inicio, horarioFormatado, idRobo)

            cursor.execute(query, values)
            server_cursor.execute(query, values)
    
            connection.commit()
            sqlserver_connection.commit()

    porcentagem_cpu = psutil.cpu_percent(interval=None, percpu=False)

    query3 = """
            INSERT INTO registros (fkRoboRegistro,  HorarioDado, dado, fkComponente)
            VALUES (%s, %s, %s, %s)
        """

    values = (idRobo, horarioFormatado, porcentagem_cpu, 1)

    cursor.execute(query3, values)
    server_cursor.execute(query3, values)
    
    connection.commit()
    sqlserver_connection.commit()

    freq = psutil.cpu_freq()

    frequencia_atual = freq.current

    print(f'A frequência atual da CPU é: {frequencia_atual} MHz')

    query4 = """
            INSERT INTO registros (fkRoboRegistro,  HorarioDado, dado, fkComponente)
            VALUES (%s, %s, %s, %s)
        """

    values = (idRobo, horarioFormatado, frequencia_atual, 2)

    cursor.execute(query4, values)
    server_cursor.execute(query4, values)
    
    connection.commit()
    sqlserver_connection.commit()
    
    time.sleep(5)
    


cursor.close()
server_cursor.close()
connection.close()
sqlserver_connection.close()
    
