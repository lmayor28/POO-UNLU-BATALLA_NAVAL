package View;

import javax.swing.*;

public class View {
    JFrame frame;

    public View(){
        iniciarView();
    }

    private void iniciarView() {
        frame = new JFrame("Batalla Naval");
        frame.setSize(1000, 700);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    private void funcionesPruebas(){



    }

}
