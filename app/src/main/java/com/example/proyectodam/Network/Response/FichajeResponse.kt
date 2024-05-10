package com.example.proyectodam.Network.Response

import com.example.proyectodam.Classes.Fichaje
import com.google.gson.annotations.SerializedName

data class FichajeResponse(
    @SerializedName("status") val status:Int,
    @SerializedName("data") val data:Fichaje,
)
