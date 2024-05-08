package com.example.proyectodam.Screens.Main.Data

import com.example.proyectodam.Classes.Fichaje
import javax.inject.Inject

class FicharUseCase @Inject constructor(private val ficharService: FicharService) {
    suspend operator fun invoke(fichaje: Fichaje):Boolean {
        return ficharService.fichar(fichaje);
    }
}