/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Marija
 */
public class Player extends Character {
    private final int movement = 15;

    private final Image blackView = new ImageIcon("data/images/blackView.png").getImage();
    
    public Player(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
   
    //movement is defined by WASD
   public void move(char newMove, ArrayList<Brick> brick) {
        switch (newMove) {
            case 'U':
                y =y- movement ;
                if(collides(brick)||y<=0)
                {
                    y = y + movement;
                }   break;
            case 'D':
                y = y + movement;
                if(collides(brick)||y >=590)
                {
                    y =y-movement ;
                }
                break;
            case 'L':
                x = x-movement;
                if(collides(brick)||x <=0)
                {
                    x = x+movement;
                }
                break;
            case 'R':
                x = x+movement;
                if(collides(brick)||x >=590)
                {
                    x = x-movement;
                }
                break;
            default:
                break;
        }

    }
   
   //if player overlaps another character
   @Override
    public boolean collidesWith(Character other) {
        Rectangle rect = new Rectangle(x, y, width/2, height/2);
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);        
        return rect.intersects(otherRect);
    }
    
    //if player collides with brick
   public boolean collides(ArrayList<Brick> bricks)
   {
        for (Brick brick : bricks) {
            if (this.collidesWith(brick)) {
                return true;
            }
        }
       return false;
   }
 
 
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x-(width/2), y-(height/2), width, height, null);
        g.drawImage(blackView, x-600, y-600, 1200, 1200, null);
    }

}
