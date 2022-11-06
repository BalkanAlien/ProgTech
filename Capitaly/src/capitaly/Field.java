/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

/**
 *
 * @author marij
 */
public abstract class Field {
    private final String type;
    
    //constructor
    public Field(String type){
        this.type=type;
    }
    
    //getters and setters
    public String getType() {
        return type;
    }
    
    public abstract boolean isItOwned();
    
    public abstract void setOwner(Player p);
    
    public abstract Player getOwner();
    
    public abstract boolean doesItHaveAHouse();
    
    public abstract void buildAHouse();
    
    public abstract int getCost();
    
    public abstract void getFree();
    
    public abstract void getSteppedOn(Player p);
    
}
