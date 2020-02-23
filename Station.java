/*This class just extends track and gives alters some the variables in the parent class
 * to reflect the different attributes a station has in relation to a piece of track */
public class Station extends Track {
	public Station(int capacity, String name) { 
		length = 100;
		this.capacity = capacity;
		this.name = name;
	}	
}






