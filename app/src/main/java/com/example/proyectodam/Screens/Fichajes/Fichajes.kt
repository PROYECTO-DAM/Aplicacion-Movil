package com.example.proyectodam.Screens.Fichajes

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.proyectodam.Classes.Fichaje
import com.example.proyectodam.Classes.Proyecto
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Fichajes.Controller.FichajesViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FichajesScreen(navController:NavHostController, fichajesViewModel: FichajesViewModel, id:Int) {
    FichajesScaffold(navController, fichajesViewModel, id)
}

@Composable
fun FichajesScaffold(navController:NavHostController, fichajesViewModel: FichajesViewModel, id:Int) {
    var user by remember { mutableStateOf(Usuario(0,"","","","")) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val loggedUser = fichajesViewModel.getUser(id, context)
        user = loggedUser;
    }

    Scaffold(
        topBar = { BarraPerfil(navController, user) },
        content = { FichajesContent(fichajesViewModel, user, context) },
        bottomBar = { BarraDatos(navController, user) }
    )
}

@Composable
fun FichajesContent(fichajesViewModel:FichajesViewModel, user:Usuario, context: Context) {
    var fichajes:List<Fichaje> by remember { mutableStateOf(emptyList()) }
    var selectedFichaje:Fichaje? by remember { mutableStateOf(null) }
    var showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val fichajesObtenidos = fichajesViewModel.getFichajes(user.id, context)
        fichajes = fichajesObtenidos
    }

    if(fichajes.isNotEmpty()) {
        LazyColumn { itemsIndexed(fichajes) { index, fichaje ->
                FichajeRow(fichaje) {
                    selectedFichaje = fichaje
                    showDialog.value = true
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No se han encontrado fichajes en este usuario",
                style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold)
            )
        }
    }

    if(showDialog.value) {
        selectedFichaje?.let { fichaje ->
            FichajeDialog(fichaje, user, onCloseDialog = { showDialog.value = false }, fichajesViewModel, context)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FichajeDialog(fichaje: Fichaje, user:Usuario, onCloseDialog: () -> Unit, fichajesViewModel: FichajesViewModel, context: Context) {
    Dialog(
        onDismissRequest = onCloseDialog,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        FichajeCard(fichaje, user, fichajesViewModel = fichajesViewModel, context)
    }
}


@Composable
fun FichajeCard(fichaje: Fichaje, user:Usuario, fichajesViewModel: FichajesViewModel, context: Context) {
    var proyecto by remember { mutableStateOf(Proyecto(0,"No se ha encontrado el proyecto")) }
    var proyectosDisponibles by remember { mutableStateOf(listOf<Proyecto>()) }

    LaunchedEffect(Unit) {
        proyectosDisponibles = fichajesViewModel.getProyectos(context = context)
    }

    if(proyectosDisponibles.isNotEmpty()) {
        proyecto = proyectosDisponibles.find { it.codigo == fichaje.proyecto }!!

        Spacer(modifier = Modifier.height(12.dp))
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
                        text = "Trabajador:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = user.fullname,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Fecha:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatDateToYYYYMMDD(fichaje.fecha),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Horas:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = fichaje.horas.toString(),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Proyecto:",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = proyecto.nombre,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No se han encontrado Proyectos",
                style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun FichajeRow(fichaje:Fichaje, onFichajeChange: () -> Unit) {

    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Surface(
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Fichaje del día ${formatDateToYYYYMMDD(fichaje.fecha)}",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = onFichajeChange) {
                    Text(text = "Ver Fichaje")
                }
            }
        }
    }
}

@Composable
fun BarraPerfil(navController: NavHostController, user:Usuario) {
    TopAppBar(
        title = { Text(text = "Fichajes y Nominas DEP", fontSize = 14.sp) },
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
fun BarraDatos(navController: NavHostController, user: Usuario) {
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
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.White,
                modifier = Modifier.clickable { navController.navigate(Routes.Main.route) }
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


@SuppressLint("SimpleDateFormat")
fun formatDateToYYYYMMDD(date:Date?): String {
    if(date != null) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dateFormat.format(date)
        return formattedDate
    }
    return ""
}
