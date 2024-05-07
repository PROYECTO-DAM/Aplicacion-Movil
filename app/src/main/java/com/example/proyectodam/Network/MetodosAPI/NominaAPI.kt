package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Classes.Nomina
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface NominaAPI {

    @GET("/api/v1/nomina/month")
    suspend fun getNominaDelMes(@Header("authorization") token:String, @Header("mes") mes:String):Response<Nomina>

    @GET("/api/v1/nomina/empleado")
    suspend fun obtenerNominasEmpleado(@Header("authorization") token:String):List<Response<Nomina>>
}