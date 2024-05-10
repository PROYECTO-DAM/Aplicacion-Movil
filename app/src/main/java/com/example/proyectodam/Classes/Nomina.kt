package com.example.proyectodam.Classes

import com.google.gson.annotations.SerializedName

data class Nomina(
    @SerializedName("Id") val id:Int,
    @SerializedName("Pago") val pago:Float,
    @SerializedName("Mes") val mes:String,
    @SerializedName("Año") val año:Int,
    @SerializedName("Horas") val horas:Int,
    @SerializedName("Empleado") val empleado:Int
)
