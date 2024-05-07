package com.example.proyectodam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.proyectodam.Routing.CustomNavigator
import com.example.proyectodam.Screens.Login.Controller.LoginViewModel
import com.example.proyectodam.Screens.Main.Controller.MainViewModel
import com.example.proyectodam.Screens.Register.Controller.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            CustomNavigator(loginViewModel, registerViewModel,mainViewModel)
        }
    }
}
