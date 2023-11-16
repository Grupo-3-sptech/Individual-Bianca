import com.github.britooo.looca.api.core.Looca
import javax.swing.JOptionPane

fun main() {

    // chamando um objeto looca para captura
    var looca = Looca()

    // dependecias do banco
    Conexao.criarTabelas()
    var jdbcTemplate= Conexao.jdbcTemplate!!
    val repositorio = Repositorio()
    repositorio.iniciar()
    repositorio.validarUsuario()



 
}