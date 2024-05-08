package com.example.proyectodam.Screens.Main.Controller

import androidx.lifecycle.ViewModel
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Main.Data.ObtenerUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val obtenerUsuarioUseCase: ObtenerUsuarioUseCase): ViewModel() {

    suspend fun getLoggedUser():Usuario {
        return withContext(Dispatchers.IO) {
            obtenerUsuarioUseCase()
        }
    }

    private fun fichar() {
        //TODO FUNCIONALIDAD FICHAR
    }

}