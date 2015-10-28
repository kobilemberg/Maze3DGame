package algorithms.mazeGenerators;
/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> AbsMaze3dGenerator </h1>
 * The Abstract Class AbsMaze3dGenerator implements Maze3dGenerator.
 * all extends classes will have to implement generate method that will generate a new maze domain with given floors, lines and columns.
 */

public abstract class AbsMaze3dGenerator implements Maze3dGenerator {

	/**
	 * Instantiates a new abstract maze3d generator.
	 */
	public AbsMaze3dGenerator() {

	}

	/**
	 * Generate a new search-able maze3d with given floors, lines and columns
	 *@param floor integer that represent the number of floors to set 
	 *@param lines integer that represent the number of lines to set
	 *@param cols integer that represent the number of columns to set
	 */
	@Override
	public abstract Maze3d generate(int floors, int lines, int cols);
	
	/**
	 * This method measuring the time to generate maze by some generation algorithm
	 *@param x integer that represent the number of floors to set 
	 *@param y integer that represent the number of lines to set
	 *@param z integer that represent the number of columns to set
	 *@return String that represent the time to create the maze with specific algorithm.          
	 */
	@Override
	public String measureAlgorithmTime(int x,int y, int z) {
		long a = System.currentTimeMillis();
		this.generate(x, y, z);
		a= System.currentTimeMillis()-a;
		System.out.println("The time to create a new maze was:");
		return ""+a;
	}

}
