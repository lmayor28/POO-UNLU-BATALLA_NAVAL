package version_0_4.Models.Board;

import version_0_4.Clases.Jugador;

public class Cell {

    int x;
    int y;

    static final String BOAT = "B";
    static final String EMPTY = " ";
    static final String MISS = "M";
    static final String HIT = "X";

    Jugador jugador;


    private String state = EMPTY;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, String state, Jugador jugador) {
        this(x, y);
        this.jugador = jugador;
        this.state = state;
    }

    public Cell(int x, int y, String state) {
        this(x, y);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
