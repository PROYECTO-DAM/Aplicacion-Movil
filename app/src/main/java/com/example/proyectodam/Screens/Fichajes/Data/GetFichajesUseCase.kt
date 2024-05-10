package com.example.proyectodam.Screens.Fichajes.Data

import android.content.Context
import com.example.proyectodam.Classes.Fichaje
import javax.inject.Inject

class GetFichajesUseCase @Inject constructor(private val getFichajesService: GetFichajesService) {
    suspend operator fun invoke(id:Int, context: Context):List<Fichaje> {
        return getFichajesService.getFichajes(id, context)
    }
}