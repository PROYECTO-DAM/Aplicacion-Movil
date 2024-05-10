package com.example.proyectodam.Screens.Main.Data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Network.MetodosAPI.FichajeApi
import com.example.proyectodam.Network.Request.FichajeRequest
import com.example.proyectodam.Network.Utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FicharService @Inject constructor(private val fichajeApi: FichajeApi) {
    suspend fun fichar(fichaje:Fichaje, context: Context):Boolean {
        return withContext(Dispatchers.IO) {
            val formatString = formatDateToYYYYMMDD(fichaje.fecha)
            val fichajeRequest = FichajeRequest(fichaje.userId, formatString, fichaje.horas, fichaje.proyecto)
            val token = SessionManager(context).fetchAuthToken()
            val response = fichajeApi.fichar("Bearer $token", fichajeRequest)
            response.body()?.status == 201
        }

    }
}

@SuppressLint("SimpleDateFormat")
fun formatDateToYYYYMMDD(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = dateFormat.format(date)
    return formattedDate
}