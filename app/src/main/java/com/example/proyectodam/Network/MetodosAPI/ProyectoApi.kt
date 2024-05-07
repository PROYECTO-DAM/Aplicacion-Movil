package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Classes.Proyecto
import retrofit2.Response
import retrofit2.http.GET

interface ProyectoApi {

    @GET("/api/v1/proyecto/getProyectos")
    suspend fun getProyectos():Response<List<Proyecto>>

}