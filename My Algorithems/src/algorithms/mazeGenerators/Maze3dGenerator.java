package algorithms.mazeGenerators;
/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> Maze3dGenerator </h1>
 * This interface Maze3dGenerator represent an 3D maze generator with given given floors, lines and columns.
 * all implements classes will have to implement generate and measureAlgorithmTime methods.
 */
public interface Maze3dGenerator {
	/**
	 * The goal is to generate a new search-able maze3d with given floors, lines and columns
	 *@param floor integer that represent the number of floors to set 
	 *@param lines integer that represent the number of lines to set
	 *@param cols integer that represent the number of columns to set
	 *@return Maze3d an instance, that contains the maze with the given floors, lines and columns          
	 */
	public Maze3d generate(int floors, int lines, int cols);
	/**
	 * This method goal is to return the measuring time to generate maze by some generation algorithm
	 *@param x integer that represent the number of floors to set 
	 *@param y integer that represent the number of lines to set
	 *@param z integer that represent the number of columns to set
	 *@return String that represent the time to create the maze with specific algorithm.          
	 */
	public String measureAlgorithmTime(int x,int y, int z);

}
