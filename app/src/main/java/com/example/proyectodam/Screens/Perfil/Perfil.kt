package com.example.proyectodam.Screens.Perfil

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Payment
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Perfil.Controller.PerfilViewModel

@Composable
fun PerfilScreen(navController: NavHostController, perfilViewModel: PerfilViewModel, id:Int) {
    PerfilScaffold(navController,perfilViewModel, id)
}

@Composable
fun PerfilScaffold(navController: NavHostController, perfilViewModel: PerfilViewModel, id:Int) {

    var user by remember { mutableStateOf(Usuario(0,"","","","")) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val loggedUser = perfilViewModel.getUser(id, context)
        user = loggedUser;
    }

    Scaffold(
        topBar = { BarraPerfil() },
        content = { ContenidoPerfil(usuario = user, navController)},
        bottomBar = { BarraDatos(navController, user) }
    )
}

@Composable
fun ContenidoPerfil(usuario: Usuario, navController: NavHostController) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Tu Perfil",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Surface(
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Name:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = usuario.fullname,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Correo:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = usuario.email,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rol:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = usuario.role,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(Routes.Main.route) }) {
            Text(text = "Volver")
        }
    }
}

@Composable
fun BarraPerfil() {
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
fun BarraDatos(navController: NavHostController, user:Usuario) {
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
                modifier = Modifier.clickable { navController.navigate(Routes.Fichajes.createRoute(user.id)) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Payment,
                contentDescription = "Nóminas",
                tint = Color.White,
                modifier = Modifier.clickable { navController.navigate(Routes.Nominas.createRoute(user.id)) }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}