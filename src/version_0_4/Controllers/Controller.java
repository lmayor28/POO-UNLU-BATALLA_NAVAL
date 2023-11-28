package version_0_4.Controllers;

import version_0_4.Clases.Jugador;
import version_0_4.Models.Board.Cell;
import version_0_4.Views.View;

import java.util.ArrayList;

public interface Controller {

    void jugadorReady();

    public void viewReady(View view);
    public ArrayList<ArrayList<Cell>> getBoard();

    void setJugador(Jugador jugador);

    boolean consultarPonerBarcos();

    Jugador turnoDeljugador();
}
