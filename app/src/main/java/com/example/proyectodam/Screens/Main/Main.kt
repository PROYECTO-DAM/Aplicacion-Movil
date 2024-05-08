package com.example.proyectodam.Screens.Main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Main.Controller.MainViewModel

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    MainScaffold(navController = navController, mainViewModel = mainViewModel)
}

@Composable
fun MainScaffold(navController: NavHostController, mainViewModel: MainViewModel) {

    var user by remember { mutableStateOf(Usuario(0,"","","","")) }

    LaunchedEffect(Unit) {
        val loggedUser = mainViewModel.getLoggedUser()
        user = loggedUser;
    }
    
    Scaffold(
        topBar = { BarraPerfil(navController, user) },
        bottomBar = {BarraDatos(navController)}
    ) {

    }
}

@Composable
fun BarraPerfil(navController: NavHostController, user:Usuario) {
    TopAppBar(
        title = { Text(text = "Fichajes y Nominas DEP", fontSize = 14.sp)},
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = user.fullname,
                    modifier = Modifier.padding(end = 16.dp),
                    style = TextStyle(Color.White)
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color.White,
                    modifier = Modifier.clickable { navController.navigate(Routes.Perfil.createRoute(user.id)) }
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
                imageVector = Icons.Default.Payments,
                contentDescription = "Nóminas",
                tint = Color.White,
                modifier = Modifier.clickable { /* Acción para ir a la pantalla de nominas */ }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}