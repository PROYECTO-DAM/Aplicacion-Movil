package com.example.proyectodam.Screens.Login.Controller

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Login.Data.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String):Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String):Boolean = password.isNotEmpty()

    fun onBtnClick(navController: NavHostController, context: Context) {
        if (_email.value != null && _password.value != null && _loginEnable.value!!) {
            doLogin(navController, context, _email.value!!, _password.value!!)
        }
        else {
            AlertDialog.Builder(context)
                .setTitle("Error al completar los datos")
                .setMessage("Falta algún campo por rellenar. Por favor, revisa bien los campos")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}.show()
        }
    }


    private fun doLogin(navController: NavHostController, context: Context, email:String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase(email, password, context)
            if(result.id != -1) {
                navController.navigate(Routes.Main.route)
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Error de inicio de sesión")
                    .setMessage("El inicio de sesión ha fallado. Por favor,verifica tus credenciales e intenta nuevamente")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}.show()
            }
        }
    }
}