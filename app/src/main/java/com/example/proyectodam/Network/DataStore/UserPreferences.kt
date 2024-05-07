package com.example.proyectodam.Network.DataStore

import com.example.proyectodam.Classes.Usuario

interface UserPreferences {
    suspend fun addUser(key:String, value:String)
    suspend fun getUser(key:String):Usuario?
}