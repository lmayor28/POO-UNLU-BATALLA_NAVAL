package version_0_2.Clases.Tablero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;



public class Cuadrilla {

    private int contenido;
    private ImageIcon icon;


    public Cuadrilla(int ANCHO, int ALTO){

        String path = "src/version_0_2/img/BarcoHundido.jpeg";

//        switch (r.nextInt(0,2)){
//            case 0: path += "Barco.jpeg";
//            case 1: path += "BarcoHundido.jpeg";
//            case 2: path += "PiedraGigante.jpeg";
//            default: path += "Barco.jpeg";
//        }

        try{
            File file = new File(path);
            Image imagen = ImageIO.read(file);
            Image resizedImage = imagen.getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
            icon = new ImageIcon(imagen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    public ImageIcon getIcon(){
        return this.icon;
    }

    @Override
    public String toString(){
        return "" + contenido;
    }

    public void click(int i, int j) {
        System.out.println("Se ha echo click en la cuadrilla" + i + j);
    }
}
