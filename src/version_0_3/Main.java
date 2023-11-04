package version_0_3;

import version_0_3.Controllers.CoreController;
import version_0_3.Models.Clases.Board.Board;
import version_0_3.Models.Clases.Player;
import version_0_3.Views.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board(10,10, player);

        JFrame frame = new JFrame("Batalla Naval");
        GameView gameView = new GameView();

        //gameView.panel1.setSize(1200, 1000);
        frame.setContentPane(gameView.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(Math.max(frame.getWidth(), 1100), Math.max(frame.getHeight(), 600));
        frame.setVisible(true);

        //frame.setSize(1200, 1000);

        CoreController controller = new CoreController(board, gameView);

        gameView.attach(controller);

        gameView.setButtonAction(e -> {
            System.out.println("Hola ??");
            gameView.setBOARD(board.getBoard());
            frame.pack();
            frame.setSize(Math.max(frame.getWidth(), 1100), Math.max(frame.getHeight(), 600));
        });


    }
}
