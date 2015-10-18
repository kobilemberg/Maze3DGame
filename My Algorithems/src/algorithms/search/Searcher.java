package algorithms.search;

import java.io.Serializable;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Searcher</h1>
 * This interface defining a generally Searcher for search-able problem as maze,graph and etc'
 * all implemented classes will have to implement search and getNumberOfNodesEvaluated method.
 */
public interface Searcher<T> extends Serializable{
	/**
	 * This method goal is to search and generate a instance of solution to a search-able problam
	 *@param s Searchable instance represent the problem
	 *@return Solution that contains the path from the beginning to the end.        
	 */
    @SuppressWarnings("rawtypes")
	public Solution search(Searchable<T> s);
    // get how many nodes were evaluated by the algorithm
    /**
	 *@return Int represent the number of the nodes that were evaluated by the searcher      
	 */
    public int getNumberOfNodesEvaluated();
}
