package com.example.proyectodam.Screens.Login.Data

import android.content.Context
import com.example.proyectodam.Classes.Usuario
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginService: LoginService) {
    suspend operator fun invoke(email:String, password:String, context: Context) : Usuario {
        return loginService.doLogin(email, password, context)
    }
}