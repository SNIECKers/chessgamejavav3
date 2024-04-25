package it22207.chessjava;
import chess.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardController {
    @FXML
    private GridPane board;
    @FXML
    Chessboard chessboard = new Chessboard();
    @FXML
    boolean pieceSelected = false;
    public void initialize() {
        updateImages();
    }
    public void updateImages() {
        board.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle square = new Rectangle(50, 50);
                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle(50, 50);
                ImageView imageView = null;
                if((i + j) % 2 == 1){
                    square.setFill(Color.WHITE);
                }
                else{
                    square.setFill(Color.BLACK);
                }
                int finalI = i;
                int finalJ = j;
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setOnMousePressed(event -> squareClicked(finalI, finalJ));
                rectangle.setOnMouseEntered(event -> squareEntered(finalI, finalJ));
                Piece piece = chessboard.getPiece(finalI, finalJ);
                if(chessboard.getPiece(i, j).getColor().equals("White")){
                    Image newImage = null;
                    if(piece.getName().equals("Pawn")){
                        newImage = new Image("file:src/main/resources/chesspieces/LightPawn.png");
                    }
                    else if(piece.getName().equals("Bishop")){
                        newImage = new Image("file:src/main/resources/chesspieces/LightBishop.png");
                    }
                    else if(piece.getName().equals("Knight")){
                        newImage = new Image("file:src/main/resources/chesspieces/LightKnight.png");
                    }
                    else if(piece.getName().equals("Queen")){
                        newImage = new Image("file:src/main/resources/chesspieces/LightQueen.png");
                    }
                    else if(piece.getName().equals("King")){
                        newImage = new Image("file:src/main/resources/chesspieces/LightKing.png");
                    }
                    else if(piece.getName().equals("Rook")){
                        newImage = new Image("file:src/main/resources/chesspieces/LightRook.png");
                    }
                    imageView = new ImageView(newImage);
                }
                else if(piece.getColor().equals("Black")){
                    Image newImage = null;
                    if(piece.getName().equals("Pawn")){
                        newImage = new Image("file:src/main/resources/chesspieces/DarkPawn.png");
                    }
                    else if(piece.getName().equals("Bishop")){
                        newImage = new Image("file:src/main/resources/chesspieces/DarkBishop.png");
                    }
                    else if(piece.getName().equals("Knight")){
                        newImage = new Image("file:src/main/resources/chesspieces/DarkKnight.png");
                    }
                    else if(piece.getName().equals("Queen")){
                        newImage = new Image("file:src/main/resources/chesspieces/DarkQueen.png");
                    }
                    else if(piece.getName().equals("King")){
                        newImage = new Image("file:src/main/resources/chesspieces/DarkKing.png");
                    }
                    else if(piece.getName().equals("Rook")){
                        newImage = new Image("file:src/main/resources/chesspieces/DarkRook.png");
                    }
                    imageView = new ImageView(newImage);

                }
                if (imageView != null) {
                    imageView.setRotate(90);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    stackPane.getChildren().addAll(square, imageView, rectangle);
                } else {
                    stackPane.getChildren().addAll(square,rectangle);
                }

                board.add(stackPane, finalI, finalJ);

            }
        }
        board.setRotate(270);
    }

    private void squareClicked(int row, int col) {
        System.out.println("Square clicked at row " + row + ", col " + col);
        pieceSelected = true;
        chessboard.selectPiece(row, col);
    }
    private void squareEntered(int row, int col) {
        if(pieceSelected){
            System.out.println("Square entered at row " + row + ", col " + col);
            pieceSelected = false;
            System.out.println(chessboard.moveTo(row, col));
            updateImages();
        }

    }

}
