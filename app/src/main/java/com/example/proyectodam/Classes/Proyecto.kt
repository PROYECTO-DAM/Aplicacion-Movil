package com.example.proyectodam.Classes

import com.google.gson.annotations.SerializedName

data class Proyecto(
    @SerializedName("Codigo") val codigo:Int,
    @SerializedName("Nombre") val nombre:String,
)
