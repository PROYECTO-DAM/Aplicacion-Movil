package com.example.proyectodam.Screens.Register

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectodam.Screens.Register.Controller.RegisterViewModel

@Composable
fun RegisterScreen(navController:NavHostController, registerViewModel: RegisterViewModel) {
    var username by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val registerEnable by registerViewModel.registerEnable.observeAsState(initial = false)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crea una cuenta",
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it; registerViewModel.onRegisterChanged(username, role, email, password) },
            label = { Text("Nombre Completo") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = role,
            onValueChange = { role = it; registerViewModel.onRegisterChanged(username, role, email, password) },
            label = { Text("Rol") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it; registerViewModel.onRegisterChanged(username, role, email, password) },
            label = { Text("Correo") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; registerViewModel.onRegisterChanged(username, role, email, password) },
            label = { Text("Contraseña") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { registerViewModel.onBtnClick(navController, context) },
            modifier = Modifier.fillMaxWidth(),
            enabled = registerEnable
        ) {
            Text("Registrarse")
        }
    }
}