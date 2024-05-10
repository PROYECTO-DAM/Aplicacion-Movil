package com.example.proyectodam.Screens.Main.Controller

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Classes.Proyecto
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Main.Data.FicharUseCase
import com.example.proyectodam.Screens.Main.Data.ObtenerProyectoUseCase
import com.example.proyectodam.Screens.Main.Data.ObtenerUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val obtenerUsuarioUseCase: ObtenerUsuarioUseCase,
    private val obtenerProyectoUseCase: ObtenerProyectoUseCase,
    private val ficharUseCase: FicharUseCase,
    ): ViewModel() {

    private val _proyecto = MutableLiveData<Proyecto>()
    val proyecto : LiveData<Proyecto> = _proyecto

    private val _horas = MutableLiveData<String>()
    val horas : LiveData<String> = _horas

    private val _fecha = MutableLiveData<Date>()
    val fecha : LiveData<Date> = _fecha

    private val _isFichajeEnabled = MutableLiveData<Boolean>()
    val isFichajeEnabled: LiveData<Boolean> = _isFichajeEnabled

    suspend fun getLoggedUser():Usuario {
        return withContext(Dispatchers.IO) {
            obtenerUsuarioUseCase()
        }
    }

    suspend fun obtenerProyectosDisponibles(context: Context):List<Proyecto> {
        return withContext(Dispatchers.IO) {
            obtenerProyectoUseCase(context)
        }
    }

    fun onFichajeChanged(proyecto:Proyecto, horas:String, fecha:Date) {
        _proyecto.value = proyecto
        _horas.value = horas
        _fecha.value = fecha
        _isFichajeEnabled.value = validarCampos(proyecto, horas, fecha)
    }

    fun onBtnClick(context:Context, user:Usuario) {
        val fichaje = Fichaje(0, user.id,_fecha.value!!, _horas.value!!.toInt(), _proyecto.value?.codigo!!)
        fichar(context, fichaje)
    }

    private fun validarCampos(proyecto:Proyecto, horas:String, fecha:Date):Boolean {
        return proyecto.nombre.isNotBlank() && horas.isNotBlank() && Pattern.compile("[0-9]*").matcher(horas).matches()
    }

    private fun fichar(context:Context, fichaje:Fichaje) {
        viewModelScope.launch {
            val result = ficharUseCase(fichaje, context)
            if(result) {
                AlertDialog.Builder(context)
                    .setTitle("Fichaje Exitoso")
                    .setMessage("El proceso de fichar ha sido un éxito")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}.show()
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Error al fichar")
                    .setMessage("Por favor, revisa bien los campos antes de intentar fichar")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}.show()
            }
        }
    }

}