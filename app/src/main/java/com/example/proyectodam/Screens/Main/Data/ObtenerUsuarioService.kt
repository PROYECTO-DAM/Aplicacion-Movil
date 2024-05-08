package com.example.proyectodam.Screens.Main.Data

import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Network.DataStore.UserPreferencesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ObtenerUsuarioService @Inject constructor(private val userPreference: UserPreferencesService) {
    suspend fun ObtenerUsuario(): Usuario {
        return withContext(Dispatchers.IO) {
            userPreference.getUser("usuario")!!;
        }
    }
}