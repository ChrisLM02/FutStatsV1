package com.example.futstats.data

import java.util.Date

data class Partido(var jugador: Jugador, var equipoActual: Equipo, var equipoRival:Equipo, var estadisticas:String, var fecha:Date)

/*TODO -> Sera el objeto que mostremos en nuestra lista. El atributo estadisticas seran los G y A.*/
