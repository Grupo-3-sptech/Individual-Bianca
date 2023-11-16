import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import javax.swing.JOptionPane

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    var id = Looca().processador.id

    val idRobo = jdbcTemplate.queryForObject(
        """
                select idRobo from RoboCirurgiao where idProcess = '$id'
            """,
        Int::class.java,
    )


    fun iniciar(){

        jdbcTemplate= Conexao.jdbcTemplate!!
    }

    var looca = Looca()

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

    fun processos(){
        var grupoprocessos = looca.grupoDeProcessos
        var processos = grupoprocessos.processos

        //for (processo in processos){
            //println(processo)
        //}

        var totalProcessos = grupoprocessos.totalProcessos
        var totalThreads = grupoprocessos.totalThreads

    }

    fun temperatura(){
        var temperatura = looca.temperatura
        println(temperatura)

        while(true) {
            jdbcTemplate.execute(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    ('$idRobo', '${now()}', '$temperatura', 5);
                """.trimIndent()
            )

            Thread.sleep(7000)
        }
    }


    fun sistema(){
        val sistema = looca.sistema
        var tempDeAtividade = sistema.tempoDeAtividade

        while(true) {
            jdbcTemplate.execute(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    ('$idRobo', '${now()}', '$tempDeAtividade', 3);
                """.trimIndent()
            )

            Thread.sleep(7000)
        }
    }

    fun cpu(){
        var processador = looca.processador
        var frequencia = processador.frequencia
        var uso = processador.uso

        while(true) {
            jdbcTemplate.execute(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    ('$idRobo', '${now()}', '$frequencia', 2),
                    ('$idRobo', '${now()}', '$uso', 1);
                """.trimIndent()
            )

            Thread.sleep(7000)
        }
    }

    //cadastro de usuario


    fun validarUsuario() {

        var email: String = JOptionPane.showInputDialog("Insira seu email: ")
        var senha: String = JOptionPane.showInputDialog("Insira sua senha: ")


        var usu = jdbcTemplate.queryForObject(
            """
             select fkHospital from usuario
                where email = '$email' AND senha = '$senha'
            """,
            Int::class.java
        )


        var usuario = jdbcTemplate.queryForObject(
            """
                select fkHospital from usuario
                where email = '$email' AND senha = '$senha'
            """,
            Int::class.java
        )

        if(usuario != null){
            var validacao = JOptionPane.showInputDialog(null, "Login realizado com sucesso. Deseja iniciar a captura? Digite S para sim e qualquer coisa para não: ")



            if( validacao == "S"){
                cadastro(usu)
                cpu()
                temperatura()
                processos()
                sistema()
            }else{
                JOptionPane.showMessageDialog(null, "Saindo do programa.")
            }

        } else{
            JOptionPane.showMessageDialog(null, "Você não está registrado.")
        }

}



    private fun cadastro(fkHospital: Int) {

        if(verificarPrecedentes() == true) {
            jdbcTemplate.execute(
                """
                INSERT INTO RoboCirurgiao (modelo, fabricacao, fkStatus, idProcess, fkHospital) 
                VALUES ('Modelo A', '${looca.processador.fabricante}', 1, '$id', $fkHospital);
            """
            )
            println("Robô cadastrado.")
        } else{
            0
        }



        //solucao()
        //all()
    }



}