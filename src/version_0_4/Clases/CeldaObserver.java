package version_0_4.Clases;

import version_0_4.Models.Board.Cell;

public class CeldaObserver extends Cell {
    private final TipoCambio tipoCambio;


    public Jugador getJugador() {
        return propietario;
    }


    public enum TipoCambio {
        COLOCAR_BARCO,
        GUESS,
        RESULTADO_GUESS;



    }
    Jugador propietario;
    public CeldaObserver(Cell celdaCambiada, Jugador jugador, TipoCambio tipoCambio) {
        this(celdaCambiada.getX(), celdaCambiada.getY(), celdaCambiada.getState() , jugador, tipoCambio);
    }

    public CeldaObserver(int x, int y, String state, Jugador jugador, TipoCambio tipoCambio) {
        super(x, y, state);
        this.propietario = jugador;
        this.tipoCambio = tipoCambio;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }
}
