package version_0_4.Models;

import version_0_4.Clases.CeldaObserver;
import version_0_4.Clases.Ganador;
import version_0_4.Clases.Jugador;
import version_0_4.Clases.TableroPublico;
import version_0_4.Models.Board.Board;
import version_0_4.Models.Board.Cell;
import version_0_4.Models.Board.CellPoint;
import version_0_4.Observer.Observer;
import version_0_4.Observer.Subject;

import java.util.ArrayList;
import java.util.HashMap;

public class CoreModel extends Subject implements Observer, Model {
    HashMap<Jugador, Board> LISTA_DE_TABLEROS;
    HashMap<Jugador, Integer> CANTIDAD_BARCOS_RESTANTES;
    HashMap<Jugador, Integer> CANTIDAD_BARCOS_EN_JUEGO;
    HashMap<Jugador, Jugador> JUGADORES_EN_JUEGO;
    ArrayList<Jugador> LISTA_DE_JUGADORES;

    Jugador TURNO_DEL_JUGADOR;



    ArrayList<Jugador> JUGADORES_READY;

    private final int CANTIDAD_DE_BARCOS = 5;



    public CoreModel(){
        LISTA_DE_TABLEROS = new HashMap<>();
        LISTA_DE_JUGADORES = new ArrayList<>();
        CANTIDAD_BARCOS_RESTANTES = new HashMap<>();
        JUGADORES_READY = new ArrayList<>();
        CANTIDAD_BARCOS_EN_JUEGO = new HashMap<>();
        JUGADORES_EN_JUEGO = new HashMap<>();
    }

    public void cargarJugador(Jugador jugador){
        if (LISTA_DE_JUGADORES.contains(jugador)){
            return;
        }
        LISTA_DE_JUGADORES.add(jugador);
        CANTIDAD_BARCOS_RESTANTES.put(jugador, 0);
        LISTA_DE_TABLEROS.put(jugador, new Board(10, 10, jugador));
        CANTIDAD_BARCOS_RESTANTES.put(jugador, CANTIDAD_DE_BARCOS);

        System.out.println("Jugador cargado, cantidad de TABLEROS: " + LISTA_DE_TABLEROS.size());
    }

    @Override
    public boolean consultarPonerBarcos(Jugador jugador) {
        return CANTIDAD_BARCOS_RESTANTES.get(jugador) > 0;
    }

    @Override
    public void jugardorReady(Jugador jugador) {
        // Verifica que si el jugador ya está en la lista de ready.
        if (JUGADORES_READY.isEmpty()){
            System.out.println("NO hay jugadores listo");
        } else {
            for (Jugador  j : JUGADORES_READY){
                System.out.println("El jugador: " + j.getNombre() + " esta listo.");
            }
        }

        if (JUGADORES_READY.contains(jugador)){
            return;
        }
        JUGADORES_READY.add(jugador);
        // Si hay dos jugadores en la lista de ready, notifica a los observers y empieza el juego.
        if (JUGADORES_READY.size() == 2){
            HashMap<Jugador, ArrayList<ArrayList<Cell>>> JugadorListos = new HashMap<>();
            Jugador jugador1, jugador2;
            jugador1 = JUGADORES_READY.get(0);
            jugador2 = JUGADORES_READY.get(1);

            TableroPublico tableroPublico1 = new TableroPublico(jugador1, LISTA_DE_TABLEROS.get(jugador2).getPublicBoard());
            TableroPublico tableroPublico2 = new TableroPublico(jugador2, LISTA_DE_TABLEROS.get(jugador1).getPublicBoard());

            notifyObservers(tableroPublico1);
            notifyObservers(tableroPublico2);

            JUGADORES_EN_JUEGO.put(jugador1, jugador2);
            JUGADORES_EN_JUEGO.put(jugador2, jugador1);

            for (Jugador j : JUGADORES_READY){
                CANTIDAD_BARCOS_EN_JUEGO.put(j, CANTIDAD_DE_BARCOS);
            }

            TURNO_DEL_JUGADOR = jugador1;
            notifyObservers(TURNO_DEL_JUGADOR);
        }
    }

    @Override
    public void guess(CeldaObserver celda) {
        String result = LISTA_DE_TABLEROS.get(JUGADORES_EN_JUEGO.get(celda.getJugador())).guess(celda.getX(), celda.getY());
        CeldaObserver celdaResultado = new CeldaObserver(celda.getX(), celda.getY(), result,celda.getJugador(), CeldaObserver.TipoCambio.RESULTADO_GUESS);
        if (result.equals("HIT")){
            System.out.println(" Cantidad de barcos de "+ celda.getJugador() + CANTIDAD_BARCOS_EN_JUEGO.get(celda.getJugador()));
            System.out.println("Cantidad de Barcos restante del Jugador actual: " + CANTIDAD_BARCOS_RESTANTES.get(TURNO_DEL_JUGADOR));

            CANTIDAD_BARCOS_EN_JUEGO.put(TURNO_DEL_JUGADOR, CANTIDAD_BARCOS_EN_JUEGO.get(TURNO_DEL_JUGADOR) - 1);
            if (CANTIDAD_BARCOS_EN_JUEGO.get(TURNO_DEL_JUGADOR) == 0){
                finalDelJuego();
            }
        }

        TURNO_DEL_JUGADOR = JUGADORES_EN_JUEGO.get(TURNO_DEL_JUGADOR);
        notifyObservers(celdaResultado);
        notifyObservers(TURNO_DEL_JUGADOR);
    }

    private void finalDelJuego(){
        Ganador  ganador = new Ganador(TURNO_DEL_JUGADOR);
        notifyObservers(ganador);
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
            System.out.println("Algo paso en el update del modelo");
        }



    }

    @Override
    public void cellSetIn(CellPoint point) {
        if (!LISTA_DE_JUGADORES.contains(point.getJugador())){
            //TODO: Mostrar mensaje de error
            return;
        }
        Cell celdaACambiar = LISTA_DE_TABLEROS.get(point.getJugador()).getBoard().get(point.getX()).get(point.getY());
        if (celdaACambiar.getState().equals("B")){
            return;
        }

        LISTA_DE_TABLEROS.get(point.getJugador()).setCell(point.getX(), point.getY(), "B");
        // TODO: BOrrar despues.
        //Cell celdaSelecionadaCreo = LISTA_DE_TABLEROS.get(point.getJugador()).getBoard().get(point.getX()).get(point.getY());
        //System.out.println("LA celda " + celdaSelecionadaCreo.getX() + " " + celdaSelecionadaCreo.getY() + " ha sido cambiada por un valor de: " + celdaSelecionadaCreo.getState());

        CANTIDAD_BARCOS_RESTANTES.put(point.getJugador(), CANTIDAD_BARCOS_RESTANTES.get(point.getJugador()) - 1);
        //CANTIDAD_BARCOS_RESTANTES.put(point.getJugador(), CANTIDAD_BARCOS_RESTANTES.get(point.getJugador()) - 1);
        Cell celdaCambiada = LISTA_DE_TABLEROS.get(point.getJugador()).getBoard().get(point.getX()).get(point.getY());
        //System.out.println("La celda cambiada tiene un valor de: " + celdaCambiada.getState());
        // TODO: Borra lineas.
        System.out.println("Jugador de point cell: " + point.getJugador());
        CeldaObserver celdaObserver = new CeldaObserver(celdaCambiada, point.getJugador(), CeldaObserver.TipoCambio.COLOCAR_BARCO);



        notifyObservers(celdaObserver);
    }

    public Integer getCantidadBarcos(Jugador jugador){
        return CANTIDAD_BARCOS_RESTANTES.get(jugador);
    }

    public Integer getCantidadBarcosRestantes(Jugador jugador){
        return CANTIDAD_BARCOS_RESTANTES.get(jugador);
    }

    public int getCantidadBarcosEnJuego(Jugador jugador){
        return CANTIDAD_BARCOS_EN_JUEGO.get(jugador);
    }

    @Override
    public ArrayList<ArrayList<Cell>> getBoard(Jugador jugador) {
        if (!LISTA_DE_JUGADORES.contains(jugador)){
            System.out.println("Error");
            return null;
        }
        return LISTA_DE_TABLEROS.get(jugador).getBoard();
    }

    public boolean validarJugador(Jugador jugador){
        return LISTA_DE_JUGADORES.contains(jugador);
    }

    @Override
    public Jugador getTURNO_DEL_JUGADOR(){
        return TURNO_DEL_JUGADOR;
    }
}
