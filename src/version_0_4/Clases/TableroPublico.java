package version_0_4.Clases;

import version_0_4.Models.Board.Cell;

import java.util.ArrayList;

public class TableroPublico {
    private final ArrayList<ArrayList<Cell>> tablero;
    private final Jugador jugador;

    public TableroPublico(Jugador jugador, ArrayList<ArrayList<Cell>> tablero) {
        this.jugador = jugador;
        this.tablero = tablero;
    }

    public ArrayList<ArrayList<Cell>> getTablero() {
        return tablero;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
