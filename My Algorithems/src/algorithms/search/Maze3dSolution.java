package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

import algorithms.mazeGenerators.Position;


/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Maze3dSolution</h1>
 * This class implement Solution interface
 * The class main goal is defining a solution for 3D mazes.
 */

@SuppressWarnings("serial")
public class Maze3dSolution implements Solution<Position> {
	ArrayList<State<Position>> solution;
	int numberOfNodesEvaluated;
//Constructors
	/**
	 * Instantiates a new  MazeSolution
	 */

	public Maze3dSolution() 
	{
		this.solution = new ArrayList<>();
		numberOfNodesEvaluated =0;
	}
	
	/**
	 * Copy constructor that Instantiates a new  MazeSolution with given other MazeSolution
	 *@param sol MazeSolution type in order to copy his values. 
	 *@return Maze3dSolution an instance, that contains the other MazeSolution settings 
	 */
	public Maze3dSolution(Maze3dSolution sol) 
	{
		this.solution = sol.getSolution();
		this.numberOfNodesEvaluated=sol.numberOfNodesEvaluated;
	}
	/**
	 * Instantiates a new  MazeSolution with given ArrayList of positions represent the solution of 3D maze.
	 *@param solutionMap ArrayList<Position> represent the solution of 3D maze. 
	 *@return new  Maze3dSolution with given ArrayList of positions represent the solution of 3D maze.
	 */
	public Maze3dSolution(ArrayList<State<Position>> solutionMap)
	{
		this.solution = solutionMap;
	}
//Getters and setters
	@Override
	/**
	 *@return ArrayList<State<Position>> a new instances represent the solution.
	 */
	public ArrayList<State<Position>> getSolution() {return solution;}
	@Override
	/**
	 *This method will set the solution.
	 *@param solution ArrayList<State<Position>> represent the solution to set. 
	 */
	public void setSolution(ArrayList<State<Position>> solution) {this.solution = solution;}
	/**
	 *@return Int represent the number of the evaluated nodes during the solution discovery by the algorithm.
	 */
	public int getNumberOfNodesEvaluated(){return numberOfNodesEvaluated;}
	/**
	 * This method set the number of the evaluated nodes during the solution discovery by the algorithm.
	 *@param Int numberOfNodesEvaluated represent the number of the evaluated nodes during the solution discovery by the algorithm.
	 */
	public void setNumberOfNodesEvaluated(int numberOfNodesEvaluated)
	{
		this.numberOfNodesEvaluated = numberOfNodesEvaluated;
	}
	
//Overrides
	@Override
	/**
	 * This boolean method comparing between 2 Maze3dSolution instances.
	 *@param obj an object to compare toString method that represent the Maze3dSolution values
	 *@return True if the Maze3dSolution values as string are equals
	 *@return False if the Maze3dSolution values as string are not equals
	 */
	public boolean equals(Object obj) {return obj.toString().equals(this.toString());}
	@Override
	/**
	 *@return String of the solution with the solution map with the following format: {up, down, ...}.
	 */
	@SuppressWarnings("rawtypes")
	public String toString() {
		String strToRet="Solution:{";
		for (State pos :solution)
		{
			strToRet+=pos.toString()+",";
		}
		return strToRet+="}";
		
	}
//Functionality
	/**
	 * This Method will provide an instance of solution that contains a solution for 3D mazes.
	 *@param Current Position represent the Position to generate the path to.
	 *@return instance of solution that contains a solution for 3D mazes.
	 */
	@SuppressWarnings("rawtypes")
	public  Solution reconstructPath(State<Position> current) 
	 {	
		Maze3dSolution solution = new Maze3dSolution(); 	
		ArrayList<State<Position>> moves = new ArrayList<State<Position>>();
			while (current.getFather()!=null)
			{
				moves.add(current);
				
				current = current.getFather();
			}
			Collections.reverse(moves);
			solution.setSolution(moves);
			return solution;
	}

}
