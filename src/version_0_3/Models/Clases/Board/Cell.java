package version_0_3.Models.Clases.Board;

public class Cell {

    int x;
    int y;

    static final String BOAT = "B";
    static final String EMPTY = " ";
    static final String MISS = "M";
    static final String HIT = "X";


    private String state = EMPTY;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, String state) {
        this(x, y);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
