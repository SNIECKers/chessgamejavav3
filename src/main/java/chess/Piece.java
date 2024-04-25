package chess;

import java.util.ArrayList;

public class Piece {
    private String color;
    private String name = "None";
    private int x;
    private int y;
    private boolean moved;
    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.moved = false;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isMoved() {
        return moved;
    }
    public void setMoved() {
        this.moved = true;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}

