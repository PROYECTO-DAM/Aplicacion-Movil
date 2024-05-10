package com.example.proyectodam.Screens.Fichajes.Data

import android.content.Context
import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Network.MetodosAPI.FichajeApi
import com.example.proyectodam.Network.Utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFichajesService @Inject constructor(private val fichajeApi: FichajeApi) {

    suspend fun getFichajes(id:Int, context:Context):List<Fichaje> {
        return withContext(Dispatchers.IO) {
            val token = SessionManager(context).fetchAuthToken()

            val response = fichajeApi.obtenerFichajesporId("Bearer $token", id)

            println("Response: " + response.body())

            if(response.isSuccessful) {
                response.body()?.data!!
            } else {
                listOf()
            }
        }
    }
}