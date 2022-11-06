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
public class Property extends Field{
    
    private Player owner;
    private boolean house;
    
    //constructor
    public Property() {
        super("Property");
        this.owner = null;
        this.house = false;
    }
    
    @Override
    public void setOwner(Player p){
        this.owner = p;
    }
    
    @Override
    public Player getOwner(){
        return owner;
    }
    
    //check if property has owner
    @Override
    public boolean isItOwned(){
        return this.owner!=null;
    }
    
    //check if property has house
    @Override
    public boolean doesItHaveAHouse(){
        return this.house==true;
    }
    
    @Override
    public void buildAHouse(){
        this.house = true;
    }
    
    //if the owner loses, the property becomes free
    @Override
    public void getFree(){
        this.owner = null;
    }
    

    
    @Override
    public int getCost(){
        throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public void getSteppedOn(Player p){
        if(!this.isItOwned()){
            p.buyProperty(this);
        }
        else if(this.getOwner().equals(p) && !this.doesItHaveAHouse()){
            p.buildAHouse(this);
        }
        else if(!this.doesItHaveAHouse()){
            p.payThePlayer(500);
            this.getOwner().getPaid(500);
        }
        else if(this.doesItHaveAHouse()){
            p.payThePlayer(2000);
            this.getOwner().getPaid(2000);
        }
    }
}
