import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
import java.lang.IllegalStateException

object Conexao {

    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null){
                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                dataSource.url= "jdbc:mysql://localhost:3306/medconnect"
                dataSource.username = "admin"
                dataSource.password = "admin"
                val novoJdbcTemplate = JdbcTemplate(dataSource)
                field = novoJdbcTemplate
            }
            return  field
        }

    fun criarTabelas(){
        val jdbcTemplate = Conexao.jdbcTemplate ?: throw IllegalStateException("O JDBC não foi inicializado corretamente.")
        jdbcTemplate!!.execute("""

            USE medconnect;

            """)
    }

}