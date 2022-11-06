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
public class Service extends Field{
    
    private int cost;
    
    //constructor
    public Service(int cost){
        super("Service");
        this.cost = cost;
    }
    
    @Override
    public int getCost(){
        return cost;
    }
    

    @Override
    public void getFree(){
          throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public boolean isItOwned(){
          throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public void setOwner(Player p){
          throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public Player getOwner(){
          throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public void buildAHouse(){
          throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public boolean doesItHaveAHouse(){
          throw new UnsupportedOperationException("This operation is not supported.");
    }
    
    @Override
    public void getSteppedOn(Player p){
        p.stepsOnServiceField(this.getCost());
    }
}
