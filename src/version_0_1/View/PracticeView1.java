package version_0_1.View;

import javax.swing.*;
import java.awt.*;

public class PracticeView1 {
    JFrame jFrame = new JFrame();
    int WIDTH = 1000;
    int HEIGHT = 800;

    public PracticeView1(){
        iniciarView();
    }

    private void iniciarView() {
        jFrame.setSize(400,400);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Practica de Vistas 1");
        jFrame.add(new JLabel("Practice version_0_1.View 1"));

        iniciarMenu();

        inicarPanel();







        /*
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);

        JButton button = new JButton("FILE");
        JButton button2 = new JButton("HELP");

        JPanel panel = new JPanel();
        panel.setLayout(flowLayout);
        panel.add(button);
        panel.add(button2);

        BorderLayout layout = new BorderLayout();
        jFrame.setLayout(layout);
        */
    }

    private void inicarPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("");
        label.setSize(WIDTH, 400);




        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);
        panel.add(label,  BorderLayout.NORTH);



    }

    private void iniciarMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("FILE");
        JMenu mHelp = new JMenu("HELP");

        menuBar.add(mFile);
        menuBar.add(mHelp);

        JMenuItem mItemGuardar = new JMenuItem("Guardar");
        JMenuItem MItemAbrir = new JMenuItem("Abrir");

        mFile.add(mItemGuardar);
        mFile.add(MItemAbrir);


        jFrame.setJMenuBar(menuBar);
    }
}
