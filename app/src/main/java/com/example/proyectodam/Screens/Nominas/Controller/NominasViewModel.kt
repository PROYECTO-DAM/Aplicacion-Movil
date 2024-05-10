package com.example.proyectodam.Screens.Nominas.Controller

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.proyectodam.Classes.Nomina
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Nominas.Data.GetNominasUseCase
import com.example.proyectodam.Screens.Perfil.Data.GetUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NominasViewModel @Inject constructor(
    private val getUsuarioUseCase: GetUsuarioUseCase,
    private val getNominasUseCase: GetNominasUseCase
    ):ViewModel() {

    suspend fun getUser(id:Int, context: Context):Usuario {
        return withContext(Dispatchers.IO) {
            getUsuarioUseCase.invoke(id, context)
        }
    }

    suspend fun getNominas(id:Int, context: Context):List<Nomina> {
        return withContext(Dispatchers.IO) {
            getNominasUseCase.invoke(id, context)
        }
    }

}