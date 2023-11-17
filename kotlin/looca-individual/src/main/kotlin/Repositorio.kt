import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime.*
import javax.swing.JOptionPane

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    //iniciando a conexão sql
    fun iniciar(){

        jdbcTemplate = Conexao.jdbcTemplate!!

    }

    var looca = Looca()

    // id do processador
    var id = Looca().processador.id

    fun idRobo(): Int{

       return  jdbcTemplate.queryForObject(
            """
                select idRobo from RoboCirurgiao where idProcess = '$id'
            """,
            Int::class.java,
        )
    }




    // verificando se a máquina já foi cadastrada/registrada no bd
    fun verificarPrecedentes(): Boolean {

        val existeID = jdbcTemplate.queryForObject(
            """
                select count(*) as count from RoboCirurgiao where idProcess = '$id'
             """,
            Int::class.java,
        )

        if (existeID == 0) {
            return true
        } else {
            return false
        }


    }

    // capturando processos (não enviar pro banco)
    fun processos(){
        var grupoprocessos = looca.grupoDeProcessos
        var processos = grupoprocessos.processos

        //for (processo in processos){
        //println(processo)
        //}

        var totalProcessos = grupoprocessos.totalProcessos
        var totalThreads = grupoprocessos.totalThreads

    }


    // capturando temperatura e mandando pro bd
    fun temperatura(){
        var temperatura = looca.temperatura

        println(temperatura)

        while(true) {
            jdbcTemplate.execute(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    ('${idRobo()}', '${now()}', '$temperatura', 5);
                """.trimIndent()
            )

            Thread.sleep(7000)
        }
    }

    // capturando boot-time e mandando pro bd
    fun sistema(){
        val sistema = looca.sistema
        var tempDeAtividade = sistema.tempoDeAtividade

        while(true) {
            jdbcTemplate.execute(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    ('${idRobo()}', '${now()}', '$tempDeAtividade', 3);
                """.trimIndent()
            )

            Thread.sleep(7000)
        }
    }

    // capturando dados de cpu e mandando pro bd
    fun cpu(){
        var processador = looca.processador
        var frequencia = processador.frequencia
        var uso = processador.uso

        while(true) {
            jdbcTemplate.execute(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    ('${idRobo()}', '${now()}', '$frequencia', 2),
                    ('${idRobo()}', '${now()}', '$uso', 1);
                """.trimIndent()
            )

            Thread.sleep(7000)
        }
    }

    // login de usuario verificando no bd
    fun validarUsuario() {

        var email: String = JOptionPane.showInputDialog(" [MedConnect]: Insira seu email: ")
        var senha: String = JOptionPane.showInputDialog(" [MedConnect]: Insira sua senha: ")


        // puxando a fkHospiital
        var usu = jdbcTemplate.queryForObject(
            """
             select fkHospital from usuario
                where email = '$email' AND senha = '$senha'
            """,
            Int::class.java
        )

        // verificando se o user existe
        var usuario = jdbcTemplate.queryForObject(
            """
                select fkHospital from usuario
                where email = '$email' AND senha = '$senha'
            """,
            Int::class.java
        )

        // pergunta de deseja iniciar a captura
        if(usuario != null){
            var validacao = JOptionPane.showInputDialog(null, " [MedConnect]: Login realizado com sucesso. Deseja iniciar a captura? Digite S para sim e qualquer coisa para não: ")


            if( validacao == "S" || validacao == "s"){
                cadastro(usu)
                cpu()
                temperatura()
                processos()
                sistema()
            }else{
                JOptionPane.showMessageDialog(null, " [MedConnect]: Saindo do programa.")
            }

        } else{
            JOptionPane.showMessageDialog(null, " [MedConnect]: Você não está registrado.")
        }

    }

    // caso a máquina não esteja cadastrada, faz o cadastro dela através do idprocessador
    private fun cadastro(fkHospital: Int) {

        if(verificarPrecedentes() == true) {
            jdbcTemplate.execute(
                """
                INSERT INTO RoboCirurgiao (modelo, fabricacao, fkStatus, idProcess, fkHospital) 
                VALUES ('Modelo A', '${looca.processador.fabricante}', 1, '$id', $fkHospital);
            """
            )
            println(" [MedConnect]: Robô cadastrado.")
        } else{
            0
        }

    }

}