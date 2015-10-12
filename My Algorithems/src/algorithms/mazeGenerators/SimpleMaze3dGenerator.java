package algorithms.mazeGenerators;

import java.util.Random;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> SimpleMaze3dGenerator </h1>
 * The SimpleMaze3dGenerator Class extends the abstract AbsMaze3dGenerator.
 * The class implement a Maze3d abstract generation method from the super class.
 * the implementation of the maze generation is done by me
 * during the implementation i used a several Random class in order to create dynamic maze.
 */

public class SimpleMaze3dGenerator extends AbsMaze3dGenerator {

//Constructors
	/**
	 * Instantiates a new  my own maze3d generator.
	 */
	public SimpleMaze3dGenerator() {
		// TODO Auto-generated constructor stub
	}
//Functionality
	@Override
	/**
	 * Generate a new search-able maze3d with given floors, lines and columns
	 *@param floor integer that represent the number of floors to set 
	 *@param lines integer that represent the number of lines to set
	 *@param cols integer that represent the number of columns to set
	 *@return Maze3d an instance, that contains the maze with the given floors, lines and columns          
	 */
	public Maze3d generate(int floors, int lines, int cols) {
		Maze3d maze3DToReturn = new Maze3d(floors, lines, cols);
		int[][][] mazeMatrix = maze3DToReturn.getMaze();
		Random random = new Random();
		
		for (int i=0;i<floors;i++)
		{
			for(int j=0;j<lines;j++)
			{
				for(int k=0;k<cols;k++)
				{
					if (mazeMatrix[i][j][k]==0){mazeMatrix[i][j][k] = random.nextInt(2);}
				}
			}
		}
		setValidPath(mazeMatrix,maze3DToReturn);
		maze3DToReturn.setMaze(mazeMatrix);
		maze3DToReturn.getStartPosition().setValue(0, maze3DToReturn);
		maze3DToReturn.getGoalPosition().setValue(0, maze3DToReturn);
		return maze3DToReturn;
	}
	/**
	 *This method goal is to make the maze valid
	 *@param maze int[][][] array represent the maze matrix
	 *@param obj Maze3d represent the domain.          
	 */
	public void setValidPath(int[][][] maze,Maze3d obj)
	{
		Random rand = new Random();
		int floors = maze.length;
		int lines = maze[0].length;
		int cols = maze[0][0].length;
		
		//randomized choosing of floor to start with creating a valid path
		int currentFloor = 0;
		int currentLine =0;
		int place =0;
		
		while (place==0)
		{
				place = rand.nextInt(cols-1)+1;
		}
		obj.setStartPosition(new Position(currentFloor,currentLine,place));
				
				for( currentFloor=0;currentFloor<floors;currentFloor++)
				{
					//randomized direction to choose for steps:
					//0=left
					//1=right
					int direction = rand.nextInt(2);
						
					
					//Setting a path to left or right from floor.
					int steps=0;
					//for (int i = 0; i < 5; i++) {	
					while (steps==0)
					{
						steps = rand.nextInt(place)+1;
					}
						//}
					for (int i = 0; i < steps; i++) {
							
						if (direction==0 && place>1 &&  place<cols)
						{
							maze[currentFloor][currentLine][place]=0;
							place--;
						}
						else if(direction==1 && place>0 &&  place<cols-1)
							maze[currentFloor][currentLine][place]=0;
							place++;
							
					}
					//expanding the path down on the same floor	
					steps=0;
					while (steps==0)
					{
						steps = rand.nextInt(lines-1)+1;
					}
					if (currentFloor==floors-1)
					{
						steps=lines;
					}
						
					for (int i = 0; i < steps; i++) {
						
						if (currentFloor>=0 && currentFloor<floors && currentLine<lines && currentLine >=0 && place>=0 && place <cols)
						{
							maze[currentFloor][currentLine][place]=0;
							obj.setGoalPosition(new Position(currentFloor,currentLine,place));
							currentLine++;
						}	
					}
					
						currentLine--;		
				}
				for (int i=0;i<floors;i++)
				{
					maze[i][0][0] = 0;
					maze[i][0][cols-1] = 0;
					maze[i][lines-1][cols-1] = 0;
					maze[i][lines-1][0] = 0;
				}
	}
}




