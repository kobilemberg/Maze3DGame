package algorithms.demo;


import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStarCommonSearcher;
import algorithms.search.AirDistance;
import algorithms.search.BFSCommonSearcher;
import algorithms.search.ManhatenDistance;
import algorithms.search.Maze3dSolution;
import algorithms.search.Solution;
import algorithms.search.State;

/**
 * The Class Demo.
 * <h1>Demo</h1>
 * This Class job is to run a demo of Maze3D via MyMaze3DGenerator and solve it via BFS and A*
 */
public class Demo 
{
	
	/**
	 * Instantiates a new Demo      
	 */
	public Demo()
	{	
	}
	/**
	 * this method goal is to create a Search-able 3D Maze
	 * @param floors represent the search-able 3D maze
	 * @param lines represent the search-able 3D maze
	 * @param cols represent the search-able 3D maze
	 * @return SearchableMaze3d as new instance.         
	 */
	public SearchableMaze3d createSearchableMaze3d (int floors,int lines, int cols)
	{
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze =mg.generate(floors, lines, cols);		
		return new SearchableMaze3d(maze);
	}
	/**
	 * this method goal is to print the SearchableMaze3d
	 */
	public void printMaze3d(SearchableMaze3d searchableMaze)
	{
		System.out.println(searchableMaze.maze.toString());
	}
	/**
	 * this method goal is to solve a Search-able 3D Maze with BFS algorithm
	 * @param searchableMaze represent the search-able 3D maze to solve
	 * @return solution of the maze as new instance.         
	 */
	public Solution<Position> solveSearchableMazeWithBFS(SearchableMaze3d searchableMaze)
	{
		BFSCommonSearcher bfs = new BFSCommonSearcher(new Maze3dSolution());
		Maze3dSolution solution = new Maze3dSolution();
		bfs.setSolution(solution);
		solution = (Maze3dSolution) (bfs.search(searchableMaze));
		solution.setNumberOfNodesEvaluated(bfs.getNumberOfNodesEvaluated());
		return solution;
	}
	/**
	 * this method goal is to solve a Search-able 3D Maze with A* algorithm while using Manhattan distance as heuristic function
	 * @param searchableMaze represent the search-able 3D maze to solve
	 * @return solution of the maze as new instance.         
	 */
	public Solution<Position> solveSearchableMazeWithAstarByManhatenDistance(SearchableMaze3d searchableMaze)
	{
		AStarCommonSearcher aStar = new AStarCommonSearcher(new ManhatenDistance());
		Maze3dSolution solution = new Maze3dSolution();
		aStar.setSolution(solution);
		solution= (Maze3dSolution)(aStar.search(searchableMaze));
		solution.setNumberOfNodesEvaluated(aStar.getNumberOfNodesEvaluated());
		return solution;
	}
	/**
	 * this method goal is to solve a Search-able 3D Maze with A* algorithm while using air distance as heuristic function
	 * @param searchableMaze represent the search-able 3D maze to solve
	 * @return solution of the maze as new instance.         
	 */
	public Solution<Position> solveSearchableMazeWithAstarByAirnDistance(SearchableMaze3d searchableMaze)
	{
		AStarCommonSearcher aStar = new AStarCommonSearcher(new AirDistance());
		Maze3dSolution solution = new Maze3dSolution();
		aStar.setSolution(solution);
		solution= (Maze3dSolution)(aStar.search(searchableMaze));
		solution.setNumberOfNodesEvaluated(aStar.getNumberOfNodesEvaluated());
		return solution;
	}
	/**
	 * this method goal is to print the solution of the maze to the screen       
	 */
	public void printSolutionOfSearchableMaze3d(Solution<?> solution)
	{
		System.out.println(solution.toString());
	}
	@Override
	public String toString() {
		return this.getClass().toString();
	}
	public void run()
	{
		System.out.println("**********Demo tests of solving maze**********");
		System.out.println("Creating searchable 3D maze");

		SearchableMaze3d searchableMaze = createSearchableMaze3d(3,10,18);

		System.out.println("Searchable 3D maze was succesfully created");
		printMaze3d(searchableMaze);
		System.out.println("Starting with BFS Search algorithm:");
		Maze3dSolution bfsMaze3dSolution = (Maze3dSolution)(solveSearchableMazeWithBFS(searchableMaze));
		System.out.println("Maze was solved via BFS, the solution is:");
		for (State<Position> p: bfsMaze3dSolution.getSolution())
		{
			System.out.println(p.getCameFromAction() + " To: "+p.toString());
		}
		System.out.println("Evaluated nodes: "+bfsMaze3dSolution.getNumberOfNodesEvaluated());
		System.out.println("Finished with BFS Search algorithm:");
		System.out.println("");
		System.out.println("Starting with A* Search algorithm:");
		Maze3dSolution aStarMaze3dSolution = (Maze3dSolution)(solveSearchableMazeWithAstarByAirnDistance(searchableMaze));
		System.out.println("Maze was solved via A* with air distance, the solution is:");
		for (State<Position> p: aStarMaze3dSolution.getSolution())
		{
			System.out.println(p.getCameFromAction() + " To: "+p.toString());
		}
		System.out.println("Evaluated nodes: "+aStarMaze3dSolution.getNumberOfNodesEvaluated());
		System.out.println("Finished with A* with air distance Search algorithm:");
		System.out.println("");
		aStarMaze3dSolution = (Maze3dSolution)(solveSearchableMazeWithAstarByManhatenDistance(searchableMaze));
		System.out.println("Maze was solved via A* with Manhaten distance, the solution is:");
		for (State<Position> p: aStarMaze3dSolution.getSolution())
		{
			System.out.println(p.getCameFromAction() + " To: "+p.toString());
		}
		System.out.println("Evaluated nodes: "+aStarMaze3dSolution.getNumberOfNodesEvaluated());
		System.out.println("Finished with A* with manhaten distance Search algorithm:");
		System.out.println("");
		System.out.println("**********Demo tests of was finished**********");
		
		
	}
	public static void main(String[] args)
	{
		Demo d = new Demo();
		d.run();
	}
	
}