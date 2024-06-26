package com.example.proyectodam.Classes

import com.google.gson.annotations.SerializedName
import java.util.*

data class Fichaje(
    @SerializedName("ID") val id: Int,
    @SerializedName("Trabajador") val userId: Int,
    @SerializedName("Fecha") val fecha: Date,
    @SerializedName("Horas") val horas: Int,
    @SerializedName("Proyecto") val proyecto: Int,
)
