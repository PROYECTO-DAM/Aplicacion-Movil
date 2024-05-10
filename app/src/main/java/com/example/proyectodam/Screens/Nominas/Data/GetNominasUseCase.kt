package com.example.proyectodam.Screens.Nominas.Data

import android.content.Context
import com.example.proyectodam.Classes.Nomina
import javax.inject.Inject

class GetNominasUseCase @Inject constructor(private val getNominasService: GetNominasService) {
    suspend operator fun invoke(id:Int, context: Context):List<Nomina> {
        return getNominasService.getNominas(id, context)
    }
}