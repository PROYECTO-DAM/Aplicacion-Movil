package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Network.Response.NominasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NominaAPI {

    @GET("/api/v1/nominas/getNominasByUserId")
    suspend fun obtenerNominasEmpleado(@Header("authorization") token:String, @Header("_id") id:Int):Response<NominasResponse>
}