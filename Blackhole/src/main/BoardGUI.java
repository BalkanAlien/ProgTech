package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardGUI {
    
    private Board board;
    private final JButton[][] buttons;
    private final JPanel boardPanel;   
    private int redPlayers;
    private int yellowPlayers;
    private Point currentPoint;   
    private int turn;
 //adding the buttons and the players
    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        buttons = new JButton[boardSize][boardSize];
        redPlayers = boardSize-1;
        yellowPlayers = boardSize-1;
        currentPoint = null;
        turn = 0;
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.addKeyListener(new ArrowKeyListener());
                button.setPreferredSize(new Dimension(100, 100));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        update();
    }
    
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
//when game ends
    public boolean GameOver() {
        return redPlayers <= (board.getBoardSize()-1)/2 || yellowPlayers <= (board.getBoardSize()-1)/2;
    }
    
//start a new game
    public void restart(){
        board = new Board(board.getBoardSize());
        redPlayers = board.getBoardSize()-1;
        yellowPlayers = board.getBoardSize()-1;
        currentPoint = null;
        turn = 0;
        update();
    }
    
    
//updates the GUI
    public void update() {
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                Field field = board.getField(i, j);
                JButton button;
                button = buttons[i][j];
                if (!field.isItASpaceship() && !field.isItABlackhole()) {
                    button.setBackground(Color.WHITE);
                }
                else{
                    button.setBackground(field.getColor());
                }
            }
        }
        if (GameOver()) {
            JLabel gameOver = new JLabel(((redPlayers < yellowPlayers) ? "Red player" : "Yellow player") + " wins!");
            int result = JOptionPane.showConfirmDialog(boardPanel, gameOver, "Game over", JOptionPane.PLAIN_MESSAGE);
            if(result == 0) restart();
        }
    }
    
    class ButtonListener implements ActionListener {
        private final int x;
        private final int y;
        
        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(turn % 2 == 0){
                if (board.getField(x, y).isItASpaceship() && board.getField(x, y).getColor() == Color.RED) {
                    currentPoint = new Point(x, y);
                }
                else{
                    currentPoint = null;
                }
            }
            else{
                if (board.getField(x, y).isItASpaceship() && board.getField(x, y).getColor() == Color.YELLOW) {
                    currentPoint = new Point(x, y);
                }
                else{
                    currentPoint = null;
                }
            }
        }
    }
    public void ArrowUp(){
        int x = Math.max(currentPoint.x-1, 0);
                            int y = currentPoint.y;
                            while(!board.getField(x, y).isItASpaceship() && !board.getField(x, y).isItABlackhole() && x > 0){
                                x--;
                            }       board.getField(currentPoint.x,currentPoint.y).setSpaceship(false);
                            if(board.getField(x, y).isItABlackhole()){
                                if(board.getField(currentPoint.x, currentPoint.y).getColor() == Color.RED){
                                    redPlayers--;
                                }
                                else yellowPlayers--;
                            }
                            else{
                                if(board.getField(x, y).isItASpaceship()) x++;
                                if(x == currentPoint.x) turn--;
                                
                                board.getField(x, y).setSpaceship(true);
                                board.getField(x, y).setColor(board.getField(currentPoint.x,currentPoint.y).getColor());
                            }       currentPoint = null;
                            
    }
    public void ArrowDown(){
        int x = Math.min(currentPoint.x+1, board.getBoardSize()-1);
                            int y = currentPoint.y;
                            while(!board.getField(x, y).isItASpaceship() && !board.getField(x, y).isItABlackhole() && x < board.getBoardSize()-1){
                                x++;
                            }       board.getField(currentPoint.x,currentPoint.y).setSpaceship(false);
                            if(board.getField(x, y).isItABlackhole()){
                                if(board.getField(currentPoint.x, currentPoint.y).getColor() == Color.RED){
                                    redPlayers--;
                                }
                                else yellowPlayers--;
                            }
                            else{
                                if(board.getField(x, y).isItASpaceship()) x--;
                                if(x == currentPoint.x) turn--;
                                
                                board.getField(x, y).setSpaceship(true);
                                board.getField(x, y).setColor(board.getField(currentPoint.x,currentPoint.y).getColor());
                            }       currentPoint = null;
                            
    }
    public void ArrowLeft(){
         int x = currentPoint.x;
                            int y = Math.max(currentPoint.y-1, 0);
                            while(!board.getField(x, y).isItASpaceship() && !board.getField(x, y).isItABlackhole() && y>0){
                                y--;
                            }       board.getField(currentPoint.x,currentPoint.y).setSpaceship(false);
                            if(board.getField(x, y).isItABlackhole()){
                                if(board.getField(currentPoint.x, currentPoint.y).getColor() == Color.RED){
                                    redPlayers--;
                                }
                                else yellowPlayers--;
                            }
                            else{
                                if(board.getField(x, y).isItASpaceship()) y++;
                                if(y == currentPoint.y) turn--;
                                
                                board.getField(x, y).setSpaceship(true);
                                board.getField(x, y).setColor(board.getField(currentPoint.x,currentPoint.y).getColor());
                            }       currentPoint = null;
                            
    }
    public void ArrowRight(){
        int x = currentPoint.x;
                            int y = Math.min(currentPoint.y+1, board.getBoardSize()-1);
                            while(!board.getField(x, y).isItASpaceship() && !board.getField(x, y).isItABlackhole() && y < board.getBoardSize()-1){
                                y++;
                            }       board.getField(currentPoint.x,currentPoint.y).setSpaceship(false);
                            if(board.getField(x, y).isItABlackhole()){
                                if(board.getField(currentPoint.x, currentPoint.y).getColor() == Color.RED){
                                    redPlayers--;
                                }
                                else yellowPlayers--;
                            }
                            else{
                                if(board.getField(x, y).isItASpaceship()) y--;
                                if(y == currentPoint.y) turn--;
                                
                                board.getField(x, y).setSpaceship(true);
                                board.getField(x, y).setColor(board.getField(currentPoint.x,currentPoint.y).getColor());
                            }       currentPoint = null;
                            
    }
    class ArrowKeyListener implements KeyListener{
        
        @Override
        public void keyReleased(KeyEvent e) {
        }
            
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(currentPoint != null){
                    if(e.getKeyCode() == KeyEvent.VK_UP)
                    {
                        ArrowUp();
                    }           
                    if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    {
                        ArrowDown();
                    }
                    if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    {
                       ArrowLeft();

                    }
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    {
                       ArrowRight();
                    }
                turn++;  
                update();
            }
        }
    }
}
