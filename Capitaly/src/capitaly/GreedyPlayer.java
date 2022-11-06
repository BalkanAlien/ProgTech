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
public class GreedyPlayer extends Player{
    
    //constructor
    public GreedyPlayer(String name) {
        super(name, "greedy player");
    }

    //If he steps on an unowned property, he
    //starts buying it, if he has enough money for it. 
    @Override
    public void buyProperty(Property p) {
      if(this.money>=1000){
          this.money -= 1000;
          p.setOwner(this);
          ownedProperty.add(p);
      }
    }
    
    //If he steps on his own property without a house, he
    //starts buying it, if he has enough money for it.

    @Override
    public void buildAHouse(Property p) {
        if(this.money>=4000){
            this.money -= 4000;
            p.buildAHouse();
        }
    }
    
    
}
