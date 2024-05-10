package com.example.proyectodam.Routing

sealed class Routes(val route:String) {
    object Login: Routes("login")
    object Splash:Routes("splash")
    object Register: Routes("registro")
    object Main: Routes("main")
    object Nominas: Routes("nominas/{id}") {
        fun createRoute(id:Int) = "nominas/$id"
    }
    object Fichajes: Routes("fichajes/{id}") {
        fun createRoute(id:Int) = "fichajes/$id"
    }
    object Perfil : Routes("perfil/{id}") {
        fun createRoute(id: Int) = "perfil/$id"
    }
}
