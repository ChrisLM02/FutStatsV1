package com.example.futstats

import android.R
import android.annotation.SuppressLint
import android.graphics.Path
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.futstats.data.Equipo
import com.example.futstats.data.Jugador
import com.example.futstats.data.Partido
import com.example.futstats.dataproviders.DataProviderEquipos
import com.example.futstats.dataproviders.DataProviderJugadores
import com.example.futstats.dataproviders.DataProviderPartidos
import com.example.futstats.ui.theme.FutStatsTheme
import java.util.Calendar
import androidx.compose.*
import androidx.compose.foundation.Image
import androidx.compose.material.DismissValue
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.DismissDirection
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FutStatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    setUI()
                }
            }
        }
    }
}

/** La idea principal es realizar una aplicacion de estadisticas de partidos de LaLiga (exclusivamente) **/

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun setUI() {

    var text by remember { mutableStateOf("") }

    var active by remember { mutableStateOf(false) }

    var update by remember { mutableStateOf(false) }

    var lista = remember { mutableListOf("") }

    val jugadores = remember { DataProviderJugadores.listaJugadores }

    var partidos = remember { DataProviderPartidos.listaPartidos }

    var listaJugadores: ArrayList<Jugador> = ArrayList()

    var listaEquipos: ArrayList<Equipo> = ArrayList()

    var listaPartidos: ArrayList<Partido> = ArrayList()

    var equipoSeleccionado by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    var showDialogPartido by remember {
        mutableStateOf(false)
    }

    var partidoSeleccionado by remember {
        mutableStateOf<Partido?>(null)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "FUTSTATS",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { showDialog = true }) {
                Text(text = "Add")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Delete")
            }
        }

        Scaffold() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    query = text,
                    onQueryChange = {
                        text = it
                        equipoSeleccionado = text
                    },
                    onSearch = {
                        active = true
                        update = true
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = {
                        Text(text = "Buscar equipo...")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    text = ""
                                    active = false
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                ) {
                    val partidosFiltrados = remember {
                        DataProviderPartidos.listaPartidos.filter { partido ->
                            partido.jugador.equipo.nombre.contains(
                                equipoSeleccionado,
                                ignoreCase = true
                            )
                        }
                    }

                    if (update) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(
                                items = partidosFiltrados,
                                itemContent = { partido ->
                                    JugadoresListItem(partido = partido,
                                        onItemClicked = {
                                            partidoSeleccionado = it; showDialogPartido = true
                                        })
                                }
                            )
                        }
                    }
                }

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(
                        items = partidos,
                        itemContent = { partido ->
                            val dismissState = androidx.compose.material3.rememberDismissState()

                            SwipeToDismiss(
                                state = dismissState,
                                background = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Transparent),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Delete Icon",
                                            modifier = Modifier.padding(end = 16.dp)
                                        )
                                    }
                                }, dismissContent = {
                                    JugadoresListItem(partido = partido,
                                        onItemClicked = {
                                            partidoSeleccionado = it; showDialogPartido = true
                                        })
                                },
                                directions = setOf(DismissDirection.EndToStart)
                            )

                            LaunchedEffect(dismissState.isDismissed(DismissDirection.EndToStart)) {
                                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                                    partidos.remove(partido)
                                }
                            }
                        }
                    )
                }

                /*LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(
                        items = partidos,
                        itemContent = { partido ->
                            JugadoresListItem(partido = partido)
                        }
                    )
                }*/
            }
        }

        if (showDialog) {
            AddDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                ondAddClick = { partido ->
                    partidos.add(partido)
                    println("SE HA AÑADIDO EL PARTIDO \n")
                    update = !update
                }
            )
        }

        if (showDialogPartido && partidoSeleccionado != null){
            AddDialogPartido(partido = partidoSeleccionado,
                onDismiss = {showDialogPartido = false})
        }
    }
}

@Composable
fun AddDialogPartido(
    partido: Partido?,
    onDismiss: () -> Unit
) {
    if (partido != null) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.DarkGray
                    )
            ) {
                Column (modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                ) {
                    Image(painterResource(id = partido.jugador.imagenJugador), contentDescription = "Player Image")
                    Text(text = partido.jugador.nombre)
                    Text(text = partido.estadisticas)
                    Text(text = "${partido.jugador.equipo.nombre} VS ${partido.equipoRival.nombre}")
                    Text(text = partido.fecha.toString())
                }
            }
        }
    }
}

@Composable
fun AddDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    ondAddClick: (Partido) -> Unit
) {
    if (showDialog) {
        val jugadores = DataProviderJugadores.listaJugadores
        val equipos = DataProviderEquipos.listaEquipos

        var selectedPlayerIndex by remember { mutableStateOf(0) }
        var selectedTeamIndex by remember { mutableStateOf(0) }
        var goals by remember { mutableStateOf("") }
        var assists by remember { mutableStateOf("") }
        var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

        val cornerRadius = 16.dp

        val roundedShape: Shape = RoundedCornerShape(cornerRadius)

        Dialog(
            onDismissRequest = onDismiss
        ) {

            Surface(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.DarkGray,
                        shape = roundedShape
                    )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    var expandedPlayerMenu by remember { mutableStateOf(false) }
                    Box(
                        modifier = Modifier.clickable { expandedPlayerMenu = true }
                    ) {
                        Text(
                            "Seleccionar jugador: ${jugadores[selectedPlayerIndex].nombre}",
                            color = Color.Black
                        )
                        DropdownMenu(
                            expanded = expandedPlayerMenu,
                            onDismissRequest = { expandedPlayerMenu = false }
                        ) {
                            jugadores.forEachIndexed { index, jugador ->
                                androidx.compose.material.DropdownMenuItem(onClick = {
                                    selectedPlayerIndex = index
                                    expandedPlayerMenu = false
                                }) {
                                    Text(jugador.nombre)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    var expandedTeamMenu by remember { mutableStateOf(false) }
                    Box(
                        modifier = Modifier.clickable { expandedTeamMenu = true }
                    ) {
                        Text(
                            "Seleccionar equipo: ${equipos[selectedTeamIndex].nombre}",
                            color = Color.Black
                        )
                        DropdownMenu(
                            expanded = expandedTeamMenu,
                            onDismissRequest = { expandedTeamMenu = false }
                        ) {
                            equipos.forEachIndexed { index, equipo ->
                                androidx.compose.material.DropdownMenuItem(onClick = {
                                    selectedTeamIndex = index
                                    expandedTeamMenu = false
                                }) {
                                    Text(equipo.nombre)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = goals,
                        onValueChange = { goals = it },
                        label = { Text("Goles") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(value = assists,
                        onValueChange = { assists = it },
                        label = { Text("Asistencias") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        val selectedPlayer = jugadores[selectedPlayerIndex]
                        val selectedTeam = equipos[selectedTeamIndex]

                        val partido = Partido(
                            jugador = selectedPlayer,
                            equipoActual = selectedPlayer.equipo,
                            equipoRival = selectedTeam,
                            estadisticas = "G/A: $goals/$assists",
                            fecha = selectedDate.time
                        )

                        ondAddClick(partido)
                        onDismiss()

                    }) {
                        Text(text = "Añadir partido")
                    }
                }
            }
        }
    }
}