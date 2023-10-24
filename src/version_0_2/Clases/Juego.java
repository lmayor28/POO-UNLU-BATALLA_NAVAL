package version_0_2.Clases;

import version_0_2.Clases.Tablero.Tablero;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego {

    private HashMap<Jugador, Tablero> TABLEROS;

    private ArrayList<Jugador> JUGADORES;

    private int FILAS_TABLERO;
    private int COLUMNAS_TABLERO;



    public Juego(int filas, int columnas){
        FILAS_TABLERO = filas;
        COLUMNAS_TABLERO = columnas;

        TABLEROS = new HashMap<>();
        JUGADORES = new ArrayList<>();

        Jugador jugador1 = new Jugador("Jugador 1");
        JUGADORES.add(jugador1);
        TABLEROS.put(jugador1, new Tablero(FILAS_TABLERO, COLUMNAS_TABLERO));

        Jugador jugador2 = new Jugador("Jugador 2");
        JUGADORES.add(jugador2);
        TABLEROS.put(jugador2, new Tablero(FILAS_TABLERO, COLUMNAS_TABLERO));


    }

}
