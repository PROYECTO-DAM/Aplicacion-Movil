package com.example.proyectodam.Screens.Fichajes.Controller

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Classes.Proyecto
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Fichajes.Data.GetFichajesUseCase
import com.example.proyectodam.Screens.Main.Data.ObtenerProyectoUseCase
import com.example.proyectodam.Screens.Perfil.Data.GetUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FichajesViewModel @Inject constructor(
    private val getUsuarioUseCase: GetUsuarioUseCase,
    private val getFichajesUseCase: GetFichajesUseCase,
    private val obtenerProyectoUseCase: ObtenerProyectoUseCase,
    ) : ViewModel() {

    suspend fun getUser(id:Int, context: Context):Usuario {
        return withContext(Dispatchers.IO) {
            getUsuarioUseCase.invoke(id, context)
        }
    }

    suspend fun getFichajes(id:Int, context: Context):List<Fichaje> {
        return withContext(Dispatchers.IO) {
            getFichajesUseCase.invoke(id, context)
        }
    }

    suspend fun getProyectos(context:Context):List<Proyecto> {
        return withContext(Dispatchers.IO) {
            obtenerProyectoUseCase(context)
        }
    }
}