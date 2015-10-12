package algorithms.mazeGenerators;

//import algorithms.search.AirDistance;
//import algorithms.search.BFSCommonSearcher;
//import algorithms.search.Maze3dSolution;
//import algorithms.search.Solution;

//This is my test class, created by with spirit from your class "Run"

public class tests {

	public tests() {
		// TODO Auto-generated constructor stub
	}
	/*
	private static void testMazeGenerator(Maze3dGenerator mg)
	{
		
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(3, 4, 5));
		// generate another 3d maze
		Maze3d maze=mg.generate(3,4,5);
		// get the maze entrance
		System.out.println(maze.toString());
		
		Position p=maze.getStartPosition();
		// print the position
		System.out.println("Start position of the maze is:"+p); // format "{x,y,z}"
		// get all the possible moves from a position
		String[] moves=maze.getPossibleMoves(p);
		// print the moves
		System.out.println("Start position can do the following moves:");
		for(String move : moves)
		System.out.println("	Move: "+move);
		// prints the maze exit position
		System.out.println("Goal position is:"+maze.getGoalPosition());
		try{
		// get 2d cross sections of the 3d maze
			int[][] maze2dx=maze.getCrossSectionByX(2);
			System.out.println("Cross section by X array[][]:");
			printArr(maze2dx);
			// TODO add code to print the array
		
			int[][] maze2dy=maze.getCrossSectionByY(5);
			System.out.println("Cross section by y array[][]:");
			printArr(maze2dy);
			// TODO add code to print the array
			int[][] maze2dz=maze.getCrossSectionByZ(0);
			System.out.println("Cross section by Z array[][]:");
			printArr(maze2dz);
			// TODO add code to print the array
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
			} catch (IndexOutOfBoundsException e)
			{
			System.out.println("good!, there was an exception:"+e.getMessage());
		
			}
		}
		public static void printArr(int[][] arr)
		{
			String strOfMazeMatrix="";
			for (int i=0;i<arr.length;i++)
			{
				strOfMazeMatrix+="{";
				for(int j=0;j<arr[0].length;j++)
				{
							strOfMazeMatrix+=arr[i][j];
				}
				strOfMazeMatrix+="}\n";
			}
			System.out.println(strOfMazeMatrix);
		}
		public static void main(String[] args)
		{	
				//Test all things!!!
				testMazeGenerator(new SimpleMaze3dGenerator());
				System.out.println("**********Testing Search algorithms with Simple Maze 3d Generator**********");
				SimpleMaze3dGenerator mg = new SimpleMaze3dGenerator();
				Maze3d s = mg.generate(3,4,5);
				System.out.println("Generated Maze:\n"+s.toString());
				System.out.println("Start:"+ s.getStartPosition());
				System.out.println("Goal:" + s.getGoalPosition());
				System.out.println("");
				System.out.println("Starting with BFS Search algorithm:");
				BFSCommonSearcher bfs = new BFSCommonSearcher(new Maze3dSolution());
				System.out.println("Created BFS");
				System.out.println("Searching with BFS........");
				Maze3dSolution maze3dSolutionBFS =  (Maze3dSolution) bfs.search(s);
				for (Position p: maze3dSolutionBFS.getSolution())
				{
					System.out.println(p.getCameFromAction() + " To: "+p.toString());
				}
				System.out.println("Evaluated nodes: "+bfs.getNumberOfNodesEvaluated());
				System.out.println("Finished with BFS Search algorithm:");
				System.out.println("");
				System.out.println("Starting with A* Search algorithm:");
				Astar aStar = new Astar(new Maze3dSolution(),new AirDistance());
				System.out.println("Created A*");
				System.out.println("Searching with A*........");
				Maze3dSolution maze3dSolutionAStar =  (Maze3dSolution) aStar.search(s);
				for (Position p: maze3dSolutionAStar.getSolution())
				{
					System.out.println(p.getCameFromAction() + " To: "+p.toString());
				}
				System.out.println("Evaluated nodes: "+aStar.getNumberOfNodesEvaluated());
				System.out.println("Finished with A* Search algorithm:");
				System.out.println("");
				System.out.println("**********Finish to test Search algorithms**********");
				System.out.println("**********Comparing 2 algorithms results**********");
				System.out.println("Are algorithms Solutions Even: "+(maze3dSolutionAStar.toString().equals(maze3dSolutionBFS.toString())));
				System.out.println("Astar tested: "+(aStar.getNumberOfNodesEvaluated())+", BFS tested: "+(bfs.getNumberOfNodesEvaluated()));
				System.out.println("");
				System.out.println("*******************************************************************************");
				System.out.println("");
				System.out.println("**********Testing Search algorithms with My Maze 3d Generator**********");
				MyMaze3dGenerator myMG = new MyMaze3dGenerator();
				Maze3d sNew = myMG.generate(3,4,5);
				System.out.println("Generated Maze:\n"+sNew.toString());
				System.out.println("Start:"+ sNew.getStartPosition());
				System.out.println("Goal:" + sNew.getGoalPosition());
				System.out.println("");
				System.out.println("Starting with BFS Search algorithm:");
				BFSCommonSearcher bfsNew = new BFSCommonSearcher(new Maze3dSolution());
				System.out.println("Created BFS");
				System.out.println("Searching with BFS........");
				Maze3dSolution maze3dSolutionBFSNew =  (Maze3dSolution) bfsNew.search(sNew);
				for (Position p: maze3dSolutionBFSNew.getSolution())
				{
					System.out.println(p.getCameFromAction() + " To: "+p.toString());
				}
				System.out.println("Evaluated nodes: "+bfsNew.getNumberOfNodesEvaluated());
				System.out.println("Finished with BFS Search algorithm:");
				System.out.println("");
				System.out.println("Starting with A* Search algorithm:");
				Astar aStarNew = new Astar(new Maze3dSolution(),new AirDistance());
				System.out.println("Created A*");
				System.out.println("Searching with A*........");
				Maze3dSolution maze3dSolutionAStarNew =  (Maze3dSolution) aStarNew.search(sNew);
				for (Position p: maze3dSolutionAStarNew.getSolution())
				{
					System.out.println(p.getCameFromAction() + " To: "+p.toString());
				}
				System.out.println("Evaluated nodes: "+aStarNew.getNumberOfNodesEvaluated());
				System.out.println("Finished with A* Search algorithm:");
				System.out.println("");
				System.out.println("**********Finish to test Search algorithms**********");
				System.out.println("**********Comparing 2 algorithms results**********");
				System.out.println("Are algorithms Solutions Even: "+(maze3dSolutionAStarNew.toString().equals(maze3dSolutionBFSNew.toString())));
				System.out.println("Astar tested: "+(aStarNew.getNumberOfNodesEvaluated())+", BFS tested: "+(bfsNew.getNumberOfNodesEvaluated()));
				System.out.println("");
				System.out.println("*******************************************************************************");
				System.out.println("");
		
		}
*/		

}
