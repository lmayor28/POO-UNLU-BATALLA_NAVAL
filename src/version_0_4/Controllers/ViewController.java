package version_0_4.Controllers;

import version_0_4.Clases.Jugador;
import version_0_4.Models.Board.Cell;
import version_0_4.Models.Board.CellPoint;
import version_0_4.Models.Model;
import version_0_4.Observer.*;
import version_0_4.Views.View;

import java.awt.*;
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
            mainView.cambiarCell(cell2);
        }




    }

    @Override
    public void viewReady(View view) {

    }
}
