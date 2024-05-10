package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Classes.Proyecto
import com.example.proyectodam.Network.Response.ProyectosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProyectoApi {

    @GET("/api/v1/proyecto/getAllProyectos")
    suspend fun getProyectos(@Header("authorization") token:String):Response<ProyectosResponse>

}