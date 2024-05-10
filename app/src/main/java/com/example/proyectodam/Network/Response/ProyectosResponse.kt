package com.example.proyectodam.Network.Response

import com.example.proyectodam.Classes.Proyecto
import com.google.gson.annotations.SerializedName

data class ProyectosResponse(
    @SerializedName("status") val status:Int,
    @SerializedName("data") val data: List<Proyecto>
)
