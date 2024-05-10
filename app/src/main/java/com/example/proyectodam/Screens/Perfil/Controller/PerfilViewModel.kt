package com.example.proyectodam.Screens.Perfil.Controller

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Perfil.Data.GetUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(private val getUsuarioUseCase: GetUsuarioUseCase):ViewModel() {

    suspend fun getUser(id:Int, context:Context):Usuario {
        return withContext(Dispatchers.IO) {
            getUsuarioUseCase.invoke(id, context)
        }
    }

}