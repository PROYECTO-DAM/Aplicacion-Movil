package com.example.proyectodam.Screens.Login.Data

import android.content.Context
import android.util.Log
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

class LoginService @Inject constructor(private val UsuarioAPI: UsuarioAPI, private val userPreference: UserPreferencesService) {

    suspend fun doLogin(email:String, password:String, context: Context):Usuario {
        return withContext(Dispatchers.IO) {
            val data = LoginRequest(email, password)
            val response = UsuarioAPI.login(data)

            var user = Usuario(0,"Error al hacer login","","","")

            val token = response.body()?.data!!

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
                } catch (e:IOException) {
                    throw e
                }
            }
            user
        }
    }
}