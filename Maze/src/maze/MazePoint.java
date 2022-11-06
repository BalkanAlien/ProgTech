/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. */

package maze;

/**
 *
 * @author Marija
 */

public class MazePoint {  

    private boolean visited = false;  
    private boolean wallUp = true;  
    private boolean wallRight = true;  
    private boolean wallDown = true;  
    private boolean wallLeft = true;  

      

    public boolean isVisited() {  
        return visited;  
    }  

    public void setVisited(boolean visited) {  
        this.visited = visited;  
    }  

    public boolean isWallUp() {  
        return wallUp;  
    }  

    public void setWallUp(boolean wallUp) {  
        this.wallUp = wallUp;  
    }  

    public boolean isWallRight() {  
        return wallRight;  
    }  

    public void setWallRight(boolean wallRight) {  
        this.wallRight = wallRight;  
    }  

    public boolean isWallDown() {  
        return wallDown;  
    }  

    public void setWallDown(boolean wallDown) {  
        this.wallDown = wallDown;  
    }  

    public boolean isWallLeft() {  
        return wallLeft;  
    }  

    public void setWallLeft(boolean wallLeft) {  
        this.wallLeft = wallLeft;  
    }  
}  
