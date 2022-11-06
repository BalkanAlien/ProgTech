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
public class CarefulPlayer extends Player{

    //constructor
    public CarefulPlayer(String name) {
        super(name, "careful player");
    }
    
    //he buys in a round only for at most half the amount of his money.
    //a property can be bought for 1000, so he will buy it if he has at least 2000
    @Override
    public void buyProperty(Property p) {
        if(this.money>=2000){
            this.money -= 1000;
            p.setOwner(this);
            ownedProperty.add(p);
        }
    }
    //building a house costs 4000, so he would only build a house if he hast
    //at least 8000
    @Override
    public void buildAHouse(Property p) {
        if(this.money>=8000){
            this.money -= 4000;
            p.buildAHouse();
        }
    }
    
    

    
}
