package com.example.proyectodam.Screens.Main.Data

import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Network.MetodosAPI.FichajeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FicharService @Inject constructor(private val fichajeApi: FichajeApi) {
    suspend fun fichar(fichaje:Fichaje):Boolean {
        return withContext(Dispatchers.IO) {

            //TODO FUNCIONALIDAD FICHAR
            false;
        }
        return false;
    }
}