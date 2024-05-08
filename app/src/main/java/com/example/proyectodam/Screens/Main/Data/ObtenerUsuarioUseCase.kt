package com.example.proyectodam.Screens.Main.Data

import com.example.proyectodam.Classes.Usuario
import javax.inject.Inject

class ObtenerUsuarioUseCase @Inject constructor(private val obtenerUsuarioService: ObtenerUsuarioService) {
    suspend operator fun invoke(): Usuario {
        return obtenerUsuarioService.ObtenerUsuario();
    }
}
