/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Marija
 */
public class MazeGUI {
    private static JFrame frame;
    private GameEngine gameArea;

    public MazeGUI() {
        frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMenu();
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        
        frame.setPreferredSize(new Dimension(600,650));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        try {
            GameRank.loadData("data/data.dat");
        } catch (IOException e) {
            
        }
        
    }
    
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu menuGame = new JMenu("Game(G)");
        menuGame.setMnemonic('G'); 
        menuBar.add(menuGame);
        
        JMenuItem itemNew = new JMenuItem("New");
        menuGame.add(itemNew);
        
        JMenuItem itemRank = new JMenuItem("Rank");
        menuGame.add(itemRank);
        
        JMenuItem itemExit = new JMenuItem("Exit");
        menuGame.add(itemExit);
       
        ActionListener menuListener;
        menuListener = e -> {
            String cmd = e.getActionCommand();
            if (cmd.equals("Exit")) {
                System.exit(0);
            } else if(cmd.equals("Rank")) {
                String data = GameRank.getRank();
                JOptionPane.showConfirmDialog(frame, data, "GameRank", 0);
            } else if (cmd.equals("New")) {
                gameArea.restart();
            }
        };
        itemNew.addActionListener(menuListener);
        itemRank.addActionListener(menuListener);
        itemExit.addActionListener(menuListener);
    }
    
    public static String getPlayerName() {
        String inputContent = JOptionPane.showInputDialog(
                        frame,
                        "Enter your name:"
                    );
        return inputContent;
    }
}
