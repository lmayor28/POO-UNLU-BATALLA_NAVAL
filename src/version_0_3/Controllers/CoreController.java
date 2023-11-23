package version_0_3.Controllers;

import version_0_3.Models.Clases.Board.Board;
import version_0_3.Models.Clases.Board.Cell;
import version_0_3.Models.Clases.Board.CellPoint;
import version_0_3.Observer;
import version_0_3.Views.GameView;

public class CoreController implements Observer {

    Board board;
    GameView view;

    public CoreController(Board board, GameView view) {
        this.board = board;
        this.view = view;
    }

    private void acutalizarVista(){

    }


    @Override
    public void update(Object arg) {
        if (arg instanceof CellPoint point) {
            //board.setCell(point.getX(), point.getY(), point.getValue());

            view.update(new Cell(point.getX(), point.getY(), board.guess(point.getX(), point.getY()) ));

        }
    }
}
