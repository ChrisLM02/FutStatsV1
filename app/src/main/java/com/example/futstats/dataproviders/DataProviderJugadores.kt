package com.example.futstats.dataproviders

import com.example.futstats.R
import com.example.futstats.data.Equipo
import com.example.futstats.data.Jugador

object DataProviderJugadores {
    val jugador =
        Jugador(
            nombre = "Yangel Herrera",
            equipo = DataProviderEquipos.listaEquipos[0],
            imagenJugador = R.drawable.girona_yangel
        )

    val listaJugadores = listOf(
        jugador,

        Jugador(
            nombre = "Jude Bellingham",
            equipo = DataProviderEquipos.listaEquipos[1],
            imagenJugador = R.drawable.realmadrid_jude
        ),
        Jugador(
            nombre = "Robert Lewandowski",
            equipo = DataProviderEquipos.listaEquipos[2],
            imagenJugador = R.drawable.barcelona_lewandowski
        ),
        Jugador(
            nombre = "Antoine Griezmann",
            equipo = DataProviderEquipos.listaEquipos[3],
            imagenJugador = R.drawable.atleticomadrid_griezmann
        ),
        Jugador(
            nombre = "Unai Simón",
            equipo = DataProviderEquipos.listaEquipos[4],
            imagenJugador = R.drawable.athletic_unai
        ),
        Jugador(
            nombre = "Takefusa Kubo",
            equipo = DataProviderEquipos.listaEquipos[5],
            imagenJugador = R.drawable.realsociedad_kubo
        ),
        Jugador(
            nombre = "Isco Alarcón",
            equipo = DataProviderEquipos.listaEquipos[6],
            imagenJugador = R.drawable.betis_isco
        ),
        Jugador(
            nombre = "Marc Cardona",
            equipo = DataProviderEquipos.listaEquipos[7],
            imagenJugador = R.drawable.laspalmas_marc
        ),
        Jugador(
            nombre = "Mouctar Diakhaby",
            equipo = DataProviderEquipos.listaEquipos[8],
            imagenJugador = R.drawable.valencia_diakhaby
        ),
        Jugador(
            nombre = "Radamel Falcao",
            equipo = DataProviderEquipos.listaEquipos[9],
            imagenJugador = R.drawable.rayo_falcao
        ),
        Jugador(
            nombre = "Borja Mayoral",
            equipo = DataProviderEquipos.listaEquipos[10],
            imagenJugador = R.drawable.getafe_borja
        ),
        Jugador(
            nombre = "Jon Moncayola",
            equipo = DataProviderEquipos.listaEquipos[11],
            imagenJugador = R.drawable.osasuna_jon
        ),
        Jugador(
            nombre = "Sergio Ramos",
            equipo = DataProviderEquipos.listaEquipos[12],
            imagenJugador = R.drawable.sevilla_sergio
        ),
        Jugador(
            nombre = "Alexander Sörloth",
            equipo = DataProviderEquipos.listaEquipos[13],
            imagenJugador = R.drawable.villarreal_sorloth
        ),
        Jugador(
            nombre = "Kike García",
            equipo = DataProviderEquipos.listaEquipos[14],
            imagenJugador = R.drawable.alaves_kike
        ),
        Jugador(
            nombre = "Roger Martí",
            equipo = DataProviderEquipos.listaEquipos[15],
            imagenJugador = R.drawable.cadiz_roger
        ),
        Jugador(
            nombre = "Pablo Maffeo",
            equipo = DataProviderEquipos.listaEquipos[16],
            imagenJugador = R.drawable.mallorca_maffeo
        ),
        Jugador(
            nombre = "Iago Aspas",
            equipo = DataProviderEquipos.listaEquipos[17],
            imagenJugador = R.drawable.celta_iago
        ),
        Jugador(
            nombre = "Myrto Uzuni",
            equipo = DataProviderEquipos.listaEquipos[18],
            imagenJugador = R.drawable.granada_uzuni
        ),
        Jugador(
            nombre = "Luis Suarez",
            equipo = DataProviderEquipos.listaEquipos[19],
            imagenJugador = R.drawable.almeria_suarez
        )
    )
}