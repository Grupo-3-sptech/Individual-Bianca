import java.io.File

class APIpython {

    lateinit var arquivoPy: Process

    fun chamarAPI(roboId: Int){
        val codigo = """
            from mysql.connector import connect
import psutil
import platform
import time
import mysql.connector
from datetime import datetime
import ping3
import json
import requests

#alerta = {"text": "alerta"}

webhook = "https://hooks.slack.com/services/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"
#requests.post(webhook, data=json.dumps(alerta))


# idRobo = 1

#descomente abaixo quando for ora criar esse arquivo peo kotlin
 idRobo = ${roboId}

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

cursor = connection.cursor()


while True:

    processos = psutil.process_iter()
    
    numero_de_processos = len(list(processos))

    print(f"Número total de processos: {numero_de_processos}")

    horarioAtual = datetime.now()

    horarioFormatado = horarioAtual.strftime('%Y-%m-%d %H:%M:%S')
    

    query2 = ""${'"'}
            INSERT INTO registros (fkRoboRegistro,  HorarioDado, dado, fkComponente)
            VALUES (%s, %s, %s, %s) 
        ""${'"'}

    values = (${roboId}, horarioFormatado, numero_de_processos, 6)

    cursor.execute(query2, values)
    connection.commit()


    for proc in psutil.process_iter(['pid', 'name', 'status', 'create_time']):
            pid = proc.info['pid']
            nome = proc.info['name']
            processo_status = proc.info['status']
            momento_inicio = datetime.fromtimestamp(proc.info['create_time'])
        

    query = ""${'"'}
            INSERT INTO Processos (pid, nome, processo_status, momento_inicio, data_hora_captura, fkRobo)
            VALUES (%s, %s, %s, %s, %s, %s)
        ""${'"'}
    values = (pid, nome, processo_status, momento_inicio, horarioFormatado, ${roboId})

    cursor.execute(query, values)
    connection.commit()

    time.sleep(5)
    


cursor.close()
connection.close()
    

        """

        val nomeArquivo = "script.py"

        File(nomeArquivo).writeText(codigo)
        arquivoPy = Runtime.getRuntime().exec("py $nomeArquivo")
    }

    fun encerrarPy(){
        arquivoPy.destroy()
    }
}
