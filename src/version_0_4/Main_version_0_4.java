package version_0_4;

import version_0_4.Controllers.Controller;
import version_0_4.Controllers.ViewController;
import version_0_4.Models.CoreModel;
import version_0_4.Views.DisplayView;

import javax.swing.*;

public class Main_version_0_4 {
    public static void main(String[] args) {

        // Creacion de variables.
        CoreModel coreModel = new CoreModel();
        DisplayView  displayView = new DisplayView();
        ViewController controller = new ViewController(displayView, coreModel, coreModel);


        //Display init
        displayView.setControllerObserver(controller);
        displayView.setController(controller);
        displayView.inicializar();

        JFrame  frame = new JFrame("Version 0.4");
        frame.setContentPane(displayView.PanelCarrusel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(Math.max(frame.getWidth(), 1100), Math.max(frame.getHeight(), 600));
        frame.setVisible(true);





    }

}
