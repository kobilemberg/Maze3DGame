package algorithms.search;

import algorithms.mazeGenerators.Position;



/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>ManhatenDistance</h1>
 * This class implement interface and defining a method that will generate a double number that represent a Manhaten distance between 2 different Positions.
 */
public class ManhatenDistance implements Distance {
//Constructor
	public ManhatenDistance() {}
//Functionality
	@SuppressWarnings("rawtypes")
	@Override
	 /**
	 * 
	 * This function implements interface Distance method.
	 * This function gets 2 States and returns double number represent the distance of Manhaten between them.
	 * @param from State represent the edge of the distance.
	 * @param to State represent the end of the distance.
	 * @return double number represent the distance between from and to.      
	 */
	public double getDistance(State from, State to) {
		Position f = (Position) from.getState();
		Position t = (Position) to.getState();
		return Math.abs(t.getXPosition()-f.getXPosition())
				+Math.abs(t.getYposition()-f.getYposition())
				+Math.abs(t.getZposition()-f.getZposition());
	}

}
