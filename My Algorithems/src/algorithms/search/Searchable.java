package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Searchable</h1>
 * This interface defining a generally search-able problem as maze,graph and etc'
 * all implemented classes will have to implement getStartState,getGoalState and createNSuccessors.
 */

public interface Searchable<T> extends Serializable{
		/**
		 * @return The problem beginning position          
		 */
	   State<T> getStartPosition();
		/**
		 * @return The problem ending position          
		 */
	   State<T> getGoalPosition();
	   /**
		 * This function get Position and returns all his available positions to move to.
		 * @param state represent the state that the method will check for available positions to move to.
		 * @return an ArrayList with Position instances represent all available positions to move to.      
		 */
	   public ArrayList<State<T>> createNSuccessors(State<T> state);
	   
	   public String moveToPosition(State<T> f, State<T> p);
	   public int CostOfmovingToOtherState(State<T> f, State<T> p);
	}
