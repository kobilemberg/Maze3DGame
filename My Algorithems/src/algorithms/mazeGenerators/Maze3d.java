package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.io.Serializable;
import java.nio.*;

/**
 * 
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> Maze3d </h1>
 * Maze3d Class represent a 3D maze.
 */

@SuppressWarnings("serial")
public class Maze3d implements Serializable{
	private int xLength,yLength,zLength;
	private int[][][] maze;
	private Position start,goal;
	

//Constructors	
	
	/**
	 * Instantiates a new  maze3d with given floors, lines and columns.
	 *@param floor integer that represent the number of floors to set 
	 *@param lines integer that represent the number of lines to set
	 *@param cols integer that represent the number of columns to set
	 */
	public Maze3d(int floors, int lines, int cols) {
		super();
		this.xLength = floors;
		this.yLength = lines;
		this.zLength = cols;
		maze = new int[xLength][yLength][zLength];
		start = goal = new Position();
	}
	
	/**
	 * Instantiates a new  maze3d with Byte array of the maze with the following conventions:.
	 * {Floors,Lines,Cols,Xstart,Ystart,Zstart,Xgoal,Ygoal,Zgoal,0,1,....<the maze matrix>,}
	 *@param arr byte[] that represent the array of the maze 
	 *@return Maze3d an instance, that contains the array of the maze 
	 */
	public Maze3d(byte[] arr) {
		super();
		byte[] copyArr = new byte[4];
		//xLength
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i];}
		xLength = ByteBuffer.wrap(copyArr).getInt();
		
		//yLength
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+4];}
		yLength = ByteBuffer.wrap(copyArr).getInt();
		
		//zLength
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+8];}
		zLength = ByteBuffer.wrap(copyArr).getInt();
		
		maze = new int[xLength][yLength][zLength];//Creating the Matrix
		///Start Point
		int xStart =0;
		int yStart =0;
		int zStart=0;
		//xStart
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+12];}
		xStart = ByteBuffer.wrap(copyArr).getInt();
		//yStart
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+16];}
		yStart = ByteBuffer.wrap(copyArr).getInt();
		//zStart
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+20];}
		zStart = ByteBuffer.wrap(copyArr).getInt();
		//Building Start point
		this.start = new Position(xStart, yStart, zStart);
		//Goal
		int xGoal =0;
		int yGoal =0;
		int zGoal=0;
		//xGoal
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+24];}
		xGoal = ByteBuffer.wrap(copyArr).getInt();
		//yGoal
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+28];}
		yGoal = ByteBuffer.wrap(copyArr).getInt();
		//zGoal
		for (int i = 0; i <4; i++) {copyArr[i] = arr[i+32];}
		zGoal = ByteBuffer.wrap(copyArr).getInt();
		this.goal = new Position(xGoal,yGoal, zGoal);		
		int z=36;
		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {
				for (int k = 0; k < zLength; k++) {
					maze[i][j][k]=arr[z];
					z++;
				}
			}
		}
	}
	
	
	/**
	 * Copy constructor that Instantiates a new  maze3d with given other 3D maze
	 *@param other Maze3d type in order to copy his values. 
	 */
	public Maze3d(Maze3d other)
	{
		this.xLength = other.xLength;
		this.yLength = other.yLength;
		this.zLength = other.zLength;
		this.maze = other.getMaze();
		this.start = new Position(other.start);
		this.goal = new Position(other.goal);
	}
	
//Getters and setters
	/**
	 * This method will replace the maze with given maze
	 *@param maze int[][][] represent the maze to set. 
	 */
	public void setMaze(int[][][] maze){this.maze= maze.clone();}
	/**
	 *@return return the maze as int[][][]
	 */
	public int[][][] getMaze(){return this.maze;}
	/**
	 * @return Position represent the contained start position as instance         
	 */
	public Position getStartPosition(){return this.start;}
	/**
	 * This method will set the start position of the maze.
	 *@param start Position represent the position to start
	 */
	public void setStartPosition(Position start)
	{
		this.start = new Position(start);
		this.maze[start.getXPosition()][start.getYposition()][start.getZposition()]=0;
	}
	/**
	 * @return Position represent the contained goal position as instance         
	 */
	public Position getGoalPosition(){return this.goal;}
	/**
	 * This method will set the start position of the maze.
	 *@param start Position represent the position to start
	 */
	public void setGoalPosition(Position Goal)
	{
		this.goal = new Position(Goal);
		this.maze[Goal.getXPosition()][Goal.getYposition()][Goal.getZposition()]=0;
	}
	/**
	 * This method will return the maze as a string
	 *@return the maze as a string
	 */
//Overrides
	@Override
	public String toString() {
		String strOfMazeMatrix="";
		for (int i=0;i<xLength;i++)
		{
			strOfMazeMatrix+="\n{\n";
			for(int j=0;j<yLength;j++)
			{
				strOfMazeMatrix+="{";
				for(int k=0;k<zLength;k++)
				{
						strOfMazeMatrix+=maze[i][j][k];
				}
				strOfMazeMatrix+="}\n";
			}
			strOfMazeMatrix+="},\n";
		}
		return "Maze3d:\nDimensions are: Floors="+xLength + ", Lines=" + yLength+ ", Columns=" + zLength+"\n" +"Start position: "+this.start.toString()+" Goal position: "+this.goal.toString()+"\n"
				+ "Maze matrix:\n"
		+ strOfMazeMatrix;
	}
	
	
	
/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}


@Override
public boolean equals(Object obj) {
	return this.toString().equals(obj.toString());
}

	//Functionality
	/**
	 * This method will return the section that crossed by some given floor.
	 *@param xIndex int represent the given floor to return the crossed section
	 *@return int[][] represent the section that crossed by some given floor.
	 */
	public int[][] getCrossSectionByX(int xIndex) 
	{
		if (xIndex<0 || xIndex>=xLength)
			throw new IndexOutOfBoundsException("the valid x index between 0 to "+(xLength-1));
		int[][]arrToRet = new int[yLength][zLength];
				for (int i = 0; i < yLength; i++) {
					for (int k = 0; k < zLength; k++) {
						arrToRet[i][k] = maze[xIndex][i][k];
					}	
				}
		return arrToRet;
	}
	/**
	 * This method will return the section that crossed by some given line.
	 *@param yIndex int represent the given line to return the crossed section
	 *@return int[][] represent the section that crossed by some given line.
	 */
	public int[][] getCrossSectionByY(int yIndex) 
	{
		if (yIndex<0 || yIndex>=yLength)
			throw new IndexOutOfBoundsException("the valid y index between 0 to "+(yLength-1));
		
		int[][] arrToRet = new int[xLength][zLength];
				for (int i = 0; i < xLength; i++) {
					for (int k = 0; k < zLength; k++) {
						arrToRet[i][k] = maze[i][yIndex][k];
					}	
				}
		return arrToRet;
	}
	/**
	 * This method will return the section that crossed by some given column.
	 *@param zIndex int represent the given column to return the crossed section
	 *@return int[][] represent the section that crossed by some given column.
	 */
	public int[][] getCrossSectionByZ(int zIndex) 
	{
		if (zIndex<0 || zIndex>=zLength)
			throw new IndexOutOfBoundsException("the valid z index between 0 to"+(zLength-1));
		int[][] arrToRet = new int[xLength][yLength];
				for (int i = 0; i < xLength; i++) {
					for (int k = 0; k < yLength; k++) {
						arrToRet[i][k] = maze[i][k][zIndex];
					}
					
				}
		return arrToRet;
	}
	
	/**
	 * This function get Position and returns all available steps to do
	 * @param state Position represent the state that the method will check for available steps on the maze
	 * @return String[] with strings of Positions represent all available steps to do.        
	 */
	public String[] getPossibleMoves(Position state)
	{
		ArrayList<String> strings = new ArrayList<String>();
		int stateFloor = (state).getXPosition();
		int stateLine = (state).getYposition();
		int stateCol = (state).getZposition();
		
		if (stateFloor>0 )
		{
			if(maze[stateFloor-1][stateLine][stateCol]==0)
				strings.add("Down");
		}
		if(stateCol<zLength-1)
		{
			if(maze[stateFloor][stateLine][stateCol+1]==0)
				strings.add("Right");
		}
		if(stateLine<yLength-1)
		{
			if(maze[stateFloor][stateLine+1][stateCol]==0)
				strings.add("Forward");
				
		}
		if(stateLine>0)
		{
			if(maze[stateFloor][stateLine-1][stateCol]==0)
				strings.add("Backward");
		}
		if(stateCol>0)
		{
			if(maze[stateFloor][stateLine][stateCol-1]==0)
				strings.add("Left");
		}
		if (stateFloor<xLength-1 )
		{
			if(maze[stateFloor+1][stateLine][stateCol]==0)
				strings.add("Up");
		}
			return strings.toArray(new String[strings.size()]);
	}
	
	
	/**
	 * This function get Position and returns all his available neighbors on the maze
	 * @param state represent the state that the method will check for neighbors on the maze
	 * @return an ArrayList with Position instances represent all available neighbors of Position state        
	 */
	public ArrayList<Position> createNSuccessors(Position state)
	{
		ArrayList<Position> arrtoRet = new ArrayList<Position>();
		int stateFloor = (state).getXPosition();
		int stateLine = (state).getYposition();
		int stateCol = (state).getZposition();
		//Down
		if (stateFloor>0 )
		{
			if(maze[stateFloor-1][stateLine][stateCol]==0)
				arrtoRet.add(new Position(stateFloor-1,stateLine,stateCol));
		}
		//Right
		if(stateCol<zLength-1)
		{
			if(maze[stateFloor][stateLine][stateCol+1]==0)
				arrtoRet.add(new Position(stateFloor,stateLine,stateCol+1));
		}
		//Forward
		if(stateLine<yLength-1)
		{
			if(maze[stateFloor][stateLine+1][stateCol]==0)
				arrtoRet.add(new Position(stateFloor,stateLine+1,stateCol));
		}
		//Backward
		if(stateLine>0)
		{
			if(maze[stateFloor][stateLine-1][stateCol]==0)
				arrtoRet.add(new Position(stateFloor,stateLine-1,stateCol));
		}
		//Left
		if(stateCol>0)
		{
			if(maze[stateFloor][stateLine][stateCol-1]==0)
				arrtoRet.add(new Position(stateFloor,stateLine,stateCol-1));
		}
		//Up
		if (stateFloor<xLength-1 )
		{
			if(maze[stateFloor+1][stateLine][stateCol]==0)
				arrtoRet.add(new Position(stateFloor+1,stateLine,stateCol));
		}

		return arrtoRet;		
	}
	/**
	 * This method returns the required move to do in order to go to another maze position as a String.
	 *@param f Position represent the place we are starting.
	 *@param p Position represent the place that we need to go to.
	 *@return String represent the required move to do in order to go to another maze position
	 */
	public String moveToPosition(Position f, Position p) {
		if((p).getXPosition()>(f).getXPosition())
			return "Up";
		if((p).getXPosition()<(f).getXPosition())
			return "Down";
		if ((p).getYposition()>(f).getYposition())
			return "Forward";
		if ((p).getYposition()<(f).getYposition())
			return "Backward";
		if ((p).getZposition()<(f).getZposition())
			return "Left";
	//	p.setCost(1);
		return "Right";
		
	}
	/**
	 * This method returns the cost of legal move from one position to other.
	 *@param f Position represent the place we are starting.
	 *@param p Position represent the place that we need to go to.
	 *@return int represent the cost
	 */
	public int CostOfmovingToOtherPosition(Position f, Position p){ return 1;}
	/**
	 * This method returns the Maze3d as a ByteArray with the following conventions:.
	 *{Floors,Lines,Cols,Xstart,Ystart,Zstart,Xgoal,Ygoal,Zgoal,0,1,....<the maze matrix>,}
	 *@return byte[] Represent the Maze3d instance.
	 */
	public byte[] toByteArray()
	{
		byte[] buffer = new byte[4];
		byte[] arrToRet = new byte[(xLength*yLength*zLength)+36];
		//X
		buffer = ByteBuffer.allocate(4).putInt(xLength).array();
		for (int i = 0; i <4; i++) {arrToRet[i]=buffer[i];}
		//Y
		buffer = ByteBuffer.allocate(4).putInt(yLength).array();
		for (int i = 4; i <8; i++) {arrToRet[i]=buffer[i-4];}
		//Z
		buffer = ByteBuffer.allocate(4).putInt(zLength).array();
		for (int i = 8; i <12; i++) {arrToRet[i]=buffer[i-8];}
		//Start		
		//xStart
		buffer = ByteBuffer.allocate(4).putInt(start.getXPosition()).array();
		for (int i = 12; i <16; i++) {arrToRet[i]=buffer[i-12];}
		//yStart
		buffer = ByteBuffer.allocate(4).putInt(start.getYposition()).array();
		for (int i = 16; i <20; i++) {arrToRet[i]=buffer[i-16];}
		//zStart
		buffer = ByteBuffer.allocate(4).putInt(start.getZposition()).array();
		for (int i = 20; i <24; i++) {arrToRet[i]=buffer[i-20];}
		//Goal		
		//xGoal
		buffer = ByteBuffer.allocate(4).putInt(goal.getXPosition()).array();
		for (int i = 24; i <28; i++) {arrToRet[i]=buffer[i-24];}
		//yGoal
		buffer = ByteBuffer.allocate(4).putInt(goal.getYposition()).array();
		for (int i = 28; i <32; i++) {arrToRet[i]=buffer[i-28];}
		//zGoal
		buffer = ByteBuffer.allocate(4).putInt(goal.getZposition()).array();
		for (int i = 32; i <36; i++) {arrToRet[i]=buffer[i-32];}
		int z=36;
		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {
				for (int k = 0; k < zLength; k++) {			
					arrToRet[z] = (byte)maze[i][j][k];
					z++;
				}
			}
		}
		return arrToRet;
	}
}