package com.example.proyectodam.Screens.Main

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectodam.Classes.Proyecto
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Main.Controller.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

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
        content = {MainContent(mainViewModel, user)},
        bottomBar = {BarraDatos(navController, user)}
    )
}

@Composable
fun MainContent(mainViewModel: MainViewModel, user:Usuario) {
    var proyecto by remember { mutableStateOf(Proyecto(0,"")) }
    var proyectosDisponibles by remember { mutableStateOf(listOf<Proyecto>()) }
    var horas by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(Date()) }

    var expanded by remember { mutableStateOf(false) }
    var datePickerExpanded by remember { mutableStateOf(false) }

    val isButtonEnabled = mainViewModel.isFichajeEnabled.observeAsState(initial = false).value

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        proyectosDisponibles = mainViewModel.obtenerProyectosDisponibles(context)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Selecciona el proyecto: ${proyecto.nombre}",
                modifier = Modifier.clickable { expanded = true }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { },
                modifier = Modifier.fillMaxWidth(),
                content = {
                    proyectosDisponibles.forEach { proy ->
                        DropdownMenuItem(onClick = {
                            proyecto = proy
                            mainViewModel.onFichajeChanged(proyecto,horas,fecha)
                            expanded = false
                        }) {
                            Text(text = proy.nombre)
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Horas")
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = horas,
                onValueChange = {
                    horas = it
                    mainViewModel.onFichajeChanged(proyecto,horas,fecha)
                },
                label = { Text("Ingresa las horas") }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { datePickerExpanded = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Seleccionar fecha")
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            Text("Fecha Seleccionada ${formatDateToYYYYMMDD(fecha)}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (datePickerExpanded) {
                DatePicker() { selectedDate ->
                    fecha = selectedDate
                    mainViewModel.onFichajeChanged(proyecto,horas,fecha)
                    datePickerExpanded = false
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { mainViewModel.onBtnClick(context, user) },
                modifier = Modifier.fillMaxWidth(),
                enabled = isButtonEnabled
            ) {
                Text("Fichar")
            }

        }
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
                imageVector = Icons.Default.Payments,
                contentDescription = "Nóminas",
                tint = Color.White,
                modifier = Modifier.clickable { navController.navigate(Routes.Nominas.createRoute(user.id)) }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun DatePicker(onDateChange: (Date) -> Unit) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val calendar = Calendar.getInstance()
        calendar.time = Date()

        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            onDateChange(calendar.time)
        }

        val datePickerDialog = DatePickerDialog(
            context,
            onDateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}

fun formatDateToYYYYMMDD(date:Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = dateFormat.format(date)
    return formattedDate
}

