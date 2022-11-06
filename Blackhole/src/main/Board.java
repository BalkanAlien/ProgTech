package main;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;



public class Board {
    private final Field[][] board;
    private final int boardSize;
    private final ArrayList<Point> upperSpaceships;//considering the ones on the upper part of the diagonal

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.upperSpaceships = new ArrayList<>();
        //setting the fields
        board = new Field[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Field();
            }
        }
        //setting the blackhole
        Field blackhole = getField(boardSize/2, boardSize/2);
        blackhole.setBlackhole();
        blackhole.setColor(Color.BLACK);    
        for(int i = 0; i < boardSize; i++){
            for(int j = i+1; j<boardSize; j++){
                if(i != boardSize/2 && j !=boardSize/2){
                upperSpaceships.add(new Point(i,j));
                }
            }
        }
        //randomizing
        Collections.shuffle(upperSpaceships); 
        //yellow spaceships in the upper part, red in lower part
        for (int i = 0; i < boardSize-1; i++) {
            Point p = upperSpaceships.get(i);
            Field yellowSpaceships = getField(p.x, p.y);
            yellowSpaceships.setSpaceship(true);
            yellowSpaceships.setColor(Color.YELLOW);         
            int X = boardSize-1 - p.x;
            int Y = boardSize-1 - p.y;
            Field redSpaceships = getField(X, Y);
            redSpaceships.setSpaceship(true);
            redSpaceships.setColor(Color.RED);
        }
    }
    
    public final Field getField(int x, int y) {
        return board[x][y];
    }
    
    public int getBoardSize() {
        return boardSize;
    }
}