package algorithms.search;
import algorithms.mazeGenerators.Position;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>AirDistance</h1>
 * This class implement interface and defining a method that will generate a double number that represent an air-distance between 2 different States.
 */

public class AirDistance implements Distance {
/**
	 * 
	 */
	private static final long serialVersionUID = -6610117370845769523L;
	//Constructors
	public AirDistance() {
		// TODO Auto-generated constructor stub
	}
//Functionality
	@SuppressWarnings("rawtypes")
	@Override
	/**
	 * This function implements interface Distance method.
	 * This function gets 2 States and returns double number represent the air distance between them.
	 * @param from State represent the edge of the distance.
	 * @param to State represent the end of the distance.
	 * @return double number represent the distance between from and to.      
	 */
	public double getDistance(State from, State to) {
		Position f = (Position) from.getState();
		Position t = (Position) to.getState();
		return Math.sqrt(
				((f).getXPosition()-(t).getXPosition())
				*(f).getXPosition()-(t).getXPosition())
				+
				((f).getYposition()-(t).getYposition())
				*((f).getYposition()-(t).getYposition())
				+
				((f).getZposition()-(t).getZposition())
				*((f).getZposition()-((t).getZposition()));
	}

}
