package app

import Conexao
import Repositorio
import com.github.britooo.looca.api.core.Looca

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            // chamando um objeto looca para captura
            var looca = Looca()

            // dependecias do banco
            Conexao.criarTabelas()
            var jdbcTemplate= Conexao.jdbcTemplate!!

            val repositorio = Repositorio()
            repositorio.iniciar()
            repositorio.validarUsuario()

        }
    }
}