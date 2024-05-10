package com.example.proyectodam.Screens.Perfil.Data

import android.content.Context
import com.example.proyectodam.Classes.Usuario
import javax.inject.Inject

class GetUsuarioUseCase @Inject constructor(private val getUsuarioService: GetUsuarioService) {
    suspend operator fun invoke(id:Int, context:Context): Usuario {
        return getUsuarioService.getUsuarioById(id, context)
    }
}