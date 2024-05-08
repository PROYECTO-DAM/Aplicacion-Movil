package com.example.proyectodam.Routing

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.proyectodam.Screens.Login.Controller.LoginViewModel
import com.example.proyectodam.Screens.Login.LoginScreen
import com.example.proyectodam.Screens.Main.Controller.MainViewModel
import com.example.proyectodam.Screens.Main.MainScreen
import com.example.proyectodam.Screens.Perfil.Controller.PerfilViewModel
import com.example.proyectodam.Screens.Perfil.PerfilScreen
import com.example.proyectodam.Screens.Register.Controller.RegisterViewModel
import com.example.proyectodam.Screens.Register.RegisterScreen


@Composable
fun CustomNavigator(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    mainViewModel: MainViewModel,
    perfilViewModel: PerfilViewModel
) {
    val navController = rememberNavController()

    NavHost(navController, Routes.Splash.route) {
        composable(route = Routes.Login.route) {
            LoginScreen(navController, loginViewModel)
        }
        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }
        composable(Routes.Register.route) {
            RegisterScreen(navController, registerViewModel)
        }
        composable(Routes.Main.route) {
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(route = Routes.Perfil.route, arguments = listOf(
            navArgument("id") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 1
            PerfilScreen(navController = navController, perfilViewModel = perfilViewModel, id = id)
        }
    }

}
