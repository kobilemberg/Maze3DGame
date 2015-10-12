


package algorithms.mazeGenerators;

import java.util.ArrayDeque;
import java.util.ArrayList;

//import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Random;



/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> MyMaze3dGenerator </h1>
 * The MyMaze3dGenerator Class extends the abstract AbsMaze3dGenerator.
 * The class implement a Maze3d abstract generation method from the super class.
 * the implementation of the maze generation is done by DFS Recursive backtracker algorithm
 * @see https://en.wikipedia.org/wiki/Maze_generation_algorithm 
 */

public class MyMaze3dGenerator extends AbsMaze3dGenerator {

	
//Constructors
	/**
	 * Instantiates a new  my own maze3d generator.
	 */
	public MyMaze3dGenerator() {

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
		Random random = new Random();
		Maze3d maze3DToReturn = new Maze3d(floors, lines, cols);
		if(floors==1 && lines ==1 && cols ==1)
			return maze3DToReturn;
		int[][][] mazeMatrix = maze3DToReturn.getMaze();
		Position current,startPos;
		int colToStartDfs;
		if (cols>1)	{colToStartDfs = random.nextInt(Math.max(1,cols-2))+1;}
		else {colToStartDfs=0;}
		startPos = new Position(0,0,colToStartDfs);
		HashMap<String, String> map = new HashMap<String, String>();
		Deque<Position> stack = new ArrayDeque<Position>();	
		int flag =0;	
		for (int i=0;i<floors;i++)
		{
			for(int j=0;j<lines;j++)
			{
				for(int k=0;k<cols;k++)
				{	
					mazeMatrix[i][j][k] = 1;
					map.put(new Position(i,j,k).toString(),"unvisited");	
				}	
			}
		}
		mazeMatrix[0][0][colToStartDfs]=0;
		current = startPos;
		map.put(current.toString(),"visited" );
		ArrayList<Position> unvisitedNighbors,allNighbors ;	
		do
		{
			unvisitedNighbors = getAllPossibleWalls(current,maze3DToReturn);	
			allNighbors = getAllPossibleWalls(current,maze3DToReturn);
			for(Position nighbor:allNighbors)
			{
				if(  map.get(nighbor.toString()).equals("visited") )
				{
					unvisitedNighbors.remove(nighbor);
					map.put(nighbor.toString(), "visited");
				}
				else
				{
					ArrayList<Position> wallPositionsNighbors= maze3DToReturn.createNSuccessors(nighbor);
					if (wallPositionsNighbors.size()==1)
					{
						if(!(wallPositionsNighbors.get(0)).equals(current)){unvisitedNighbors.remove(nighbor);}
					}
					else{unvisitedNighbors.remove(nighbor);}
				}
			}
			if (unvisitedNighbors.size()>0)
			{
				int numOfNighborToChoose = random.nextInt(unvisitedNighbors.size());
				stack.push(current);
				current = unvisitedNighbors.remove(numOfNighborToChoose);
				mazeMatrix[current.getXPosition()][current.getYposition()][current.getZposition()]=0;
				map.put(current.toString(),"visited");
				unvisitedNighbors.remove(current);
				maze3DToReturn.setGoalPosition(current);
			}
			else if (!stack.isEmpty()){current = stack.pop();}
			else
			{
				flag =0;
				while(flag==0)
				{
					int x = random.nextInt(floors);
					int y = random.nextInt(lines);
					int z = random.nextInt(cols);
					Position randomUnvisitedMazePosition = new Position(x, y, z);
					if (map.get(randomUnvisitedMazePosition.toString()).equals("unvisited"))
					{
						flag=1;
						current = randomUnvisitedMazePosition;
						map.remove(current.toString(),"unvisited");
						if(!map.containsKey(current.toString())){map.put(current.toString(),"visited");}
					}	
				}
			}
		}while(map.containsValue("unvisited"));
			
		//Setting the goal position on the maze.
		if (mazeMatrix[current.getXPosition()][current.getYposition()][current.getZposition()] ==0 )
			maze3DToReturn.setGoalPosition(current);
		else
		{
			/*ArrayList<Position> goalCandidatesArrayList = maze3DToReturn.createNSuccessors(current);
			if( goalCandidatesArrayList.size()>0)
			{
				Collections.shuffle(goalCandidatesArrayList);
				if (!goalCandidatesArrayList.get(0).toString().equals(startPos.toString()))
					maze3DToReturn.setGoalPosition(goalCandidatesArrayList.get(0));
				else if( goalCandidatesArrayList.size()>1)
					maze3DToReturn.setGoalPosition(goalCandidatesArrayList.get(1));
				else
					findValidGoal(maze3DToReturn, mazeMatrix, floors, lines, cols);
			}
			else*/{findValidGoal(maze3DToReturn, mazeMatrix, floors, lines, cols);}
		}
		maze3DToReturn.setStartPosition(startPos);
		//maze3DToReturn.setMaze(mazeMatrix);
		return maze3DToReturn;
	}
	
	/**
	 * This method will print given [][][]int array represent the maze
	 *@param arr int[][][] represent the maze
	 *@param x integer that represent the floors  
	 *@param y integer that represent the lines
	 *@param z integer that represent the columns       
	 */
	public void printArr(int[][][] arr,int x,int y,int z)
	{
		String strOfMazeMatrix="";
		for (int i=0;i<x;i++)
		{
			strOfMazeMatrix+="\n{\n";
			for(int j=0;j<y;j++)
			{
				strOfMazeMatrix+="{";
				for(int k=0;k<z;k++){strOfMazeMatrix+=arr[i][j][k];}
				strOfMazeMatrix+="}\n";
			}
			strOfMazeMatrix+="},\n";
		}
		System.out.println(strOfMazeMatrix);
	}
	
	/**
	 * This function get Position and returns all neighbors and walls on the maze
	 * @param state represent the state that the method will check for neighbors on the maze
	 * @return an ArrayList with Position instances represent all available neighbors and walls of Position state        
	 */
	public ArrayList<Position> getAllPossibleWalls(Position state,Maze3d maze3D)
	{
		ArrayList<Position> arrtoRet = new ArrayList<Position>();
		int stateFloor = state.getXPosition();
		int stateLine = state.getYposition();
		int stateCol = state.getZposition();
		int[][][] maze = maze3D.getMaze();
		int xLength = maze.length;
		int yLength = maze[0].length;
		int zLength = maze[0][0].length;
		//Down
		if (stateFloor>0 )
		{
			if(stateLine>0&&stateLine<yLength-1&&stateCol>0&&stateCol<zLength-1)
			{
				if(maze[stateFloor-1][stateLine][stateCol]==1){arrtoRet.add(new Position(stateFloor-1,stateLine,stateCol));}
			}
		}
		//Right
		if(stateCol+1<zLength-1)
		{
			if(stateLine>0&&stateLine<yLength-1&&stateCol+1>0)
			{
				if (maze[stateFloor][stateLine][stateCol+1]==1){arrtoRet.add(new Position(stateFloor,stateLine,stateCol+1));}		
			}
		}
		//Forward
		if(stateLine+1<yLength-1)
		{
			if(stateLine+1>0&&stateCol>0&&stateCol<zLength-1)
			{
				if(maze[stateFloor][stateLine+1][stateCol]==1){arrtoRet.add(new Position(stateFloor,stateLine+1,stateCol));}		
			}			
		}
		//Backward
		if(stateLine-1>1)
		{
			if(stateLine-1<yLength-1&&stateCol>0&&stateCol<zLength-1)
			{
				if(maze[stateFloor][stateLine-1][stateCol]==1){arrtoRet.add(new Position(stateFloor,stateLine-1,stateCol));}		
			}
		}
		//Left
		if(stateCol-1>1)
		{
			if(stateLine>0&&stateLine<yLength-1&&stateCol-1<zLength-1)
			{
				if(maze[stateFloor][stateLine][stateCol-1]==1){arrtoRet.add(new Position(stateFloor,stateLine,stateCol-1));}		
			}
		}
		//Up
		if (stateFloor<xLength-1 )
		{
			if(stateLine>0&&stateLine<yLength-1&&stateCol>0&&stateCol<zLength-1)
			{
				if(maze[stateFloor+1][stateLine][stateCol]==1){arrtoRet.add(new Position(stateFloor+1,stateLine,stateCol));}
			}
		}
	
		return arrtoRet;
	}
	public boolean findValidGoal(Maze3d maze,int[][][] matrix,int floors,int lines, int cols )
	{
		for (int i=floors-1;i>=0;i--)
		{
			for(int j=lines-1;j>=0;j--)
			{
				for(int k=cols-1;k>=0;k--)
				{	
					if(matrix[i][j][k] ==0){
						maze.setGoalPosition(new Position(i,j,k));
						return true;
						}	
				}	
			}
		}
		return true;
	}
}
