package chess;

public class King extends Piece {
    private String name = "King";
    public King(String color, int x, int y) {
        super(color, x, y);
        setName("King");
    }
}
