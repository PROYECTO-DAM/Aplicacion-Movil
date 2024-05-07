package com.example.proyectodam.Routing

sealed class Routes(val route:String) {
    object Login: Routes("login")
    object Splash:Routes("splash")
    object Register: Routes("registro")
    object Main: Routes("main")
    object Nominas: Routes("nominas")
    object Nomina: Routes("nomina")
    object Fichaje: Routes("fichaje")
}
