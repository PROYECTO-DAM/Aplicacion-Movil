package com.example.proyectodam.Screens.Main.Data

import android.content.Context
import com.example.proyectodam.Classes.Proyecto
import com.example.proyectodam.Network.MetodosAPI.ProyectoApi
import com.example.proyectodam.Network.Utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ObtenerProyectoService @Inject constructor(private val proyectoApi: ProyectoApi) {
    suspend fun obtenerProyectos(context:Context):List<Proyecto> {
        return withContext(Dispatchers.IO) {

            val token = SessionManager(context).fetchAuthToken()

            val response = proyectoApi.getProyectos("Bearer $token")

            if(response.isSuccessful) {
                response.body()?.data!!
            } else {
                listOf()
            }
        }
    }
}