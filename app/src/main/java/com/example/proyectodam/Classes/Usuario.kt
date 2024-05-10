package com.example.proyectodam.Classes

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("id") val id:Int,
    @SerializedName("fullname") val fullname:String,
    @SerializedName("role") val role:String,
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)
