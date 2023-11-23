package version_0_4.Models;

import version_0_4.Clases.Jugador;
import version_0_4.Models.Board.Cell;
import version_0_4.Models.Board.CellPoint;

import java.util.ArrayList;

public interface Model {

    public void cellSetIn(CellPoint point);

    public ArrayList<ArrayList<Cell>> getBoard(Jugador jugador);
    public void cargarJugador(Jugador jugador);



}
