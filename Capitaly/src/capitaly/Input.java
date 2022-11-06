/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;
import java.io.*;
import java.util.*;
/**
 *
 * @author marij
 */
public class Input {
    private final ArrayList<Field>fields;
    private final ArrayList<Player>players;
    private final ArrayList<Integer>diceRolls;
    
    private int totalFields;
    private int totalPlayers;
    
    public Input(){
        fields = new ArrayList<>();
        players = new ArrayList<>();
        diceRolls = new ArrayList<>();
    }
    
    public void readInput(String fileName) throws FileNotFoundException, InvalidInputException {
       Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
       totalFields = sc.nextInt();
       for(int i=0; i<totalFields; i++){
           Field field;
           String type = sc.next();
           switch(type){
               case "Property":
                   field = new Property();
                   break;
               case "Service":
                   field = new Service(sc.nextInt());
                   break;
               case "LuckyField":
                   field = new LuckyField(sc.nextInt());
                   break;
               default:
                   throw new InvalidInputException();
           }
           fields.add(field);
       }
       totalPlayers = sc.nextInt();
       for(int i=0; i<totalPlayers; i++){
           String name = sc.next();
           Player player;
           String strategy = sc.next();
           switch(strategy){
               case "tactical":
                   player = new TacticalPlayer(name);
                   break;
               case "greedy":
                   player = new GreedyPlayer(name);
                   break;
               case "careful":
                   player = new CarefulPlayer(name);
                   break;
               default:
                   throw new InvalidInputException();
           }
           players.add(player);
       }
       while(sc.hasNextInt()){
           diceRolls.add(sc.nextInt());
       }
    }

       
       public void StartGame() {
           int diceIndex = 0;
           while(diceIndex < diceRolls.size() && totalAlivePlayers()>1){
               for(Player p : players){
                   int steps = diceRolls.get(diceIndex);
                   if(!p.isAlive()){
                       //the player loses, his properties become free
                       for(Field f : p.ownedProperty){
                           f.getFree();
                       }
                   }
                   else{//if the player hasn't lost, he continues to play
                       p.rollDiceGoForward(steps, totalFields);
                       Field field = fields.get(p.getIndexPosition());
                       field.getSteppedOn(p);

               }
           }
               diceIndex++;
       }
           for(Player p : players){
               if(p.isAlive()){
                   System.out.println("The winner is: " + p);
               }
           }
}
       
       
       public int totalAlivePlayers(){
           int cnt=0;
           for(Player p : players){
               if(p.isAlive()){
                   cnt++;
               }
           }
           return cnt;
       }
}
