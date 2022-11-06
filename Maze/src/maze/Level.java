/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Marija
 */
public class Level {

    private final int brickWidth = 30;
    private final int brickHeight = 30;
    ArrayList<Brick> bricks;

    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }
    
    public ArrayList<Brick> getBrickLists()
    {
        return bricks;
    }
    
   //setting the bricks in the maze
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        bricks = new ArrayList<>();
        MazeRandomization maze = new MazeRandomization(30, 20);
        maze.traversal();  
        maze.creates();     
        for(int i = 0; i < 19 ;i++){
            for(int j = 0; j < 20 ; j++)
            {
                Image image = new ImageIcon("data/images/bbb.jpg").getImage();
                if (maze.isWall(i, j)) {
                    bricks.add(new Brick(i * brickWidth, j * brickHeight, brickWidth, brickHeight, image));
                }
            }
        }
    }
    
    //making sure the dragon doesn't hit a brick
    public boolean collides(Dragon dragon) {
        Brick collidedWith = null;
        for (Brick brick : bricks) {
            if (dragon.collidesWith(brick)) {
                collidedWith = brick;
                break;
            }
        }
        return collidedWith != null;
    }
    
    
    
   
    public void draw(Graphics g) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

}
