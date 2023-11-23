package version_0_4.Models.Board;

import version_0_4.Clases.Jugador;

public class CellPoint {

    private int x;
    private int y;

    Jugador jugador;

    public CellPoint(int x, int y, Jugador jugador){
        this.x = x;
        this.y = y;
        this.jugador = jugador;
    }


    public Jugador  getJugador(){
        return jugador;
    }

    public int getX(){
        return x;
    }


    public int getY(){
        return y;
    }
}
