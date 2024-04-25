package chess;

public class Queen extends Piece {
    private String name = "Queen";
    public Queen(String color, int x, int y) {
        super(color, x, y);
        setName("Queen");
    }
}
