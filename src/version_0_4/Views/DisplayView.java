package version_0_4.Views;

import version_0_4.Clases.Jugador;
import version_0_4.Controllers.Controller;
import version_0_4.Models.Board.Cell;
import version_0_4.Observer.Observer;

import javax.swing.*;
import java.awt.*;

public class DisplayView implements View {


    private Controller controller;
    private Jugador jugador;
    private BoardView boardView = null;
    private Observer observer;

    // Componentes de la interfaz de usuario
    public JPanel PanelCarrusel;
    private JPanel MenuPrincipal, IniciarJugador, ColocarBarcos, PantallaCarga;
    private JButton jugarButton, continuarButton1, continuarButton, volverButton, volverButton1, button1;
    public JButton siguienteButton;
    private JTextField jugador1TextField;
    private JTable ColocarBarcosTable;
    private JLabel BarcosRestantes;

    // Etapa de juego
    private JPanel Juego;
    private JTable TableroPublicoEnemigo;
    private JPanel EsperandoJugador;
    private JProgressBar progressBar1;
    private JLabel TurnoDeJugador;
    private JLabel BarcosRestantesNUMERO;
    private JPanel Ganador;
    private JPanel Perdedor;
    private Integer cantidadBarcosRestantes;

    public void inicializar(){
        // FUNCIONALIDAD BOTONES
        jugarButton.addActionListener(e -> cambiarCarrusel("IniciarJugador"));
        volverButton.addActionListener(e -> cambiarCarrusel("MenuPrincipal"));
        volverButton1.addActionListener(e -> cambiarCarrusel("IniciarJugador"));
        continuarButton.addActionListener(e -> continuarJuego());
        siguienteButton.addActionListener(e -> verificarBarcos());

        //Public_Board = new JTable(); // TODO: Refactorizar despues.
    }

    private void cambiarCarrusel(String nombre){
        CardLayout cl = (CardLayout)(PanelCarrusel.getLayout());
        cl.show(PanelCarrusel, nombre);
    }

    private void continuarJuego() {
        if (jugador1TextField.getText().isEmpty()){
            // TODO: Mostrar mensaje de error
            return;
        }

        if (this.jugador == null){
            this.jugador = new Jugador(jugador1TextField.getText());
        }

        cambiarCarrusel("ColocarBarcos");
        controller.setJugador(this.jugador);

        if (boardView == null){
            // TODO: Mostrar mensaje de error(Observer Null);
            System.out.println("Se ha creado un tablero");
            boardView = new BoardView(ColocarBarcosTable, TableroPublicoEnemigo, jugador, observer, this);
            System.out.println("Controller Board: " + controller.getBoard());
            boardView.setBoard(controller.getBoard());
        }
    }

    private void verificarBarcos() {
        if (controller.consultarPonerBarcos()){
            // TODO: Mostrar mensaje de error que faltan poner barcos.
            return;
        }

        cambiarCarrusel("EsperandoJugador");
        controller.jugadorReady();
    }

    public boolean consultarPonerBarcos(){
        return controller.consultarPonerBarcos();
    }

    public void comenzarJuego(JTable enemyPublicBoard) {
        cambiarCarrusel("Juego");
        Juego.add(enemyPublicBoard);

        // TODO: Terminar de poner las diferentes propiedades.

    }


    @Override
    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public void cambiarCell(Cell cell2) {
        boardView.setBoatAtCell(cell2);
    }

    @Override
    public void cambiarCellPublic(Cell cell2) {
        boardView.setGuessPublicBoard(cell2);
    }

    @Override
    public void cambiarAJuegoPrincipal(JTable arrayLists) {
        comenzarJuego(arrayLists);
    }

    @Override
    public void cambiarTableroPublic(Cell celda) {
        boardView.setGuessPublicBoard(celda);
    }

    @Override
    public void cambiarVistaAJuego() {
        cambiarCarrusel("Juego");
    }

    @Override
    public void mostrarTurnoDelJuegador(Jugador turnoDelJugador) {
        if (turnoDelJugador.equals(this.getJugador())){
            TurnoDeJugador.setText("Tu turno !!");
        } else {
            TurnoDeJugador.setText("Turno del Enemigo !!");
        }
    }

    @Override
    public void setCantidadBarcosRestantes(Integer cantidad){

    }

    @Override
    public void hayGanador(Jugador jugador){

        if (jugador.equals(this.getJugador())){
            cambiarCarrusel("Ganador");
        } else {
            cambiarCarrusel("Perdedor");
        }

        //JOptionPane.showMessageDialog(null, "Ganador: " + jugador.getNombre());
    }

    @Override
    public void mostrarMenuPrincipal() {

    }

    @Override
    public void mostrarCargarBarcos() {

    }

    @Override
    public void mostrarTableroPrincipal() {

    }

    @Override
    public void mostrarCargaDeJugador() {

    }

    @Override
    public void setControllerObserver(Observer observer){
        this.observer = observer;
    }

    @Override
    public Jugador getJugador() {
        return this.jugador;
    }

    public void actualizarCantidadDeBarcos(Integer Cantidad){
        BarcosRestantes.setText(Cantidad.toString());
    }

    public Jugador turnoDelJugador(){
        return controller.turnoDeljugador();
    }

}


/*
public class DisplayView implements View {

    private Controller controller;

    private Jugador jugador;

    public JPanel PanelCarrusel;
    public JPanel MenuPrincipal;
    private JButton jugarButton;
    private JButton continuarButton1;
    private JTextField jugador1TextField;
    private JButton continuarButton;
    private JButton volverButton;
    private JPanel IniciarJugador;
    private JPanel ColocarBarcos;
    private JTable ColocarBarcosTable;
    private JButton siguienteButton;
    private JButton volverButton1;
    private JLabel BarcosRestantes;
    private JPanel PantallaCarga;
    private JButton button1;

    private BoardView boardView = null;
    private JTable Public_Board = new JTable();
    private Observer observer;

    private Integer cantidadBarcosRestantes;

    public void inicializar(){


        // FUNCIONALIDAD BOTONES
        jugarButton.addActionListener(e -> {
            // Se cambia el focus del panel carrusel a un panel de selection de jugador.
            System.out.println("Algo paso");
            cambiarCarrusel("IniciarJugador");

        });

        volverButton.addActionListener(e -> {
            System.out.println("A volver al menu principal");
            cambiarCarrusel("MenuPrincipal");
        });

        volverButton1.addActionListener(e -> {
            System.out.println("Volver a la selection de jugadores");
            cambiarCarrusel("IniciarJugador");
        });

        continuarButton.addActionListener(e -> {
            System.out.println("Continuar con el juego");

            if (jugador1TextField.getText().isEmpty()){

                System.out.println("No se ha introducido un nombre");
                return;
            }

            jugador = new Jugador(jugador1TextField.getText());
            cambiarCarrusel("ColocarBarcos");

            controller.setJugador(jugador);

            if (boardView == null){

                System.out.println("Observer Null");
                boardView = new BoardView(ColocarBarcosTable, Public_Board, jugador, observer, this);
                boardView.setBoard(controller.getBoard());
            }

        });

        siguienteButton.addActionListener(e -> {
            if (!BarcosRestantes.getText().equals("0")){

                System.out.println("No se han colocado todos los barcos");
                return;
            }

        });
    }


    @Override
    public void mostrarMenuPrincipal() {
        cambiarCarrusel("MenuPrincipal");
    }

    @Override
    public void mostrarCargarBarcos() {
        cambiarCarrusel("ColocarBarcos");
    }

    @Override
    public void mostrarTableroPrincipal() {

    }

    @Override
    public void mostrarCargaDeJugador() {
        cambiarCarrusel("IniciarJugador");
    }

    @Override
    public Jugador getJugador() {
        return jugador;
    }


    @Override
    public void setController(Controller controller){
        this.controller = controller;

    }

    @Override
    public void cambiarCell(Cell cell2) {
        boardView.setBoatAtCell(cell2);
    }


    @Override
    public void setControllerObserver(Observer observer){
        System.out.println("Observer se ha ajustado");
        this.observer = observer;
    }

    private void cambiarCarrusel(String nombre){
        CardLayout cl = (CardLayout)(PanelCarrusel.getLayout());
        cl.show(PanelCarrusel, nombre);
    }

    public void actualizarCantidadDeBarcos(Integer Cantidad){
        BarcosRestantes.setText(Cantidad.toString());
    }
}

 */
