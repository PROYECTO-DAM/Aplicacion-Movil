package com.example.proyectodam.Routing

sealed class Routes(val route:String) {
    object Login: Routes("login")
    object Splash:Routes("splash")
    object Register: Routes("registro")
    object Main: Routes("main")
    object Nominas: Routes("nominas")

    object Nomina: Routes("nomina/{id}") {
        fun createRoute(id:Int) = "nomina/$id"
    }
    object Fichaje: Routes("fichaje/{id}") {
        fun createRoute(id:Int) = "fichaje/$id"
    }
    object Perfil : Routes("perfil/{id}") {
        fun createRoute(id: Int) = "perfil/$id"
    }
}
