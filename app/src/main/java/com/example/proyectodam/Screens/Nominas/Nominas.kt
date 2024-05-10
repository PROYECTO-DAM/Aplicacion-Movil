package com.example.proyectodam.Screens.Nominas

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Payments
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
import com.example.proyectodam.Classes.Nomina
import com.example.proyectodam.Classes.Usuario
import com.example.proyectodam.Routing.Routes
import com.example.proyectodam.Screens.Nominas.Controller.NominasViewModel

@Composable
fun NominasScreen(navController:NavHostController, nominasViewModel: NominasViewModel, id:Int) {
    NominasScaffold(navController = navController, nominasViewModel = nominasViewModel, id = id)
}

@Composable
fun NominasScaffold(navController:NavHostController, nominasViewModel: NominasViewModel, id:Int) {
    var user by remember { mutableStateOf(Usuario(0,"","","","")) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val loggedUser = nominasViewModel.getUser(id, context)
        user = loggedUser;
    }

    Scaffold(
        topBar = { BarraPerfil(navController, user) },
        content = { NominasContent(nominasViewModel, user, context) },
        bottomBar = { BarraDatos(navController, user) }
    )
}

@Composable
fun NominasContent(nominasViewModel: NominasViewModel, user: Usuario, context: Context) {
    var nominas: List<Nomina> by remember { mutableStateOf(emptyList()) }
    var selectedNomina: Nomina? by remember { mutableStateOf(null) }
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val nominasObtenidas = nominasViewModel.getNominas(user.id, context)
        nominas = nominasObtenidas
    }

    if (nominas.isNotEmpty()) {
        LazyColumn {
            itemsIndexed(nominas) { index, nomina ->
                NominaRow(nomina = nomina) {
                    selectedNomina = nomina
                    showDialog.value = true
                }
            }
        }
    }

    if (showDialog.value) {
        selectedNomina?.let { nomina ->
            NominaDialog(nomina = nomina, user, onCloseDialog = { showDialog.value = false })
        }
    }
}

@Composable
fun NominaRow(nomina: Nomina, onVerNominaClick: () -> Unit) {
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

                if(nomina.mes.toInt() < 10) {
                    Text(
                        text = "Nómina del mes 0${nomina.mes}-${nomina.año}",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                } else {
                    Text(
                        text = "Nómina del mes ${nomina.mes}-${nomina.año}",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = onVerNominaClick) {
                    Text(text = "Ver nómina")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NominaDialog(nomina: Nomina, user:Usuario, onCloseDialog: () -> Unit) {
    Dialog(
        onDismissRequest = onCloseDialog,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        NominaCard(nomina = nomina, user)
    }
}

@Composable
fun NominaCard(nomina: Nomina, user:Usuario) {
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
                    text = "Empleado:",
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
                if(nomina.mes.toInt() < 5) {
                    Text(
                        text = "0${nomina.mes}-${nomina.año}",
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                } else {
                    Text(
                        text = "${nomina.mes}-${nomina.año}",
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                }
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
                    text = nomina.horas.toString(),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pago:",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = nomina.pago.toString(),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
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
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Main",
                tint = Color.LightGray,
                modifier = Modifier.clickable { navController.navigate(Routes.Main.route) }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}
