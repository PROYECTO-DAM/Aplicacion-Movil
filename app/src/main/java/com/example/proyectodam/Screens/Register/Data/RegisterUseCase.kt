package com.example.proyectodam.Screens.Register.Data

import android.content.Context
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Login.Data.LoginService
import com.example.proyectodam.Screens.Register.Controller.RegisterService
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerService: RegisterService) {
    suspend operator fun invoke(usuario:Usuario, context:Context) : Usuario {
        return registerService.register(usuario, context)
    }
}