package com.example.proyectodam.Network.MetodosAPI

import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Network.Request.LoginRequest
import com.example.proyectodam.Network.Response.LoginResponse
import com.example.proyectodam.Network.Response.UsuarioResponse
import retrofit2.Response
import retrofit2.http.*

interface UsuarioAPI {

    @GET("/api/v1/users/getUser")
    suspend fun getUserById(@Header("authorization") token:String, @Header("_id") id:Int):Response<UsuarioResponse>

    @POST("/api/v1/users/signIn")
    suspend fun login(@Body user:LoginRequest):Response<LoginResponse>

    @POST("/api/v1/users/signUp")
    suspend fun register(@Body user:Usuario):Response<LoginResponse>

    @PUT("/api/v1/users/updateUser")
    suspend fun updateUser(@Header("authorization") token:String, @Body user:Usuario):Response<UsuarioResponse>
}


