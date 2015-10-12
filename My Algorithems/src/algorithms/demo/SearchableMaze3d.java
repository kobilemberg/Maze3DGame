/**
 * 
 * @author Kobi Lemberg
 * @version 1.0
 */
package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;


/**
 * The Class SearchableMaze3d.
 * <h1> SearchableMaze3d </h1>
 * This Class job is to adaption between interface search-able to Maze3d
 */
public class SearchableMaze3d implements Searchable<Position>
{

	protected Maze3d maze;
	

//Constructors	
	/**
	 * Instantiates a new search-able maze3d.
	 *
	 * @param maze represent the search-able 3D maze
	 * @return new instance that contains the maze          
	 */
	public SearchableMaze3d(Maze3d maze) {this.maze = maze;}

//Getters and Setters
	/**
	 * @return State<Position> as instance represent the contained 3D maze start state          
	 */
	@Override
	public State<Position> getStartPosition() {return new State<Position>(maze.getStartPosition());}
	
	
	/**
	 * @return State<Position> as instance Represent the contained 3D maze goal position          
	 */
	@Override
	public State<Position> getGoalPosition() {return new State<Position>(maze.getGoalPosition());}
//Functionality
	/**
	 * This function get State<Position> state represent a Position on of the Maze3d and returns all his available neighbors on the maze
	 * @param State<Position> represent the state that the method will check for neighbors on the maze
	 * @return an ArrayList<State<Position>> with State<Position> as instances represent all available neighbors of Position state        
	 */
	@Override
	public ArrayList<State<Position>> createNSuccessors(State<Position> state) {
		ArrayList<State<Position>> arr = new ArrayList<State<Position>>();
		for (Position p:maze.createNSuccessors(state.getState())){arr.add(new State<Position>(p));}
		return arr;
	}

	@Override
	/**
	 * This method returns the required move to do in order to go from state f to State p as a String.
	 *@param p MazePOsion represent the place that we need to go to.
	 *@return String represent the required move to do in order to go to another maze position
	 */
	public String moveToPosition(State<Position> f, State<Position> p) {return maze.moveToPosition(f.getState(), p.getState());}

	@Override
	/**
	 * This method returns the cost of moving from one state to other
	 *@param State<Position> f, represent the current state.
	 *@param State<Position> p, represent the state we want to reach from f.
	 *@return int represent the Cost of moving.
	 */
	public int CostOfmovingToOtherState(State<Position> f, State<Position> p) {
		return maze.CostOfmovingToOtherPosition(f.getState(), p.getState());
	}
}
