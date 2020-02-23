import java.util.LinkedList;
/*objects of this class are created inside the next train class to generate a new trains
the values for the variables train speed and number along with a linked list of track pieces*/

public class Train implements Runnable {

	//Train variables 
	private int speed;
	private Integer number;
	private LinkedList<Track> trainLine;
	// the train speed and the train line is passed from the Next Train Class
	public Train(int speed, LinkedList<Track> trainLine, int number) {
		//initialise the train variables
		this.speed = speed;
		this.trainLine = trainLine;
		this.number = number;
	}
	
	public void run() {

		// loops through the whole line of track and train sections 
		//adding the train to and removing it from each one as it goes
		for (Track tr:trainLine) {

			/* add this train to the correct section
				   the lock will be activated here to add the train*/
			tr.addTrain(this);
			// sleep this train for the length of journey time
			// to simulate travel time
			try {
				Thread.sleep(getDuration(tr));
			} catch (InterruptedException e) {}					
			//remove the train at the end of its run
			tr.removeTrain(this);			
		}
	}
	
	/*method to get the duration time, private because it only accessed
	 *  from the run() method of this class
	 */
	private int getDuration (Track segment) {
		return (segment.name=="track")   ?   segment.length/speed  :  (segment.length/speed)+5000;
	}
	// returns the number of the train for the print-out
	@Override
	public String toString() {
		return number.toString() ;
	}
}
