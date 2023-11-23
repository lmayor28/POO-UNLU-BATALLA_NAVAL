package version_0_4.Models.Board;



import version_0_4.Clases.Jugador;

import java.util.ArrayList;

public class Board {

    private ArrayList<ArrayList<Cell>> PUBLIC_BOARD;
    private ArrayList<ArrayList<Cell>> BOARD;
    int WITHD;
    int HIGHT;
    Jugador playerOwnership;

    public Board(int WITHD, int HIGHT, Jugador player){
        this.HIGHT = HIGHT;
        this.WITHD = WITHD;
        this.playerOwnership = player;

        createBoard();
        //createRandomBoard();
    }

    public Jugador getPlayerOwnership() {
        return playerOwnership;
    }

    private void createBoard(){
        this.BOARD = new ArrayList<>();
        this.PUBLIC_BOARD = new ArrayList<>();

        for (int i = 0; i < this.HIGHT; i++){
            BOARD.add(new ArrayList<>());
            PUBLIC_BOARD.add(new ArrayList<>());

            for (int j = 0; j < this.WITHD; j++){
                PUBLIC_BOARD.get(i).add(new Cell(i, j));
                BOARD.get(i).add(new Cell(i, j));

            }
        }
    }

    private void createRandomBoard(){
        this.BOARD = new ArrayList<>();
        this.PUBLIC_BOARD = new ArrayList<>();

        for (int i = 0; i < this.HIGHT; i++){
            BOARD.add(new ArrayList<>());
            PUBLIC_BOARD.add(new ArrayList<>());

            for (int j = 0; j < this.WITHD; j++){
                PUBLIC_BOARD.get(i).add(new Cell(i, j));
                BOARD.get(i).add(new Cell(i, j));

                String[] states = {" ", "B"};
                int random = (int) (Math.random() * states.length);


                BOARD.get(i).get(j).setState(states[random]);
                PUBLIC_BOARD.get(i).get(j).setState(Cell.EMPTY);
            }
        }
    }

    public void setCell(int i, int j,  String state){
        BOARD.get(i).get(j).setState(state);
    }

    public String guess(int i, int j){
        String boardContent = BOARD.get(i).get(j).getState();

        if (boardContent.equals(Cell.EMPTY)){
            PUBLIC_BOARD.get(i).get(j).setState(Cell.MISS);
            return Cell.MISS;
        }

        if (boardContent.equals(Cell.BOAT)){
            PUBLIC_BOARD.get(i).get(j).setState(Cell.HIT);
            return Cell.HIT;
        }

        return null;
    }

    public ArrayList<ArrayList<Cell>> getBoard() {
        return BOARD;
    }

    public String getCellContent(int i, int j, Jugador playerOwnership){
        if (playerOwnership.equals(this.playerOwnership)){
            return BOARD.get(i).get(j).getState();
        }

        return PUBLIC_BOARD.get(i).get(j).getState();
    }

    public String toStringBoard(){
        String board = " "; // Espacios iniciales para alinear los índices superiores con las celdas

        // Imprimir índices superiores
        for (int j = 0; j < BOARD.get(0).size(); j++){
            board += "   " + j + "   ";
        }
        board += "\n";

        for (int i = 0; i < BOARD.size(); i++){
            board += i; // Imprimir índice lateral

            for (int j = 0; j < BOARD.get(i).size(); j++){
                board += " | "  + BOARD.get(i).get(j).toString() + " | ";
            }
            board += "\n";
            if (i < BOARD.size() - 1) {
                board += new String(new char[BOARD.get(0).size() * 6]).replace("\0", "-") + "\n"; // Línea de separación
            }
        }

        return board;
    }

    public String toStringPublicBoard(){
        String board = "   "; // Espacios iniciales para alinear los índices superiores con las celdas

        // Imprimir índices superiores
        for (int j = 0; j < PUBLIC_BOARD.get(0).size(); j++){
            board += "   " + j + "  ";
        }
        board += "\n";

        for (int i = 0; i < PUBLIC_BOARD.size(); i++){
            board += i; // Imprimir índice lateral

            for (int j = 0; j < PUBLIC_BOARD.get(i).size(); j++){
                board += " | " + PUBLIC_BOARD.get(i).get(j).toString() + " | ";
            }
            board += "\n";
            if (i < PUBLIC_BOARD.size() - 1) {
                board += new String(new char[PUBLIC_BOARD.get(0).size() * 10]).replace("\0", "-") + "\n"; // Línea de separación
            }
        }

        return board;
    }







}
