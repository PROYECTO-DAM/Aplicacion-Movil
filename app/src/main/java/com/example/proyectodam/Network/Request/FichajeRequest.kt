package com.example.proyectodam.Network.Request

import com.google.gson.annotations.SerializedName
import java.util.*

data class FichajeRequest(
    @SerializedName("Trabajador") val userId: Int,
    @SerializedName("Fecha") val fecha: String,
    @SerializedName("Horas") val horas: Int,
    @SerializedName("Proyecto") val proyecto: Int,
)
