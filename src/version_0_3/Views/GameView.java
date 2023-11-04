package version_0_3.Views;

import version_0_3.Models.Clases.Board.Cell;
import version_0_3.Models.Clases.Board.CellPoint;
import version_0_3.Observer;
import version_0_3.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class GameView extends Subject {
    public JPanel panel1;
    private JTable BOARD;
    private JTable PUBLIC_BOARD;
    private JButton LoadBoard;


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

        PUBLIC_BOARD.setDefaultRenderer(Object.class, centerRenderer);
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

        //JScrollPane scrollPane = new JScrollPane(BOARD);
        //JScrollPane scrollPane2 = new JScrollPane(PUBLIC_BOARD);

        //BOARD.setFillsViewportHeight(true);
        //BOARD.setFillsViewportHeight(true);

        //PUBLIC_BOARD.setFillsViewportHeight(true);
        //PUBLIC_BOARD.setFillsViewportHeight(true);

        //scrollPane.setPreferredSize((new Dimension(800, 600)));
        //scrollPane2.setPreferredSize((new Dimension(800, 600)));

        PUBLIC_BOARD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = PUBLIC_BOARD.rowAtPoint(e.getPoint());
                int col = PUBLIC_BOARD.columnAtPoint(e.getPoint());
                System.out.println("Click at Cell:  " + row + " " + col + " " + PUBLIC_BOARD.getValueAt(row, col));
                notifyObservers(new CellPoint(row, col));
            }
        });


        // TODO: Ver que pasa con este codigo de porque no funciona y creo que se cambia el setDefaultRender en alguna otra parte.
        PUBLIC_BOARD.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (" ".equals(value)) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(table.getBackground());
                }

                return c;
            }
        });




        //BOARD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //PUBLIC_BOARD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }





    public void setButtonAction(ActionListener action) {
        LoadBoard.addActionListener(action);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here




    }


    public void update(Cell newCell){
        PUBLIC_BOARD.setValueAt(newCell.getState(), newCell.getX(), newCell.getY());

    }
}
