package com.example.proyectodam.Screens.Register.Controller

import android.content.Context
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Network.DataStore.UserPreferencesService
import com.example.proyectodam.Network.MetodosAPI.UsuarioAPI
import com.example.proyectodam.Network.Request.LoginRequest
import com.example.proyectodam.Network.Utils.SessionManager
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class RegisterService @Inject constructor(private val UsuarioAPI: UsuarioAPI, private val userPreference: UserPreferencesService)  {
    suspend fun register(usuarioRequest: Usuario, context: Context):Usuario {
        return withContext(Dispatchers.IO) {
            val response = UsuarioAPI.register(usuarioRequest)
            val token = response.body()?.data!!

            var user = Usuario(0,"Error al hacer registro","","","")

            SessionManager(context).saveAuthToken(token)

            if (token.isNotEmpty() && token.isNotBlank()) {
                try {
                    val usuario = UsuarioAPI.getUserById("Bearer $token")

                    val usuarioResponse = usuario.body()?.data!!

                    if(usuario.isSuccessful) {
                        user = Usuario(
                            id = usuarioResponse.id,
                            fullname = usuarioResponse.fullname,
                            role = usuarioResponse.role,
                            email = usuarioResponse.email,
                            password = usuarioResponse.password
                        )
                        Gson().toJson(user).let { userPreference.addUser("usuario", it) }
                    }

                } catch (e: IOException) {
                    throw e
                }
            }
            user
        }
    }
}