package algorithms.search;

import java.io.Serializable;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1>State<T></h1>
 * This holder class defining a generally state that holdes an object.
 */

@SuppressWarnings({ "rawtypes", "serial" })
public class State<T> implements Comparable<State>,Serializable
{
	private T state;
	protected double cost;
	protected State<T> fatherPosition;
	protected String cameFromAction;

//CTORS	
	/**
	 * Instantiates a new State with cost = 0
	 *@return State as instance, that contains cost as 0.
	 */
	public State() 
	{
		this.cost=0;
	}
	
	/**
	 *Instantiates a new State with given the following values:
	 *Object to hold,price of position "movement" , Father of this position and Came from direction.  
	 *@param state T that represent the object to hold 
	 *@param cost double that represent the price of position "movement" 
	 *@param Father State<T> that represent the Father of this State
	 *@param cameFromAction String that represent the Came from direction, I.E "Left" meaning is that his father-State located right.  
	 *@return State<t> as instance with given values.        
	 */
	public State(T state,double cost, State<T> father, String cameFromAction) {
		this.state=state;
		this.cost = cost;
		this.fatherPosition = father;
		this.cameFromAction = cameFromAction;
	}
	public State(T state) {
		this.state=state;
		this.cost = 0;
	}
	/**
	 * Copy constructor that Instantiates a new State<t> with given other State<t>
	 *@param other State<t> type in order to copy his values. 
	 *@return State<t> an instance, that contains the other State<t> settings 
	 */
	public State(State<T> other) {
		this.cost = other.cost;
		this.fatherPosition = other.fatherPosition;
		this.cameFromAction = other.cameFromAction;
		this.state=other.state;
	}
//Getters and setters
	/**
	 *@return double represent the price of position "movement". 
	 */
	public double getCost() {return cost;}
	/**
	 *This method will set the the price of position "movement".
	 *@param cost double represent the price of position "movement".
	 */
	public void setCost(double cost) {this.cost = cost;}
	/**
	 *@return Position instance of the current position father.
	 */
	public State<T> getFather() {return fatherPosition;}
	/**
	 *@return T Represent the object that the state is holding.
	 */
	public T getState() {return state;}
	/**
	 *This method will set the the object that the state is holding.
	 *@param state T represent the object that the state is need to hold..
	 */
	public void setState(T state) {this.state = state;}
	/**
	 *This method will set the current State<T> father.
	 *@param father State<T> represent the father State<T> to set.
	 */
	public void setFather(State<T> father) {this.fatherPosition = father;}
	/**
	 *@return String that represent the Came from direction, I.E "Left" meaning is that his father-State<T> located right.
	 */
	public String getCameFromAction() {return cameFromAction;}
	/**
	 *This method will set the State<T> came from action, String that represent the Came from direction, I.E "Left" meaning is that his father-State<T> located right. 
	 *@param cameFromAction String that represent the Came from direction, I.E "Left" meaning is that his father-State<T> located right.
	 */
	public void setCameFromAction(String cameFromAction) {this.cameFromAction = cameFromAction;}
//Override Methods
	@Override
	/**
	 *@return int represent the hashCode of T.
	 */
	public int hashCode() {return this.state.hashCode();}


	@SuppressWarnings("unchecked")
	@Override
	/**
	 *@return True if the states are equal.
	 *@return False if the states are not equal.
	 */
	public boolean equals(Object obj) {return this.state.equals((State<T>)obj);}
	/**
	 *@return String of the position values
	 */
	@Override
	public String toString() {return state.toString();}
	/**
	 * This abstract method returns the required move to do in order to go to another position as a String.
	 * The method is abstract because the returning answer is different for each domain(Graph, Maze, etc')
	 *@param p Position represent the position that we need to go to.
	 *@return String represent the required move to do in order to go to p
	 */


	@Override
	public int compareTo(State arg0) {
		if (cost<arg0.cost)
			return -1;
		else if(cost>arg0.cost)
			return 1;
		return 0;
	}
	
	
	


}
