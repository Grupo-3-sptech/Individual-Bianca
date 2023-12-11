import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
import java.lang.IllegalStateException


object Conexao {

    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null){
                val dataSoruceServer = BasicDataSource()
                dataSoruceServer.url = "jdbc:sqlserver://52.7.105.138:1433;databaseName=medconnect;encrypt=false";
                dataSoruceServer.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                dataSoruceServer.username = "sa"
                dataSoruceServer.password = "medconnect123"
                jdbcTemplate = JdbcTemplate(dataSoruceServer)
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
