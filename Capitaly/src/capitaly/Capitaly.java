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
 * @author Marija Turmakoska
 */
public class Capitaly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            Input input = new Input();
            Scanner sc = new Scanner(System.in);
            try{
                String fileName;
                System.out.println("Enter file name: ");
                fileName = sc.nextLine();
                input.readInput(fileName);
            } catch(FileNotFoundException e){
                System.out.println("File not found!");
                System.exit(-1);
            } catch(InvalidInputException e){
                System.out.println("Invalid input!");
                System.exit(-1);
            }
            
            input.StartGame();
      
    }
    
}
