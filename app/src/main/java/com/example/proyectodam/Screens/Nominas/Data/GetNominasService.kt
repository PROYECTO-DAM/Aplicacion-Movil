package com.example.proyectodam.Screens.Nominas.Data

import android.content.Context
import com.example.proyectodam.Classes.Nomina
import com.example.proyectodam.Network.MetodosAPI.NominaAPI
import com.example.proyectodam.Network.Utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNominasService @Inject constructor(private val nominaAPI: NominaAPI) {

    suspend fun getNominas(id:Int, context: Context):List<Nomina> {
        return withContext(Dispatchers.IO) {

            val token = SessionManager(context).fetchAuthToken()

            val response = nominaAPI.obtenerNominasEmpleado("Bearer $token", id)

            if(response.isSuccessful) {
                response.body()?.data!!
            } else {
                listOf()
            }
        }
    }

}