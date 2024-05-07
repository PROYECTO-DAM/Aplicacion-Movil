package com.example.proyectodam.Network.Response

import com.example.proyectodam.Classes.Usuario
import com.google.gson.annotations.SerializedName

data class UsuarioResponse(
    @SerializedName("status") val status:Int,
    @SerializedName("data") val data:Usuario,
)
