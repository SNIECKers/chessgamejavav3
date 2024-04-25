package chess;



import static java.lang.Math.abs;

public class Chessboard {
    Piece[][] board;
    int sizex;
    int sizey;
    int move;
    Piece selectedPiece;
    public Chessboard() {
        move = 0;
        sizex = 8;
        sizey = 8;
        board = new Piece[8][8];
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Piece("None", i, j);
                board[i][j].setName("None");
            }
        }
        for(int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("White", 1, i);
            board[6][i] = new Pawn("Black", 6, i);
        }
        board[0][0] = new Rook("White", 0, 0);
        board[7][0] = new Rook("Black", 7, 0);
        board[0][1]= new Knight("White", 0 , 1);
        board[7][1] = new Knight("Black", 7, 1);
        board[0][2] = new Bishop("White", 0 , 2);
        board[7][2] = new Bishop("Black", 7, 2);
        board[0][3] = new Queen("White", 0, 3);
        board[7][3] = new Queen("Black", 7, 3);
        board[0][4] = new King("White", 0, 4);
        board[7][4] = new King("Black", 7, 4);
        board[0][5] = new Bishop("White", 0 , 5);
        board[7][5] = new Bishop("Black", 7, 5);
        board[0][6] = new Knight("White", 0, 6);
        board[7][6] = new Knight("Black", 7, 6);
        board[0][7] = new Rook("White", 0, 7);
        board[7][7] = new Rook("Black", 7, 7);

        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                System.out.print(piece.getName() + piece.getColor() + " ");
            }
            System.out.print("\n");
        }
    }
    public String whoTurn(){
        if(move % 2 == 0){
            return "White";
        }
        else{
            return "Black";
        }
    }
    public boolean selectPiece(int x, int y) {
        if(board[x][y].getColor().equals(whoTurn())){
            selectedPiece = board[x][y];
            return true;
        }
        else{
            return false;
        }
    }
    private short diagonalCheckTrace(Piece piece){
        if(piece.getColor().equals("None")){
            return 0;
        }
        if(piece.getColor().equals(whoTurn())){
            return -1;
        }
        else if(piece.getName().equals("Bishop") ||
                piece.getName().equals("Queen")){
            return 1;
        }
        else if(piece.getName().equals("Pawn") ||
                piece.getName().equals("Rook") ||
                piece.getName().equals("King") ||
                piece.getName().equals("Knight")){
            return -1;
        }
        return 0;
    }
    private short verticalHorizontalCheckTrace(Piece piece){
        if(piece.getColor().equals("None")){
            return 0;
        }
        if(piece.getColor().equals(whoTurn())){
            return -1;
        }
        else if(piece.getName().equals("Rook") ||
                piece.getName().equals("Queen")){
            return 1;
        }
        else if(piece.getName().equals("Pawn") ||
                piece.getName().equals("Bishop") ||
                piece.getName().equals("King") ||
                piece.getName().equals("Knight")){
            return -1;
        }
        return 0;
    }
    private int checkKingCheck(){
        int posx = -1;
        int posy = -1;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j].getColor() + board[i][j].getName() + " " );
                if(board[i][j].getColor().equals(whoTurn()) && board[i][j].getName().equals("King")){
                    posx = i;
                    posy = j;
                    break;
                }
            }
            System.out.print("\n");
        }
        return checkCellCheck(posx, posy);
    }
    private short knightCheckTrace(Piece piece){
        if(piece.getColor().equals("None")){
            return 0;
        }
        if(piece.getColor().equals(whoTurn())){
            return -1;
        }
        else if(piece.getName().equals("Knight")){
            return 1;
        }
        else if(piece.getName().equals("Pawn") ||
                piece.getName().equals("Bishop") ||
                piece.getName().equals("King") ||
                piece.getName().equals("Queen") ||
                piece.getName().equals("Rook")){
            return -1;
        }
        return 0;

    }
    private int checkCellCheck(int posx, int posy){
        int numberofchecks = 0;

        if(posx == -1 || posy == -1){
            return numberofchecks;
        }
        else{
            int checkposx = posx;
            int checkposy = posy;
            //Checking vertical for checks
            while(checkposx < sizex -1){
                checkposx++;
                Piece piece = board[checkposx][posy];
                short decision = verticalHorizontalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            checkposx = posx;
            while(checkposx > 0){
                checkposx--;
                Piece piece = board[checkposx][posy];
                short decision = verticalHorizontalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            //Checking horizontal for checks
            while(checkposy < sizey - 1){
                checkposy++;
                Piece piece = board[posx][checkposy];
                short decision = verticalHorizontalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            checkposy = posy;
            while(checkposy > 0){
                checkposy--;
                Piece piece = board[posx][checkposy];
                short decision = verticalHorizontalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            //Checking diagonals for checks
            checkposy = posy;
            checkposx = posx;
            while(checkposy < sizey - 1 && checkposx < sizex - 1){
                checkposx++;
                checkposy++;
                Piece piece = board[checkposx][checkposy];
                short decision = diagonalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            checkposx = posx;
            checkposy = posy;
            while(checkposx > 0 && checkposy > 0){
                checkposx--;
                checkposy--;
                Piece piece = board[checkposx][checkposy];
                short decision = diagonalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            checkposx = posx;
            checkposy = posy;
            while(checkposx > 0 && checkposy < sizey - 1){
                checkposx--;
                checkposy++;
                Piece piece = board[checkposx][checkposy];
                short decision = diagonalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            checkposx = posx;
            checkposy = posy;
            while (checkposx < sizex - 1 && checkposy > 0){
                checkposx++;
                checkposy--;
                Piece piece = board[checkposx][checkposy];
                short decision = diagonalCheckTrace(piece);
                if(decision == 1){
                    numberofchecks++;
                    break;
                } else if (decision == -1) {
                    break;
                }
            }
            //checking knight checks
            if(posx + 2 < sizex && posy + 1 < sizey){
                short decision  = knightCheckTrace(board[posx + 2][posy + 1]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx + 1 < sizex && posy + 2 < sizey){
                short decision  = knightCheckTrace(board[posx + 1][posy + 2]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx - 2 >= 0 && posy + 1 < sizey){
                short decision  = knightCheckTrace(board[posx - 2][posy + 1]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx - 1 >= 0 && posy + 2 < sizey){
                short decision  = knightCheckTrace(board[posx - 1][posy + 2]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx + 2 < sizex && posy - 1 >= 0){
                short decision  = knightCheckTrace(board[posx + 2][posy - 1]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx + 1 < sizex && posy - 2 >= 0){
                short decision  = knightCheckTrace(board[posx + 1][posy - 2]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx - 1 >= 0 && posy - 2 >= 0){
                short decision  = knightCheckTrace(board[posx - 1][posy - 2]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            if(posx - 2 >= 0 && posy - 1 >= 0){
                short decision  = knightCheckTrace(board[posx - 2][posy - 1]);
                if(decision == 1){
                    numberofchecks++;
                }
            }
            //checking pawn checks
            if(whoTurn().equals("White")){
                if(posx - 1 >= 0 && posy + 1 < sizey){
                    Piece piece = board[posx + 1][posy + 1];
                    if(piece.getName().equals("Pawn") && piece.getColor().equals("Black")){
                        numberofchecks++;
                    }
                }
                if(posx + 1 < sizex && posy - 1 >= 0){
                    Piece piece = board[posx + 1][posy - 1];
                    if(piece.getName().equals("Pawn") && piece.getColor().equals("Black")){
                        numberofchecks++;
                    }
                }
            }
            if(whoTurn().equals("Black")){
                if(posx + 1 >= 0 && posy + 1 < sizey){
                    Piece piece = board[posx - 1][posy + 1];
                    if(piece.getName().equals("Pawn") && piece.getColor().equals("White")){
                        numberofchecks++;
                    }
                }
                if(posx + 1 < sizex && posy - 1 >= 0){
                    Piece piece = board[posx - 1][posy - 1];
                    if(piece.getName().equals("Pawn") && piece.getColor().equals("White")){
                        numberofchecks++;
                    }
                }
            }
            return numberofchecks;
        }

    }
    public boolean moveTo(int x, int y){
        boolean castle = false;
        boolean long_castle = false;
        if(selectedPiece == null){
            return false;
        }
        if(x < 0 || x > sizex - 1 || y < 0 || y > sizey - 1){
            return false;
        }
        else if(selectedPiece.getX() == x && selectedPiece.getY() == y){
            return false;
        }
        else if(!selectedPiece.getColor().equals(whoTurn())){
            return false;
        }
        else if(checkKingCheck() > 1){
            return false;
        }
        else{
            if(selectedPiece.getName().equals("Pawn")){
                if(x < selectedPiece.getX() && selectedPiece.getColor().equals("White")){
                    return false;
                }
                else if(x > selectedPiece.getX() && selectedPiece.getColor().equals("Black")){
                    return false;
                }
                if(2 < x - selectedPiece.getX() && !selectedPiece.isMoved() && selectedPiece.getColor().equals("White")){
                    return false;
                }
                else if(2 < selectedPiece.getX() - x && !selectedPiece.isMoved() && selectedPiece.getColor().equals("Black")){
                    return false;
                }
                else if(x - selectedPiece.getX() > 1 && selectedPiece.getColor().equals("White") && selectedPiece.isMoved()){
                    return false;
                }
                else if (selectedPiece.getX() - x > 1 && selectedPiece.getColor().equals("Black") && selectedPiece.isMoved()) {
                    return false;
                }
                if(abs(selectedPiece.getY() - y ) > 1 ){
                    return false;
                }
                if (x == selectedPiece.getX() + 1 && 1 == abs(selectedPiece.getY() - y) && selectedPiece.getColor().equals("White")) {
                    System.out.println("what");
                    if (board[x][y].getName().equals("None") || board[x][y].getColor().equals("White")) {
                        System.out.println("what");
                        return false;
                    }
                }
                if(x == selectedPiece.getX() - 1 && 1 == abs(selectedPiece.getY() - y) && selectedPiece.getColor().equals("Black")) {
                    if (board[x][y].getName().equals("None") || board[x][y].getColor().equals("Black")) {
                        return false;
                    }
                } else if (!board[x][y].getName().equals("None")) {
                    return false;
                }
                if(x == selectedPiece.getX() && board[x][y].getColor().equals(whoTurn())){
                    return false;
                }
            }
            if(selectedPiece.getName().equals("Knight")){
                if(x == selectedPiece.getX() + 1 && y == selectedPiece.getY() + 2){
                    if(!board[x][y].getName().equals("None")){
                        if (!board[x][y].getColor().equals("None")) {
                            return false;
                        }
                    }
                }

                else if(x == selectedPiece.getX() + 2 && y == selectedPiece.getY() + 1){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX() - 1 && y == selectedPiece.getY() + 2){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX() - 2 && y == selectedPiece.getY() + 1){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX() + 1 && y == selectedPiece.getY() - 2){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX() + 2 && y == selectedPiece.getY() - 1){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX() - 1 && y == selectedPiece.getY() - 2){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())) {
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX() - 2 && y == selectedPiece.getY() - 1){
                    if(!board[x][y].getName().equals("None")){
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }

            }
            if(selectedPiece.getName().equals("Rook")){
                if (y == selectedPiece.getY()){
                    if(x > selectedPiece.getX()){
                        for(int i = selectedPiece.getX() + 1; i < x - 1; i++){
                            if(!board[i][y].getName().equals("None") || board[i][y].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(x < selectedPiece.getX()){
                        for(int i = x + 1; i < selectedPiece.getX() - 1; i++){
                            if(!board[i][y].getName().equals("None") || board[i][y].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX()){
                    if(y > selectedPiece.getY()){
                        for(int i = selectedPiece.getY() + 1; i < y - 1; i++){
                            if(!board[x][i].getName().equals("None") || board[x][i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(y < selectedPiece.getY()){
                        for(int i = y + 1; i < selectedPiece.getY() - 1; i++){
                            if(!board[x][i].getName().equals("None") || board[x][i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
            if(selectedPiece.getName().equals("Bishop")){
                if(selectedPiece.getX() + selectedPiece.getY() == x + y){
                    if(selectedPiece.getX() < x){
                        for(int i = 1 ; i <= x - selectedPiece.getX() - 1; i++){
                            if(!board[selectedPiece.getX() + i][selectedPiece.getY() - i].getName().equals("None") || board[selectedPiece.getX() + i][selectedPiece.getY() - i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(selectedPiece.getX() > x){
                        for(int i = 1 ; i <= selectedPiece.getX() - x - 1; i++){
                            if(!board[selectedPiece.getX() - i][selectedPiece.getY() + i].getName().equals("None") || board[selectedPiece.getX() - i][selectedPiece.getY() + i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(selectedPiece.getX() - x == selectedPiece.getY() - y){
                    if(selectedPiece.getX() < x){
                        for(int i = 1 ; i <= x - selectedPiece.getX() - 1; i++){
                            if(!board[selectedPiece.getX() + i][selectedPiece.getY() + i].getName().equals("None") || board[selectedPiece.getX() + i][selectedPiece.getY() + i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(selectedPiece.getX() > x){
                        for(int i = 1 ; i <= selectedPiece.getX() - x - 1; i++){
                            if(!board[selectedPiece.getX() - i][selectedPiece.getY() - i].getName().equals("None") || board[selectedPiece.getX() - i][selectedPiece.getY() - i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
            if(selectedPiece.getName().equals("Queen")){
                if(selectedPiece.getX() + selectedPiece.getY() == x + y){
                    if(selectedPiece.getX() < x){
                        for(int i = 1 ; i <= x - selectedPiece.getX() - 1; i++){
                            if(!board[selectedPiece.getX() + i][selectedPiece.getY() - i].getName().equals("None") || board[selectedPiece.getX() + i][selectedPiece.getY() - i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(selectedPiece.getX() > x){
                        for(int i = 1 ; i <= selectedPiece.getX() - x - 1; i++){
                            if(!board[selectedPiece.getX() - i][selectedPiece.getY() + i].getName().equals("None") || board[selectedPiece.getX() - i][selectedPiece.getY() + i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(selectedPiece.getX() - x == selectedPiece.getY() - y){
                    if(selectedPiece.getX() < x){
                        for(int i = 1 ; i <= x - selectedPiece.getX() - 1; i++){
                            if(!board[selectedPiece.getX() + i][selectedPiece.getY() + i].getName().equals("None") || board[selectedPiece.getX() + i][selectedPiece.getY() + i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(selectedPiece.getX() > x){
                        for(int i = 1 ; i <= selectedPiece.getX() - x - 1; i++){
                            if(!board[selectedPiece.getX() - i][selectedPiece.getY() - i].getName().equals("None") || board[selectedPiece.getX() - i][selectedPiece.getY() - i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if (y == selectedPiece.getY()){
                    if(x > selectedPiece.getX()){
                        for(int i = selectedPiece.getX() + 1; i < x - 1; i++){
                            if(!board[i][y].getName().equals("None") || board[i][y].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(x < selectedPiece.getX()){
                        for(int i = x + 1; i < selectedPiece.getX() - 1; i++){
                            if(!board[i][y].getName().equals("None") || board[i][y].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else if(x == selectedPiece.getX()){
                    if(y > selectedPiece.getY()){
                        for(int i = selectedPiece.getY() + 1; i < y - 1; i++){
                            if(!board[x][i].getName().equals("None") || board[x][i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                    else if(y < selectedPiece.getY()){
                        for(int i = y + 1; i < selectedPiece.getY() - 1; i++){
                            if(!board[x][i].getName().equals("None") || board[x][i].getColor().equals(whoTurn())){
                                return false;
                            }
                        }
                        if(board[x][y].getColor().equals(whoTurn())){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
            if(selectedPiece.getName().equals("King")){
                if((abs(selectedPiece.getX() - x) < 2 && abs(selectedPiece.getY() - y) < 2) || (selectedPiece.getX() == x && abs(selectedPiece.getY() - y) == 2)){
                    if(selectedPiece.getX() == x && selectedPiece.getY() - y == 2){
                        if(selectedPiece.isMoved()){
                            return false;
                        }
                        if(checkCellCheck(selectedPiece.getX(), selectedPiece.getY() - 1) > 0){
                            return false;
                        }
                        if(checkCellCheck(selectedPiece.getX(), selectedPiece.getY() - 2) > 0){
                            return false;
                        }
                        if(!board[selectedPiece.getX()][0].getName().equals("Rook") || board[selectedPiece.getX()][0].isMoved()){
                            return false;
                        }
                        castle = true;
                    }
                    else if(selectedPiece.getX() == x && selectedPiece.getY() - y == -2){
                        if(selectedPiece.isMoved()){
                            return false;
                        }
                        if(checkCellCheck(selectedPiece.getX(), selectedPiece.getY() + 1) > 0){
                            return false;
                        }
                        if(checkCellCheck(selectedPiece.getX(), selectedPiece.getY() + 2) > 0){
                            return false;
                        }
                        if(checkCellCheck(selectedPiece.getX(), selectedPiece.getY() + 3) > 0){
                            return false;
                        }
                        if(!board[selectedPiece.getX()][7].getName().equals("Rook") || board[selectedPiece.getX()][0].isMoved()){
                            return false;
                        }
                        long_castle = true;
                    }
                    if(board[x][y].getColor().equals(whoTurn())){
                        return false;
                    }

                }
                else{
                    return false;
                }
            }
            //board[selectedPiece.getX()][selectedPiece.getY()];
            int oldx = selectedPiece.getX();
            int oldy = selectedPiece.getY();
            board[selectedPiece.getX()][selectedPiece.getY()] = new Piece("None", selectedPiece.getX(), selectedPiece.getY());
            selectedPiece.setX(x);
            selectedPiece.setY(y);
            Piece prevPiece = board[x][y];
            board[x][y] = selectedPiece;
            if(checkKingCheck() > 0){
                board[x][y] = prevPiece;
                selectedPiece.setX(oldx);
                selectedPiece.setY(oldy);
                board[oldx][oldy] = selectedPiece;
                return false;
            }
            board[oldx][oldy]= new Piece("None", oldx, oldy);
            selectedPiece.setMoved();

            move++;
            if(castle){
                Piece rook = board[selectedPiece.getX()][0];
                board[selectedPiece.getX()][0] = new Piece("None", oldx, oldy);
                board[selectedPiece.getX()][3] = rook;
            }
            if(long_castle){
                Piece rook = board[selectedPiece.getX()][0];
                board[selectedPiece.getX()][7] = new Piece("None", oldx, oldy);
                board[selectedPiece.getX()][5] = rook;
            }
            selectedPiece = null;
            return true;
        }
    }
    public Piece[][] getBoard(){
        return board;
    }
    public Piece getSelectedPiece(){
        return selectedPiece;
    }
    public Piece getPiece(int x, int y){
        return board[x][y];
    }
}
