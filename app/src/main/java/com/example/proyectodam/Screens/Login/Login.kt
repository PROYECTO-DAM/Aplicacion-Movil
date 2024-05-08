package com.example.proyectodam.Screens.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Login.Controller.LoginViewModel


@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginEnable by loginViewModel.loginEnable.observeAsState(initial = false)
    val context = LocalContext.current

    Column(
        modifier = Modifier.background(Color(112,187,129))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it; loginViewModel.onLoginChanged(username, password) },
                label = { Text("Usuario") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it; loginViewModel.onLoginChanged(username, password) },
                label = { Text("Contraseña") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { loginViewModel.onBtnClick(navController, context) },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = loginEnable
            ) {
                Text("Iniciar Sesión")
            }
            Spacer(modifier = Modifier.height(12.dp))
            ClickableText(
                text = AnnotatedString("¿No tienes una cuenta? Crea una aquí"),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    navController.navigate(Routes.Register.route)
                }

            )
        }
    }
}
