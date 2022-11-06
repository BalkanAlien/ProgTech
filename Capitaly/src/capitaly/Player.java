/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;
import java.util.*;
/**
 *
 * @author marij
 */
public class Player {
    String name, strategy;
    int money;
    ArrayList<Property>ownedProperty;
    int indexPosition;

    //constructor
    public Player(String name, String strategy){
        this.name = name;
        this.strategy = strategy;
        this.money = 10000;
        ownedProperty = new ArrayList<>();
        indexPosition = -1; //in real life scenario, this would be 0
    }
    
    
    //setters and getters
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    public void setStrategy(String strategy){
        this.strategy = strategy;
    }
    
    public String getStrategy(){
        return strategy;
    }
    
    public void setMoney(int money){
        this.money = money;
    }
    
    public int getMoney(){
        return money;
    }
   
    public void setIndexPosition(int indexPosition){
        this.indexPosition = indexPosition;
    }
    
    public int getIndexPosition(){
        return indexPosition;
    }
    
    //if i step on another player's property
    public void payThePlayer(int amount){
        this.money -= amount;
    }
    
    //if another player steps on my property
    public void getPaid(int amount){
        this.money += amount;
    }
    
    //When a player rolls the dice, he goes forward the number of steps he rolled, but we need to take care of the total number of fields (in case he needs to continue
    //stepping through the beginning of the fields)
    public void rollDiceGoForward(int rolledNumber, int numberOfTotalFields){
        this.indexPosition = (this.indexPosition + rolledNumber) % numberOfTotalFields;
    }
    
    //if his amount of money < 0, he lost
    
    public boolean isAlive(){
        return money>=0;
    }
    
    //if he steps on a lucky field
    public void stepsOnLuckyField(int amount){
        this.money += amount;
    }
    
    //if he steps on a service field
    public void stepsOnServiceField(int amount){
        this.money -= amount;
    }
    
    public void buyProperty(Property p) {}
    
    public void buildAHouse(Property p) {}
    
    //Print out player, and how rich he is (balance, owned properties)
    @Override
    public String toString(){
        int cnt = 0;
        for(Property p : ownedProperty) {
            cnt++;
        }
        
        return "Player " + name + " who is  a " + strategy + " has $" + money + " and owns " + cnt + " properties.";
    }
}
 