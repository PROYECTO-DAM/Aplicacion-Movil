package com.example.proyectodam.Screens.Perfil

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Screens.Main.Controller.MainViewModel
import com.example.proyectodam.Screens.Perfil.Controller.PerfilViewModel

@Composable
fun PerfilScreen(navController: NavHostController, perfilViewModel: PerfilViewModel, id:Int) {
    PerfilScaffold(navController,perfilViewModel, id)
}

@Composable
fun PerfilScaffold(navController: NavHostController, perfilViewModel: PerfilViewModel, id:Int) {

    var user by remember { mutableStateOf(Usuario(0,"","","","")) }

    LaunchedEffect(Unit) {
        val loggedUser = perfilViewModel.getUser(id)
        user = loggedUser;
    }

    Scaffold(
        topBar = { BarraPerfil(navController) },
        content = { ContenidoPerfil(perfilViewModel = perfilViewModel, usuario = user)},
        bottomBar = { BarraDatos(navController) }
    )
}

@Composable
fun ContenidoPerfil(perfilViewModel: PerfilViewModel, usuario: Usuario) {

}

@Composable
fun BarraPerfil(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Fichajes y Nominas DEP", fontSize = 14.sp) },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Tu Perfil",
                    modifier = Modifier.padding(end = 16.dp),
                    style = TextStyle(Color.White)
                )
            }
        },
        backgroundColor = Color(123,41,46),
        contentColor = Color.White
    )
}

@Composable
fun BarraDatos(navController: NavHostController) {
    BottomAppBar(
        backgroundColor = Color(123,41,46),
        modifier = Modifier.height(56.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Fichajes",
                tint = Color.White,
                modifier = Modifier.clickable { /* Acción para ir a la pantalla de fichajes */ }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Payment,
                contentDescription = "Nóminas",
                tint = Color.White,
                modifier = Modifier.clickable { /* Acción para ir a la pantalla de nominas */ }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}