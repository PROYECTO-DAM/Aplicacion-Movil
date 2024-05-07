package com.example.proyectodam.Screens.Register.Controller

import android.app.AlertDialog
import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Register.Data.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable : LiveData<Boolean> = _registerEnable

    private val _fullname = MutableLiveData<String>()
    val fullname : LiveData<String> = _fullname

    private val _role = MutableLiveData<String>()
    val role : LiveData<String> = _role

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    fun onRegisterChanged(name:String, role:String, email: String, password: String){
        _fullname.value = name
        _role.value = role
        _email.value = email
        _password.value = password
        _registerEnable.value = isValidName(name) && isValidRole(role) && isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidName(name:String):Boolean {
        return name.length > 10
    }

    private fun isValidRole(role:String):Boolean {
        return role.isNotEmpty()
    }

    private fun isValidEmail(email: String):Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String):Boolean {
        return password.length > 6
    }

    fun onBtnClick(navController: NavHostController, context: Context) {
        registrarse(navController, context, _fullname.value!!, _role.value!!, _email.value!!, _password.value!!)
    }

    private fun registrarse(navController: NavHostController, context: Context, nombre:String, rol:String, email:String, password:String) {
        viewModelScope.launch {
            val usuario:Usuario = Usuario(0, nombre, rol, email, password)
            val result = registerUseCase(usuario, context)
            if(result.id != -1) {
                navController.navigate(Routes.Main.route)
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Error al crear el usuario")
                    .setMessage("El registro ha fallado. Por favor,verifica tus credenciales e intenta nuevamente")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}.show()
            }
        }
    }
}