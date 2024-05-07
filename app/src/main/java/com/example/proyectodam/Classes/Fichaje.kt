package com.example.proyectodam.Classes

import java.util.Date

data class Fichaje(
    val id: Int,
    val userId: Int,
    val fecha: Date,
    val horas: Int,
    val proyecto: Int,
)
