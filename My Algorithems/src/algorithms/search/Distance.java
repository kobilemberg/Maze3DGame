package algorithms.search;



/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>Distance</h1>
 * This interface defining a generally Instance between 2 different Positions.
 * All the classes that will implements this interface will have to implement getDistance method.
 */

public interface Distance {
	 /**
	 * This function gets 2 States and returns double number represent the distance between them.
	 * @param from State represent the edge of the distance.
	 * @param to State represent the end of the distance.
	 * @return double number represent the distance between from and to.      
	 */
	@SuppressWarnings("rawtypes")
	public double getDistance(State from, State to);

}
