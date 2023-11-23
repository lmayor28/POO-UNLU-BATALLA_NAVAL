package version_0_4.Models;

import version_0_4.Clases.Jugador;
import version_0_4.Models.Board.Board;
import version_0_4.Models.Board.Cell;
import version_0_4.Models.Board.CellPoint;
import version_0_4.Observer.Observer;
import version_0_4.Observer.Subject;

import java.util.ArrayList;
import java.util.HashMap;

public class CoreModel extends Subject implements Observer, Model {
    HashMap<Jugador, Board> LISTA_DE_TABLEROS;
    HashMap<Jugador, Integer> CANTIDAD_BARCOS;
    ArrayList<Jugador> LISTA_DE_JUGADORES;

    public CoreModel(){
        LISTA_DE_TABLEROS = new HashMap<>();
        LISTA_DE_JUGADORES = new ArrayList<>();
        CANTIDAD_BARCOS = new HashMap<>();
    }

    public void cargarJugador(Jugador jugador){
        if (LISTA_DE_JUGADORES.contains(jugador)){
            return;
        }

        LISTA_DE_JUGADORES.add(jugador);
        CANTIDAD_BARCOS.put(jugador, 0);
        LISTA_DE_TABLEROS.put(jugador, new Board(10, 10, jugador));

    }

    public ArrayList<ArrayList<Cell>> getTablero(Jugador jugador){
        if (!LISTA_DE_JUGADORES.contains(jugador)){
            // TODO: Revisar la generacion de un error mediante excepciones o algo por el estilo
            return null;
        }

        return LISTA_DE_TABLEROS.get(jugador).getBoard();
    }


    @Override
    public void update(Object arg) {
        if (arg instanceof Cell cell){

        }



    }

    //public ArrayList<ArrayList<Cell>> requestTablero(){

    //}

    @Override
    public void cellSetIn(CellPoint point) {
        if (!LISTA_DE_JUGADORES.contains(point.getJugador())){
            //TODO: Mostrar mensaje de error
            return;
        }
        Cell celdaACAmbiar = LISTA_DE_TABLEROS.get(point.getJugador()).getBoard().get(point.getX()).get(point.getY());
        if (celdaACAmbiar.getState().equals("B")){
            return;
        }

        LISTA_DE_TABLEROS.get(point.getJugador()).setCell(point.getX(), point.getY(), "B");
        CANTIDAD_BARCOS.put(point.getJugador(), CANTIDAD_BARCOS.get(point.getJugador()) + 1);
        Cell celdaCambiada = LISTA_DE_TABLEROS.get(point.getJugador()).getBoard().get(point.getX()).get(point.getY());

        notifyObservers(celdaCambiada);
    }

    public Integer getCantidadBarcos(Jugador jugador){
        return CANTIDAD_BARCOS.get(jugador);
    }

    @Override
    public ArrayList<ArrayList<Cell>> getBoard(Jugador jugador) {
        if (!LISTA_DE_JUGADORES.contains(jugador)){
            System.out.println("Error");
            return null;
        }
        return LISTA_DE_TABLEROS.get(jugador).getBoard();
    }

}
