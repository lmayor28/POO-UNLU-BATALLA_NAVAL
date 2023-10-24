package version_0_2.Gui;
import version_0_2.View.View;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
            }
        });


    }
}
