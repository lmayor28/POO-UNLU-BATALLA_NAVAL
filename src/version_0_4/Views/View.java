package version_0_4.Views;

import version_0_4.Clases.Jugador;
import version_0_4.Controllers.Controller;
import version_0_4.Controllers.ViewController;
import version_0_4.Models.Board.Cell;
import version_0_4.Observer.Observer;

public interface View {
    public void mostrarMenuPrincipal();
    public void mostrarCargarBarcos();
    public void mostrarTableroPrincipal();
    public void mostrarCargaDeJugador();

    public void setControllerObserver(Observer observer);

    public Jugador getJugador();

    public void setController(Controller controller);



    void cambiarCell(Cell cell2);
}
