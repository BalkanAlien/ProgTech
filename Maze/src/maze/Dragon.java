/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
/**
 *
 * @author Marija
 */
public class Dragon extends Character {

    private double velocityX;
    private double velocityY; 
    private Random rand = new Random();
    private int movement = 3;
    private int n=0;

    public Dragon(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velocityX = 1;
        velocityY = 1;
    }

    //if the dragon collides with a brick or a player or the distance between the dragon and the player < 30
    //we change the dragon's position randomly
    public void random(ArrayList<Brick> brick, Player player)
    {
        while(this.collides(brick)||this.collidesWith(player)||Math.abs(this.x - player.x) < 30||Math.abs(this.y - player.y) < 30)

        {
            x = rand.nextInt(600);
            y = rand.nextInt(600);
        }
             
    }

    //defining the dragon's movement
    public void move(ArrayList<Brick> brick) {
        
        int r = rand.nextInt(4);
        switch (n) {
            case 0:
                x = x-movement;
                if(collides(brick)||x <=0)
                {
                    x = x+movement;
                    n=r;
                }
                break;
            case 1:
                y =y-movement ;
                if(collides(brick)||y<=0)
                {
                    y = y + movement;
                    n=r;
                }
                break;
            case 2:
                y = y + movement;
                if(collides(brick)||y >=570)
                {
                    y =y-movement ;
                    n=r;
                }
                break;
            case 3:
                x = x+movement;
                if(collides(brick)||x >=570)
                {
                    x = x-movement;
                    n=r;
                }
                break;
            default:
                break;
        }

    }
   
   //checking whether it collides with a brick
   public boolean collides(ArrayList<Brick> bricks)
   {
        for (Brick brick : bricks) {
            if (this.collidesWith(brick)) {
                return true;
            }
        }
       return false;
   }
    

    
    public void invertVelocityX() {
        velocityX = rand.nextInt(4);
    }

    public void invertVelocityY() {
        velocityY = rand.nextInt(4);

    }
    
    
    
 


}
