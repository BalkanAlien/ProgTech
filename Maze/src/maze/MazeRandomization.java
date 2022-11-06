/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Marija
 */
public class MazeRandomization {
    private final static int up = 0;  
    private final static int right = 1;  
    private final static int down = 2;  
    private final static int left = 3;  
      
    private final static int wall = 1;  
    private final static int emptySpot = 0;  
    private final static int blindSpot = -1;  
    private final static int path = 2;  

    private int width;  
    private int height;  
    private MazePoint[][] matrix;  
    private int[][] maze;  
      
    //constructs maze
    public MazeRandomization(int height, int width) {  
        this.width = width;  
        this.height = height;  
        this.matrix = new MazePoint[height][width];  
        for (int i=0; i<height; i++)  
            for (int j=0; j<width; j++)  
                matrix[i][j] = new MazePoint();  
        this.maze = new int[2*height+1][2*width+1];  //Construct mazes with a width of 2*height+1 and a length of 2*width+1
    }  
      
    //checks if neighboring fields are accessible or have already been visited
    public boolean isAccesible(int x, int y, int direction) {  
        boolean isNeighborVisited = false;  
        switch (direction) {  
        case up:  
            if ( x <= 0 )  
                isNeighborVisited = true;  
            else  
                isNeighborVisited = matrix[x-1][y].isVisited();  
            break;  
        case right:  
            if ( y >= width - 1 )  
                isNeighborVisited = true;  
            else  
                isNeighborVisited = matrix[x][y+1].isVisited();  
            break;  
        case down:  
            if ( x >= height - 1 )  
                isNeighborVisited = true;  
            else  
                isNeighborVisited = matrix[x+1][y].isVisited();  
            break;  
        case left:  
            if ( y <= 0 )  
                isNeighborVisited = true;  
            else  
                isNeighborVisited = matrix[x][y-1].isVisited();  
            break;  
        }  
        return !isNeighborVisited;  //If the dot is unvisited (isNeighborVisited is false), the return value is true, indicating that neighbors may be visited
    }  
      
    //check if at least one of the neighboring points are accessible
    public boolean isAccesible(int x, int y) {  
        return (this.isAccesible(x, y, up) || this.isAccesible(x, y, right) ||  
                this.isAccesible(x, y, down) || this.isAccesible(x, y, left));  
    }  
      
    
    //get accessible random direction
    public int getRandomDirection(int x, int y) {  
        int dir = -1;  
        Random rand = new Random();  
        if (isAccesible(x, y) ) {  
            do {  
                dir = rand.nextInt(4);  
            } while (!isAccesible(x, y, dir));  //The dir direction is not accessible
        }  
        return dir;  
    }  
      
    //sets accessible points as paths
    public void notAWall(int x, int y, int direction) {  
        switch(direction) {  
        case up:  
            matrix[x][y].setWallUp(false);  
            matrix[x-1][y].setWallDown(false); 
            break;  
        case right:  
            matrix[x][y].setWallRight(false);  
            matrix[x][y+1].setWallLeft(false);  
            break;  
        case down:  
            matrix[x][y].setWallDown(false);  
            matrix[x+1][y].setWallUp(false);  
            break;  
        case left:  
            matrix[x][y].setWallLeft(false);  
            matrix[x][y-1].setWallRight(false);  
            break;  
        }  
    }  
      
    
    public void traversal() {  
        int x = 0;  
        int y = 0;  
        Stack<Integer> stackX = new Stack<>();  
        Stack<Integer> stackY = new Stack<>();  
        do {  
            MazePoint p = matrix[x][y];  
            if ( !p.isVisited() ) {  
                p.setVisited(true);  
            }  
            if (isAccesible(x, y) ) {  
                int direction = this.getRandomDirection(x, y);  
                this.notAWall(x, y, direction);  
                stackX.add(x);  //Save coordinate information
                stackY.add(y);  //Save coordinate information
                switch (direction) {  
                case up:  
                    x--;  
                    break;  
                case right:  
                    y++;  
                    break;  
                case down:  
                    x++;  
                    break;  
                case left:  
                    y--;  
                    break;  
                }  
            }  
            else {  
                x = stackX.pop();  
                y = stackY.pop();  
            }  
        } while (!stackX.isEmpty());  
    }  
      
    //create Maze from the walls
    public void create() {  
        for (int j=0; j<2*width+1; j++)  
        	maze[0][j] = wall;         
        for (int i=0; i<height; i++) {  
            maze[2*i+1][0] = wall;  
            for (int j=0; j<width; j++) {  
                maze[2*i+1][2*j+1] = emptySpot;  
                if ( matrix[i][j].isWallRight() )  
                    maze[2*i+1][2*j+2] = wall;  
                else  
                    maze[2*i+1][2*j+2] = emptySpot;  
            }  
            maze[2*i+2][0] = 1;  
            for (int j=0; j<width; j++) {  
                if ( matrix[i][j].isWallDown() )  
                    maze[2*i+2][2*j+1] = wall;  
                else  
                    maze[2*i+2][2*j+1] = emptySpot;  
                maze[2*i+2][2*j+2] = wall;  
            }  
        }  
        maze[0][2*width] = emptySpot;
        maze[1][2*width] = emptySpot;
        maze[2*height - 1][0] = emptySpot;
        maze[2*height][0] = emptySpot;
    }  
    
    private void clean() {
        this.matrix = new MazePoint[height][width];  
        for (int i=0; i<height; i++)  
            for (int j=0; j<width; j++)  
                matrix[i][j] = new MazePoint();  
        this.maze = new int[2*height+1][2*width+1];  //Construct mazes with a width of 2*height+1 and a length of 2*width+1
    }
    
    public void creates() {
        this.create();
        int [][]temp = new int[2*height+1][2*width+1];
        for (int i = 0; i < 2 * height +1; i++) {
            System.arraycopy(maze[i], 0, temp[i], 0, 2 * width + 1);
        }
        
        this.clean();
        this.traversal(); 
        this.create();
        for (int i = 0; i < 2 * height +1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                if (temp[i][j] == emptySpot) {
                    maze[i][j] = temp[i][j];
                }
            }
        }
    }

   //get accessible directions
    public int getAccesibleDir(int x, int y) {  
        int direction = -1;  
        if ( maze[x][y+1] == 0 )  
            direction = right;  
        else if ( maze[x+1][y] == 0 )  
            direction = down;  
        else if ( maze[x][y-1] == 0 )  
            direction = left;  
        else if ( maze[x-1][y] == 0 )  
            direction = up;  
        return direction;  
    }  
      

    //find a path from (1,1) to (2*height-1, 2*width-1)
    public void findPath() {  
        int x = 1;  
        int y = 1;  
        Stack<Integer> stackX = new Stack<>();  
        Stack<Integer> stackY = new Stack<>();  
        do {  
            int direction = this.getAccesibleDir(x, y);  
            if ( direction == -1 ) {  
                maze[x][y] = blindSpot;  //the direction which doesn't exist is set as a blind spot
                x = stackX.pop();  
                y = stackY.pop();  
            }  
            else {  
                maze[x][y] = path;  
                stackX.add(x);  
                stackY.add(y);  //save path information
                switch (direction) {  
                case up:  
                    x--;  
                    break;  
                case right:  
                    y++;  
                    break;  
                case down:  
                    x++;  
                    break;  
                case left:  
                    y--;  
                    break;  
                }  
            }  
        } while ( !(x == 2*height-1 && y == 2*width-1) );  
        maze[x][y] = path;  
    }  
      
    //clear maze
    public void reset() {  
        for (int i=0; i<2*height+1; i++)  
            for (int j=0; j<2*width+1; j++)  
                if ( maze[i][j] != wall )  
                    maze[i][j] = emptySpot;  
    }  
    
    //check if it's a wall
    public boolean isWall(int i, int j) {
        return maze[i][j] == wall;
    }
}
