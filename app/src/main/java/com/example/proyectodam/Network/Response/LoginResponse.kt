package com.example.proyectodam.Network.Response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status:Int,
    @SerializedName("data") val data:String,
)
