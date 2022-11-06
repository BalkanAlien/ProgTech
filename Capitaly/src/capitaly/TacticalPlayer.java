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
public class TacticalPlayer extends Player{
    
    private int numberOfBuys = 1; //this will be used to keep track of each bought property, we start with 1 initially 
    //constructor
    public TacticalPlayer(String name) {
        super(name, "tactical player");
    }
    
    //he skips each second chance when he could buy.
    @Override
    public void buyProperty(Property p) {
        if(this.money >= 1000 && this.numberOfBuys%2==1){
            this.money -= 1000;
            p.setOwner(this);
            ownedProperty.add(p);
            numberOfBuys++;
        }
    }

    @Override
    public void buildAHouse(Property p) {
        if(this.money >= 4000 && this.numberOfBuys%2==1){
            this.money -= 4000;
            p.buildAHouse();
            numberOfBuys++;
        }
    }
    
    
    


}
