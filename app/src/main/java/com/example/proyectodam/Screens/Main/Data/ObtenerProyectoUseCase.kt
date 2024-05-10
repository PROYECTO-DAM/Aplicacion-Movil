package com.example.proyectodam.Screens.Main.Data

import android.content.Context
import com.example.proyectodam.Classes.Proyecto
import javax.inject.Inject

class ObtenerProyectoUseCase @Inject constructor(private val obtenerProyectoService: ObtenerProyectoService) {
    suspend operator fun invoke(context: Context):List<Proyecto> {
        return obtenerProyectoService.obtenerProyectos(context)
    }
}