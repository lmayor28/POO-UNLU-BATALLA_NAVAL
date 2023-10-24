package version_0_2.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ViewPruebas {
    JFrame frame;

    JPanel panelMenuPrincipal;
    JPanel panelPrincipalJuegoSolitario;

    HashMap<String, JPanel> paneles;

    public ViewPruebas(){
        iniciarView();
    }

    private void iniciarView() {
        frame = new JFrame("Batalla Naval");
        frame.setSize(1000, 700);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        iniciarMenuPrincipal();

    }

    private void iniciarMenuPrincipal(){
        panelMenuPrincipal = new JPanel();
        frame.add(panelMenuPrincipal);
        panelMenuPrincipal.setVisible(true);

        panelMenuPrincipal.setLayout(new GridLayout(3,1));

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        JButton button = new JButton("Jugar Solo");
        JButton button2 = new JButton("Jugar contra Maquina");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMenuPrincipal.setVisible(false);
                iniciarJuegoSolo();
            }
        });




        panelMenuPrincipal.add(new JLabel("BATALLA NAVAL"));
        panelButtons.add(button2);
        panelButtons.add(button);
        panelMenuPrincipal.add(panelButtons);

    }

    private void iniciarJuegoSolo(){
        panelPrincipalJuegoSolitario = new JPanel();

        panelPrincipalJuegoSolitario.setLayout(new BorderLayout());
        panelPrincipalJuegoSolitario.add(new JLabel("JUGADOR 1"), BorderLayout.NORTH);
        panelPrincipalJuegoSolitario.setVisible(true);

        var button = getjButton();
        panelPrincipalJuegoSolitario.add(button, BorderLayout.WEST);

        frame.add(panelPrincipalJuegoSolitario);
    }

    private JButton getjButton() {
        JButton button = new JButton("Volver");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int response = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal?", "Confirmar", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION){
                    panelPrincipalJuegoSolitario.setVisible(false);
                    panelMenuPrincipal.setVisible(true);
                }
            }
        });
        return button;
    }

    private void funcionesPruebas(){



    }
}
