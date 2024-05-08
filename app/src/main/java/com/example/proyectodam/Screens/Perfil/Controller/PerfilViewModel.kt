package com.example.proyectodam.Screens.Perfil.Controller

import androidx.lifecycle.ViewModel
import com.example.proyectodam.Classes.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor():ViewModel() {

    suspend fun getUser(id:Int):Usuario {
        return withContext(Dispatchers.IO) {
            Usuario(0,"","","","")
        }
    }

}