package main;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame; 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BlackholeGUI {
    private JFrame frame;
    private BoardGUI boardGUI;

    private final int starting_boardsize = 5;

//setting the frame of the game and the menus
    public BlackholeGUI() {
        frame = new JFrame("Blackhole Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardGUI = new BoardGUI(starting_boardsize);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Menu");
        menuBar.add(gameMenu);
        JMenu selectSizeMenu = new JMenu("Board Size");
        gameMenu.add(selectSizeMenu);
        int[] boardSizes = new int[]{5, 7, 9};
        for (int boardSize : boardSizes) {
            JMenuItem size = new JMenuItem(boardSize + "x" + boardSize);
            selectSizeMenu.add(size);
            size.addActionListener((ActionEvent e) -> {
                frame.getContentPane().remove(boardGUI.getBoardPanel());
                boardGUI = new BoardGUI(boardSize);
                frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
                frame.pack();
            });
        }
        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(exit);
        exit.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        
        frame.pack();
        frame.setVisible(true);
    }
}