package main;
import java.awt.Color;

//fields have colors, and can be spaceships or blackholes.
public class Field {
    
    private boolean spaceship;
    private boolean blackhole;
    private Color color;

    public Field() {
        spaceship = false;
        blackhole = false;
        color = null;
    }

    public boolean isItASpaceship() {
        return spaceship;
    }
    
    public void setSpaceship(boolean b){
        this.spaceship = b;
    }
    
    public boolean isItABlackhole(){
        return blackhole;
    }
    
    public void setBlackhole(){
        this.blackhole = true;
    }
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color c){
        this.color = c;
    }   
    
}