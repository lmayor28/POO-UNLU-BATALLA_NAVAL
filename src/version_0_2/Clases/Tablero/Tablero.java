package version_0_2.Clases.Tablero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private final ArrayList<ArrayList<Cuadrilla>> tablero;
    private final int cantidadFilas;
    private final int cantidadColumnas;

    private int ANCHO_CUADRILLA = 50;
    private int ALTO_CUADRILLA = 50;

    private JTable table;


    public Tablero(int cantidadFilas, int cantidadColumnas) {
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;
        tablero = new ArrayList<>();
        crearTablero();

        String[] columnNames =  nombreColumnas();
        Object[][] data = new Object[tablero.size()][tablero.get(0).size()];

        for (int i = 0; i < tablero.size(); i++) {
            for (int j = 0; j < tablero.get(i).size(); j++) {
                data[i][j] = tablero.get(i).get(j).getIcon();
            }
        }

        DefaultTableModel model = new DefaultTableModel(data,columnNames){
            @Override
            public Class<?> getColumnClass(int column) {
                return ImageIcon.class;
            }
        };


        table = new JTable(model);
        table.setRowHeight(ALTO_CUADRILLA);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(ANCHO_CUADRILLA);
        }



        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.rowAtPoint(e.getPoint());
                int columna = table.columnAtPoint(e.getPoint());
                if (e.getClickCount() == 1) {
                    Cuadrilla cuadrilla = tablero.get(fila).get(columna);
                    // Accede a los datos de la cuadrilla y haz algo con ellos
                    cuadrilla.click(fila,columna);
                }
            }
        });

    }

    private String[] nombreColumnas(){
        String[] columnas = new String[cantidadColumnas];

        for (int i = 0; i < cantidadColumnas; i++) {
            columnas[i] = "Columna " + i;
        }

        return columnas;
    }

    private void crearTablero(){
        for (int i = 0; i < cantidadFilas; i++) {
            tablero.add(new ArrayList<>());
            for (int j = 0; j < cantidadColumnas; j++) {
                tablero.get(i).add(new Cuadrilla(ANCHO_CUADRILLA, ALTO_CUADRILLA));
            }
        }
    }

    public JTable getTable() {
        return this.table;
    }



    @Override
    public String toString() {
        String cadena = "";

        int contadorFila = 0;

        for (ArrayList<Cuadrilla> fila : tablero) {
            cadena += "Fila: " + contadorFila + " | ";
            contadorFila++;
            for (Cuadrilla cuadrilla : fila) {
                cadena += " | ";
                cadena += cuadrilla.toString();
            }
            cadena += " | \n";
        }


        return cadena;
    }
}
