import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import java.time.format.DateTimeFormatter
import javax.swing.JOptionPane

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    //iniciando a conexão sql
    fun iniciar(){

        jdbcTemplate = Conexao.jdbcTemplate!!

    }

    var looca = Looca()

    // id do processador
    var id = looca.processador.id

    fun idRobo(): Int {
        val roboId = jdbcTemplate.queryForObject(
            """
                SELECT idRobo FROM RoboCirurgiao WHERE idProcess = '$id'
            """,
            Int::class.java
        )
        println(id)
        return roboId ?: -1
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

    // capturando temperatura e mandando pro bd
    fun temperatura() {

        val dataHoraAtual = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val dataHoraFormatada = dataHoraAtual.format(formatter)

        println("passouuuu")
        while (true) {
            // Mova a declaração da variável temperatura para dentro do loop
            val temperatura = looca.temperatura.temperatura
            println(temperatura)

            val teste = jdbcTemplate.update(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    (${idRobo()}, '$dataHoraFormatada', '$temperatura', 22);
                """.trimIndent()
            )
            println(teste)  // Troquei print por println para garantir uma nova linha
            Thread.sleep(7000)
        }
    }

    // capturando dados de cpu e mandando pro bd
    fun cpu(){
        var processador = looca.processador
        var frequencia = processador.frequencia

        while(true) {
             jdbcTemplate.update(
                """
                    INSERT INTO registros (fkRoboRegistro, HorarioDado, dado, fkComponente)
                    VALUES 
                    (, '${now()}', '$frequencia', 2);
                    
                """.trimIndent()
            )

            Thread.sleep(7000)

        }
    }

    // login de usuario verificando no bd
    fun validarUsuario() {

        var email: String = JOptionPane.showInputDialog("🤖 [MedConnect]: Insira seu email: ")
        var senha: String = JOptionPane.showInputDialog("\uD83E\uDD16 [MedConnect]: Insira sua senha: ")


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
            var validacao = JOptionPane.showInputDialog(null, "\uD83E\uDD16 [MedConnect]: Login realizado com sucesso. Deseja iniciar a captura? Digite S para sim e qualquer coisa para não: ")


            if( validacao == "S" || validacao == "s"){

                cadastro(usu)
                temperatura()
                cpu()
                //processos()
            }else{
                JOptionPane.showMessageDialog(null, "\uD83E\uDD16 [MedConnect]: Saindo do programa.")
            }

        } else{
            JOptionPane.showMessageDialog(null, "\uD83E\uDD16 [MedConnect]: Você não está registrado.")
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
            println("\uD83E\uDD16 [MedConnect]: Robô cadastrado.")
        }

    }



}