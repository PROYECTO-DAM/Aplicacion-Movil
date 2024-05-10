package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Network.Request.FichajeRequest
import com.example.proyectodam.Network.Response.FichajeResponse
import com.example.proyectodam.Network.Response.FichajesResponse
import retrofit2.Response
import retrofit2.http.*

interface FichajeApi {

    @GET("/api/v1/fichajes/getFichajesByUserId")
    suspend fun obtenerFichajesporId(@Header("authorization") token:String, @Header("_id") id:Int):Response<FichajesResponse>

    @POST("/api/v1/fichajes/fichar")
    suspend fun fichar(@Header("authorization") token:String,@Body fichaje: FichajeRequest):Response<FichajeResponse>

    @PUT("/api/v1/fichajes/updateFichaje")
    suspend fun actualizarFichaje(@Header("authorization") token:String,@Body fichaje: Fichaje):Response<Fichaje>

    @DELETE("/api/v1/fichajes/deleteFichaje")
    suspend fun borrarFichaje(@Header("authorization") token:String,@Body id:String)
}