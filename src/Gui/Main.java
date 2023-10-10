package Gui;
import View.*;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PracticeView1 view = new PracticeView1();
            }
        });


    }
}
