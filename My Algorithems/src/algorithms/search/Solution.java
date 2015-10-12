package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;




/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Solution</h1>
 * This interface defining a generally solution for some problem as maze,graph and etc'
 * all implemented classes will have to implement reconstructPath, set solution and getSolution
 */
public interface Solution <T> extends Serializable{
	
	
	/**
	 * This method goal is to return the solution map of the problem as ArrayList of Positions instances
	 *@return ArrayList<Position> represent the solution map of the problem as ArrayList of Positions instances          
	 */
	
	public ArrayList<State<T>> getSolution();
	/**
	 * This method goal is to set the solution map of the problem as ArrayList of Positions instances
	 *@param sol ArrayList<Position> represent the solution map       
	 */
	public void setSolution(ArrayList<State<T>> sol);
	/**
	 * This method goal is to built the solution for position.
	 *@param current Position that represent the position that we want to know the path to
	 *@return Solution that contains the path to current         
	 */
	@SuppressWarnings("rawtypes")
	public  Solution reconstructPath(State<T> current);
}
