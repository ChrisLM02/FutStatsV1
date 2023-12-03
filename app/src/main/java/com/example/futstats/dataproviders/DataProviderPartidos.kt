package com.example.futstats.dataproviders

import com.example.futstats.data.Partido
import java.util.Date

object DataProviderPartidos {
    val listaPartidos: MutableList<Partido> = mutableListOf(
        Partido(
            jugador = DataProviderJugadores.listaJugadores[0],
            equipoActual = DataProviderEquipos.listaEquipos[0],
            equipoRival = DataProviderEquipos.listaEquipos[1],
            estadisticas = "G/A: 1/0",
            fecha = Date()
        ),
        Partido(
            jugador = DataProviderJugadores.listaJugadores[1],
            equipoActual = DataProviderJugadores.listaJugadores[1].equipo,
            equipoRival = DataProviderEquipos.listaEquipos[3],
            estadisticas = "G/A: 1/1",
            fecha = Date()
        ),
        Partido(
            jugador = DataProviderJugadores.listaJugadores[1],
            equipoActual = DataProviderJugadores.listaJugadores[1].equipo,
            equipoRival = DataProviderEquipos.listaEquipos[7],
            estadisticas = "G/A: 2/1",
            fecha = Date()
        ),
        Partido(
            jugador = DataProviderJugadores.listaJugadores[6],
            equipoActual = DataProviderJugadores.listaJugadores[6].equipo,
            equipoRival = DataProviderEquipos.listaEquipos[2],
            estadisticas = "G/A: 0/0",
            fecha = Date()
        ),
        Partido(
            jugador = DataProviderJugadores.listaJugadores[12],
            equipoActual = DataProviderJugadores.listaJugadores[12].equipo,
            equipoRival = DataProviderEquipos.listaEquipos[5],
            estadisticas = "G/A: 2/1",
            fecha = Date()
        ),
        Partido(
            jugador = DataProviderJugadores.listaJugadores[16],
            equipoActual = DataProviderJugadores.listaJugadores[16].equipo,
            equipoRival = DataProviderEquipos.listaEquipos[9],
            estadisticas = "G/A: 2/1",
            fecha = Date()
        ),
    )
}