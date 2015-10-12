package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Astar</h1>
 * This class extends AbsCommonSearcher and implement Comparator<Position> because the algorithm needs to compare between 2 different costs of different positions
 * Each user that will use this class will have to set a instance of distance in order to calculate the heuristic function
 */


@SuppressWarnings("rawtypes")
public class AStarCommonSearcher extends AbsCommonSearcher implements Comparator<State> {

	protected Distance h;
	protected Searchable s;
//Constructors
	/**
	 * Instantiates a new  A* searcher
	 *@return new instance of A* searcher
	 */
	public AStarCommonSearcher() 
	{
		super();
		this.openList=new PriorityQueue<State>(this);
	}

	/**
	 *Instantiates a new  A* searcher with given Distance as instance to calulate the heuristics function
	 *@param h Distance as instance to calulate the heuristics function
	 *@return new instance of A* searcher
	 */
	
	public AStarCommonSearcher(Distance h) 
	{
		super();
		this.h = h;
	}
	/**
	 *Instantiates a new  A* searcher with given Distance as instance to calulate the heuristics function and solution to return when the searcher finished
	 *@param Solution solution represent the solution to return after the maze is solved
	 *@param h Distance as instance to calulate the heuristics function
	 *@return new instance of A* searcher
	 */
	public AStarCommonSearcher(Solution solution,Distance h) {
		super();
		this.solution = solution;
		this.h=h;
	}
	/**
	 *Instantiates a new  A* searcher with given Distance as instance to calulate the heuristics function and solution to return when the searcher finished
	 *@param Solution solution represent the solution to return after the maze is solved
	 *@param h Distance as instance to calulate the heuristics function
	 *@param s Searchable as instance represent the searchable problam
	 *@return new instance of A* searcher
	 */
	public AStarCommonSearcher(Solution solution,Distance h,Searchable s) {
		super();
		this.solution = solution;
		this.h=h;
		this.s = s;
	}
	
//Getters and setters
	/**
	 *@return Distance as instance represent the type of the distance to calculate the heuristic function
	 */
	public Distance getH() {
		return h;
	}
	/**
	 *this method will set the type of the distance for the heuristic calculations.
	 *@param h Distance as instance to calulate the heuristics function
	 */
	public void setH(Distance h) {
		this.h = h;
	}
//Overrides
	@Override
	/**
	 *@return new string that contains "Astar"
	 */
	public String toString() {
		return "Astar";
	}
	/**
	 * this method will compare between the cost of 2 positions.
	 *@param o1,o2 represent the Positions to compare.
	 *@return 1 if the cost of o2 is smaller from h1
	 *@return -1 if the cost of o1 is smaller from h2
	 *@return 0 if the cost o1,o2 are equals 
	 */
	public int compare(State o1, State o2) {
		State goal = s.getGoalPosition();
		if (o1.getCost()+h.getDistance(o1, goal)<o2.getCost()+h.getDistance(o2, goal))
			return -1;
		else if(o1.getCost()+h.getDistance(o1, goal)>o2.getCost()+h.getDistance(o2, goal))
			return 1;
		return 0;
	}
//Functionality
	@SuppressWarnings({ "unchecked" })
	@Override
	/**
	 *this method will search and find a solution at search-able domain.
	 *@param s Searchable represent the search domain.
	 *@return Solution as instance represent the solution of the Search domain.
	 */
	public Solution search(Searchable s) {
		this.s = s;
		State start = s.getStartPosition();
		start.setCameFromAction("Start");
		State goal = s.getGoalPosition();
		openList.add(start);
		//start.setCost(0);
		HashSet<String> visited = new HashSet<String>();
		while(!openList.isEmpty())
		{
			State current = openList.poll();
			if (!visited.contains(current.toString()))
					this.evaluatedNodes++;
			visited.add(current.toString());
			if (current.equals(goal))
				return this.solution.reconstructPath(current);
			if (openList.contains(current))
			{
				openList.remove(current);
			}
			closedList.add(current);
			ArrayList<State> arr = s.createNSuccessors(current);
			for (State successor:arr)
			{
				//successor.setCost(n.getCost()+s.CostOfmovingToOtherState(n, successor));
				
				
				double tentativeGScore = current.getCost() +s.CostOfmovingToOtherState(current, successor);
			//	double tentativeGScore = current.getCost() +s.CostOfmovingToOtherState(current, successor)+ h.getDistance(successor, goal);
			//	double tentativeGScore = current.getCost() +s.CostOfmovingToOtherState(current, successor)+ h.getDistance(successor, goal) -  h.getDistance(current, goal);
				if(closedList.contains(successor))
				{
					continue;
				}
				if( !openList.contains(successor)|| tentativeGScore<successor.getCost() )
				{
					successor.setFather(current);
					successor.setCameFromAction(s.moveToPosition(current, successor));
					successor.setCost(tentativeGScore);
					if (!openList.contains(successor))
					{
						openList.add(successor);
					}
					else
					{
						openList.remove(successor);
						openList.add(successor);
					}				
				}
			}
		}
		System.out.println("Cannot resolve");
		return null;
	}


}
