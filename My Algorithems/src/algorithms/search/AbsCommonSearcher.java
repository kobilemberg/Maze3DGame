package algorithms.search;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>AbsCommonSearcher</h1>
 * This abstract class implement Searcher interface and defining a generally Searcher for search-able problem as maze,graph and etc'
 */

@SuppressWarnings("rawtypes")
public abstract class AbsCommonSearcher implements Searcher {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5580473253732687623L;
	PriorityQueue<State> openList;
	 HashSet<State> closedList;
	 int evaluatedNodes;
	 protected Solution solution;
	 
//Constructors	 
	 	/**
		 * Instantiates a new  my own maze3d generator with the following tools:
		 * Open List of type State<T>
		 * Closed List of type State<T>
		 * Number of evaluated nodes
		 *@return AbsCommonSearcher an instance
		 */
		public AbsCommonSearcher() {
			openList = new PriorityQueue<State>();
			closedList = new HashSet<State>();
			evaluatedNodes=0;
		}
	 
		/**
		 * Instantiates a new  my own maze3d generator with specific Instance of solution to return when the maze is solved
		 *@param Solution solution represent the solution to return after the maze is solved
		 *@return AbsCommonSearcher an instance
		 */
		 
		public AbsCommonSearcher(Solution solution) {
			openList = new PriorityQueue<State>();
			closedList = new HashSet<State>();
			evaluatedNodes=0;
			this.solution =solution;
		}
//Getters and setters	
		/**
		 *@return PriorityQueue<State> that represent the open list of the searcher
		 */
		public PriorityQueue<State> getOpenList() {return openList;}
		/**
		 * This method will set the open list of the searcher
		 *@param openList PriorityQueue<State>, represent the open lost as PriorityQueue of States.
		 */
		public void setOpenList(PriorityQueue<State> openList) {this.openList = openList;}
		/**
		 *@return HashSet<State> that represent the closed list of the searcher
		 */
		public HashSet<State> getClosedList() {return closedList;}
		/**
		 * this method will set the closed list of the searcher
		 *@param closedList HashSet<State>, represent the closed list of the searcher.
		 */
		public void setClosedList(HashSet<State> closedList) {this.closedList = closedList;}
	
		/**
		 * this method will set the number of the evaluated nodes
		 *@param evaluatedNodes as Int represent the number of  evaluated nodes
		 * 
		 */
		public void setEvaluatedNodes(int evaluatedNodes) {this.evaluatedNodes = evaluatedNodes;}
		/**
		 *@return Solution instance of the search-able problem.
		 */
		public Solution getSolution() {return solution;}
		
		/**
		 * this method will set the solution of the search-able problem.
		 *@param Solution solution to set.
		 * 
		 */
		public void setSolution(Solution solution) {this.solution = solution;}
		@Override
		/**
		 *@return Int represent the number of the nodes that were evaluated by the searcher      
		 */
		public int getNumberOfNodesEvaluated() {return this.evaluatedNodes;}
//Functionality
		@Override
		/**
		 * this abstract method will search and fine a solution at search-able domain.
		 * Each searcher that extend this class will have to implement this method.
		 *@param s Searchable represent the search domain.
		 * 
		 */
		public abstract Solution search(Searchable s);	 
}
