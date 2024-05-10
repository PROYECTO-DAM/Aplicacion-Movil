package com.example.proyectodam.Screens.Perfil.Data

import android.content.Context
import android.util.Log
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Network.MetodosAPI.UsuarioAPI
import com.example.proyectodam.Network.Utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsuarioService @Inject constructor(private val UsuarioAPI: UsuarioAPI) {
    suspend fun getUsuarioById(id:Int, context:Context): Usuario {
        return withContext(Dispatchers.IO) {
            val token = SessionManager(context).fetchAuthToken()

            val response = UsuarioAPI.getUserById("Bearer $token", id)

            if(response.isSuccessful) {
                response.body()?.data!!
            } else {
                Usuario(0,"","","","")
            }
        }
    }
}