package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Position</h1>
 * This abstract class defining a generally position on a Maze
 */
@SuppressWarnings("serial")
public  class Position implements Serializable
{

	private int x;
	private int y;
	private int z;
	
//Constructors
	/**
	 * Instantiates a new Position with x=y=z = 0
	 *@return Position as instance, that contains x=y=z = 0.
	 */
	public Position() {

		x=y=z=0;
	}
	/**
	 * Instantiates a new  Position with given x,y and z values.
	 *@param x integer that represent position floor 
	 *@param y integer that represent position line 
	 *@param z integer that represent position column 
	 *@return Position as instance, that contains the given x,y and z values.
	 */
	public Position(int x,int y,int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	

	
	/**
	 * Copy constructor that Instantiates a new Position with given other Position
	 *@param other Position type in order to copy his values. 
	 *@return Position an instance, that contains the other Position settings 
	 */
	public Position(Position other) {
		this.y = other.getYposition();
		this.x = other.getXPosition();
		this.z = other.getZposition();
	}

//Getters and setters
	/**
	 *@return the position current floor index as integer
	 */
	public int getXPosition() {return x;}
	/**
	 *@return the position current line index as integer
	 */
	public int getYposition() {return y;}
	/**
	 *@return the position current column index as integer
	 */
	public int getZposition() {return z;}
	/**
	 * This method will set the floor index of the current Position
	 *@param x integer that represent the index of floor to set
	 */
	public void setXposition(int x) {this.x=x;}
	/**
	 * This method will set the line index of the current Position
	 *@param y integer that represent the index of line to set
	 */
	public void setYposition(int y) {this.y=y;}
	/**
	 * This method will set the column index of the current Position
	 *@param z integer that represent the index of column to set
	 */
	public void setZposition(int z) {this.z=z;}
//Overrides

	@Override
	/**
	 * This boolean method comparing between 2 Positions
	 *@param obj an object to compare toString method that represent the position values
	 *@return True if the positions values as string are equals
	 *@return False if the positions values as string are not equals
	 */
	public boolean equals(Object other)
	{
		return this.toString().equals(other.toString());
	}
	/**
	 *@return String of the maze position name at the following format: {x,y,z}
	 */
	@Override
	public String toString() {return "{"+x+","+y+","+z+"}";}
	@Override
	/**
	 *@return the HashCode of the Position as string
	 */
	public int hashCode() {return toString().hashCode();}
	
//Functionality
	/**
	 *This method goal is to change a Position value at a given maze.
	 *@param val integer that represent the value to set at the maze
	 *@param maze Maze3d instance that represent the maze you want to change.
	 */
	public void setValue(int val,Maze3d maze)
	{
		maze.getMaze()[this.getXPosition()][this.getYposition()][this.getZposition()]=val;
	}


	
}
