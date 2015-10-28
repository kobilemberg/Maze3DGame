package algorithms.search;


import java.util.ArrayList;


/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>BFSCommonSearcher</h1>
 * This class extends AbsCommonSearcher
 */
public class BFSCommonSearcher extends AbsCommonSearcher{

/**
	 * 
	 */
	private static final long serialVersionUID = -8352177408828044518L;
	//Constructors
	/**
	 * Instantiates a new  BFS  searcher
	 */
	public BFSCommonSearcher() {super();}
	
	/**
	 * Instantiates a new  BFS searcher with given solution to return when the searcher finished
	 *@param Solution solution represent the solution to return after the maze is solved
	 */
	public BFSCommonSearcher(@SuppressWarnings("rawtypes") Solution solution) {
		super();
		this.solution = solution;	
	}
//Functionality
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 *this method will search and find a solution at search-able domain.
	 *@param s Searchable represent the search domain.
	 *@return Solution as instance represent the solution of the Search domain.
	 */
	public Solution search(Searchable s) {
		State startPosition = s.getStartPosition();
		startPosition.setCameFromAction("Start");
		State goalPosition = s.getGoalPosition();
		this.openList.add(startPosition);
		while (openList.size()>0)
		{
			State n = openList.poll();
			this.evaluatedNodes++;
			closedList.add(n);
			if (n.equals(goalPosition))
			{
				System.out.println("Recunstruct path....");
				return this.solution.reconstructPath(n);
			}
			ArrayList<State> successors = s.createNSuccessors(n);
			for (State successor : successors)
			{
				//double newPathPrice = successor.getCost()+1;
				double newPathPrice = successor.getCost()+s.CostOfmovingToOtherState(n, successor);
				if (!openList.contains(successor)&& !closedList.contains(successor))
				{
					successor.setCameFromAction(s.moveToPosition(n, successor));
					successor.setFather(n);
					//successor.setCost(n.getCost()+1);
					successor.setCost(n.getCost()+s.CostOfmovingToOtherState(n, successor));
					openList.add(successor);
				}
				else
				{
					if (newPathPrice < successor.getCost())
					{
						if (!openList.contains(successor)&&closedList.contains(successor))
						{
							closedList.remove(successor);
							openList.add(successor);
							
						}
						else if (openList.contains(successor)&& !closedList.contains(successor))
						{
							openList.remove(successor);
							//successor.setCost(1+n.getCost());
							successor.setCost(n.getCost()+s.CostOfmovingToOtherState(n, successor));
							openList.add(successor);
							
						}
					}
				}
			}
			
		}
		System.out.println("Cannot resolve");
		return null;
	}
//Overrides
	@Override
	/**
	 *@return new string that contains "BFS"
	 */
	public String toString() {
		return "BFS";
	}
	

}
