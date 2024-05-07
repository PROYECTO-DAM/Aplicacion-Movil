package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Classes.Fichaje
import retrofit2.Response
import retrofit2.http.*

interface FichajeApi {

    @GET("/api/v1/fichaje/getFichajesById")
    suspend fun obtenerFichajesporId(@Header("authorization") token:String): List<Response<Fichaje>>

    @GET("/api/v1/fichaje/getFichaje")
    suspend fun obtenerFichaje(@Header("authorization") token:String, @Header("id") idFichaje:String): Response<Fichaje>

    @POST("/api/v1/fichaje/fichar")
    suspend fun fichar(@Header("authorization") token:String,@Body fichaje: Fichaje):Response<Fichaje>

    @PUT("/api/v1/fichaje/updateFichaje")
    suspend fun actualizarFichaje(@Header("authorization") token:String,@Body fichaje: Fichaje):Response<Fichaje>

    @DELETE("/api/v1/fichaje/deleteFichaje")
    suspend fun borrarFichaje(@Header("authorization") token:String,@Body id:String)
}