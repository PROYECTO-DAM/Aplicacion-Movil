package com.example.proyectodam.Network.Response

import com.example.proyectodam.Classes.Nomina
import com.google.gson.annotations.SerializedName

data class NominasResponse(
    @SerializedName("status") val status:Int,
    @SerializedName("data") val data: List<Nomina>,
)
