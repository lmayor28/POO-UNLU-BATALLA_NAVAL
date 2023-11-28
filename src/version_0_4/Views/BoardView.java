package version_0_4.Views;

//import version_0_4.Models.Clases.Board.Cell;
import version_0_4.Clases.CeldaObserver;
import version_0_4.Clases.Jugador;
import version_0_4.Models.Board.*;
import version_0_4.Observer.*;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*
public class BoardView extends Subject {

    JTable BOARD;
    JTable PUBLIC_BOARD;
    Jugador jugador;

    DisplayView view;

    Integer cantidadBarcos = 0;

    private Integer cantidadDEBarcosRestantes = 15;

    public BoardView(JTable Board, JTable Public_Board, Jugador jugador, Observer observer, DisplayView view) {
        BOARD = Board;
        PUBLIC_BOARD = Public_Board;
        this.jugador = jugador;
        this.attach(observer);
        this.view = view;
    }

    public void setBOARD (ArrayList<ArrayList<Cell>> board) {
        DefaultTableModel model = new DefaultTableModel(board.size(), board.get(0).size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel model2 = new DefaultTableModel(board.size(), board.get(0).size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        BOARD.setDefaultRenderer(Object.class, centerRenderer);
        BOARD.setModel(model);

        //PUBLIC_BOARD.setDefaultRenderer(Object.class, centerRenderer);cellSize
        PUBLIC_BOARD.setModel(model2);

        for (ArrayList<Cell> row : board) {
            for (Cell cell : row) {
                //System.out.println("Celda: " + cell.getX() + " " + cell.getY() + " " + cell.getState());

                BOARD.setValueAt(cell.getState(), cell.getX(), cell.getY());
                PUBLIC_BOARD.setValueAt( "E", cell.getX(), cell.getY());
            }
        }

        int cellSize = 50;
        BOARD.setRowHeight(cellSize);
        PUBLIC_BOARD.setRowHeight(cellSize);

        for (int i = 0; i < BOARD.getColumnCount(); i++) {
            TableColumn column = BOARD.getColumnModel().getColumn(i);
            TableColumn column2 = PUBLIC_BOARD.getColumnModel().getColumn(i);

            column.setPreferredWidth(cellSize);
            column2.setPreferredWidth(cellSize);
        }


        BOARD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if (cantidadDEBarcosRestantes < 0){
                    return;
                }
                int row = BOARD.rowAtPoint(e.getPoint());
                int col = BOARD.columnAtPoint(e.getPoint());
                System.out.println("Click at Cell:  " + row + " " + col + " " + BOARD.getValueAt(row, col));
                notifyObservers(new CellPoint(col, row, jugador));
                cantidadDEBarcosRestantes--;
                view.actualizarCantidadDeBarcos(cantidadDEBarcosRestantes);
            }
        });


        // TODO: Ver que pasa con este codigo de porque no funciona y creo que se cambia el setDefaultRender en alguna otra parte.
        BOARD.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);



                switch (value.toString()) {
                    case "E":
                        c.setBackground(Color.GREEN);
                        break;
                    case "B":
                        c.setBackground(Color.orange);
                        break;
                    case "X":
                        c.setBackground(Color.BLUE);
                        break;
                    case "M":
                        c.setBackground(Color.YELLOW);
                        break;
                }
                return c;
            }
        });




        //BOARD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //PUBLIC_BOARD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    public void setBoatAtCell(Cell cell2) {

        BOARD.setValueAt(cell2.getState(), cell2.getY(), cell2.getX());
    }
} */

public class BoardView extends Subject {

    private final JTable board;
    private final JTable publicBoard;
    private Jugador jugador;
    private final DisplayView view;
    private Integer cantidadDEBarcosRestantes = 5;

    public BoardView(JTable board, JTable publicBoard, Jugador jugador, Observer observer, DisplayView view) {
        this.board = board;
        this.publicBoard = publicBoard;
        //TODO: Borra linea
        //System.out.println("Board and public board" + board + publicBoard);
        this.jugador = jugador;

        // Tiene como observadores al Controlador.
        this.attach(observer);
        this.view = view;
    }

    public void setBoard(ArrayList<ArrayList<Cell>> board) {
        setTable(this.board, board);
        setTable(this.publicBoard, board);
        setMouseListener(this.board);
        setCellRenderer(this.board);

        setMouseListenerPublicBoard(this.publicBoard);
        setCellRenderer(this.publicBoard);
    }

    private void setTable(JTable table, ArrayList<ArrayList<Cell>> board) {
        DefaultTableModel model = createTableModel(board);
        table.setModel(model);
        setTableProperties(table, board);
    }

    private DefaultTableModel createTableModel(ArrayList<ArrayList<Cell>> board) {
        return new DefaultTableModel(board.size(), board.get(0).size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void setTableProperties(JTable table, ArrayList<ArrayList<Cell>> board) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        for (ArrayList<Cell> row : board) {
            for (Cell cell : row) {
                table.setValueAt(cell.getState(), cell.getX(), cell.getY());
            }
        }

        int cellSize = 50;
        table.setRowHeight(cellSize);

        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(cellSize);
        }
    }

    private void setMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (!view.consultarPonerBarcos()) {
                    System.out.println("No se puede poner barcos");
                    return;
                }

                System.out.println("Click at Cell:  " + row + " " + col + " " + table.getValueAt(row, col) + " Hecho por: " + jugador.getNombre());
                notifyObservers(new CellPoint(row, col, jugador));
                cantidadDEBarcosRestantes--;
                view.actualizarCantidadDeBarcos(cantidadDEBarcosRestantes);

                if (!view.consultarPonerBarcos()){
                    view.siguienteButton.setEnabled(true);
                }

            }
        });
    }

    private void setMouseListenerPublicBoard(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!view.turnoDelJugador().equals(view.getJugador())){
                    board.setEnabled(false);
                    return;
                }
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                System.out.println("Click at Cell:  " + row + " " + col + " " + table.getValueAt(row, col));
                //notifyObservers(new CeldaObserver);
                //table.setValueAt("M", row, col);
                Cell cell = new Cell(row, col, "M");
                CeldaObserver celdaObserver = new CeldaObserver( row, col, "", view.getJugador(),CeldaObserver.TipoCambio.GUESS);
                notifyObservers(celdaObserver);
            }
        });
    }

    private void setCellRenderer(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                switch (value.toString()) {
                    case "E":
                        c.setBackground(Color.GREEN);
                        break;
                    case "B":
                        c.setBackground(Color.orange);
                        break;
                    case "X":
                        c.setBackground(Color.BLUE);
                        break;
                    case "M":
                        c.setBackground(Color.YELLOW);
                        break;
                    default:
                        c.setBackground(Color.WHITE);
                        break;

                }

                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);


                return c;
            }
        });
    }

    public void setBoatAtCell(Cell cell2) {
        board.setValueAt(cell2.getState(), cell2.getX(), cell2.getY());
    }

    public void setGuessPublicBoard(Cell cell){
        publicBoard.setValueAt(cell.getState(), cell.getX(), cell.getY());
    }
}

