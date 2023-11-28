package version_0_4.Controllers;

import version_0_4.Clases.CeldaObserver;
import version_0_4.Clases.Ganador;
import version_0_4.Clases.Jugador;
import version_0_4.Clases.TableroPublico;
import version_0_4.Models.Board.Cell;
import version_0_4.Models.Board.CellPoint;
import version_0_4.Models.Model;
import version_0_4.Observer.*;
import version_0_4.Views.View;

import java.util.ArrayList;

public class ViewController implements Observer, Controller{

    View mainView;
    Model model;


    public ViewController(View view, Subject modelSubject, Model model) {
        modelSubject.attach(this);

        this.mainView = view;
        this.mainView.setController(this);
        this.model  = model;
    }

    public ArrayList<ArrayList<Cell>> getBoard(){
        return model.getBoard(mainView.getJugador());
    }

    @Override
    public void setJugador(Jugador jugador) {
        model.cargarJugador(jugador);
    }

    @Override
    public boolean consultarPonerBarcos() {
        return model.consultarPonerBarcos(mainView.getJugador());
    }

    public void mostrarMenuPrincipal(){

    }

    @Override
    public void update(Object arg) {
        //TODO: Verificar que el objeto sea un CellPoint
        System.out.println("Cambiando Celda");
        if (arg instanceof CellPoint cell){
            model.cellSetIn(cell);
            System.out.println("Cambiando Celda");
        }

        if (arg instanceof Cell cell2){
            // TODO: CAMBIAR LA CELDA PARA QUE SE VERIFIQUE PARA QUIEN ES EL CAMBIO A REALIZAR.
        //    mainView.cambiarCell(cell2);
        }

        if (arg instanceof CeldaObserver celda){
            System.out.println("Jugador de la celda Observer: " + celda.getJugador());
            if (celda.getJugador().equals(mainView.getJugador())){
                System.out.println("Jugador de celda Observer vs jugador de controller: " + celda.getJugador() + " / " + mainView.getJugador());
                System.out.println("celda value: " + celda.getState());
                switch (celda.getTipoCambio()){
                    case COLOCAR_BARCO:
                        mainView.cambiarCell(celda);
                        break;
                    case GUESS:
                        model.guess(celda);
                        break;
                    case RESULTADO_GUESS:
                        mainView.cambiarCellPublic(celda);
                    default:
                        break;
                }
            }
        }

        if (arg instanceof TableroPublico tableroPublico){
            if (!tableroPublico.getJugador().equals(mainView.getJugador())){
                return;
            }

            mainView.cambiarVistaAJuego();

            System.out.println("Se ha colocado un tablero publico");;
        }

        if (arg instanceof Jugador turnoDelJugador){
            mainView.mostrarTurnoDelJuegador(turnoDelJugador);
        }

        if (arg instanceof Ganador ganador){
            mainView.hayGanador(ganador.getJugador());
        }

    }

    @Override
    public void jugadorReady(){
        this.model.jugardorReady(mainView.getJugador());
    }

    @Override
    public void viewReady(View view) {

    }

    @Override
    public Jugador turnoDeljugador(){
        return model.getTURNO_DEL_JUGADOR();
    }
}
