import java.util.*;
/*  This class like a populator which is used to  generate trains infinitely simulating a schedule
 *  or timetable run by the train company. it is passed a track array generated
 *  in the main method below and each run through the infinite while loop 
 *	a new thread is created for a runnable train object which are created in the while loop below*/

public class Runme implements Runnable {

	/*the number of the train used to identify it.*/
	private int trainNumber = 0;

	/* Track Array for trains to be placed on.
	 * This is created in main and passed on to the
	 * the train itself  */
	private LinkedList<Track> line;

	/*this constructor takes a linked list created in main */
	public Runme(LinkedList<Track>line) {
		this.line=line;
	}

	/* Create new Train in new Thread and increase number the integer values are different train 
	speeds, the values are extreme so that you can see clearly in the printout that they go at different speeds*/
	private void TrainSpeed(boolean fast) {
		if (fast) new Thread( new Train(1000, line, trainNumber++)).start();
		else new Thread( new Train(1, line, trainNumber++)).start();
	}

	public void run() {
		//generate trains infinitely
		while (true) {
			TrainSpeed(new Random().nextBoolean());

			try {                                                                    
				/* sleep the thread for random amount of time between 1 and 5
				 * seconds to create a break in between the creation of trains,
				this keeps train print out on the console orderly. */ 
				Thread.sleep(1000+new Random().nextInt(4000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*printing method for the console, it takes the linked list of track created in the main 
	 * goes through each piece's toString and prints it out to the screen in one line and
	 *  repeats the process
	 * */

	static void printToConsole(LinkedList <Track> route) {
		while(true) {
			// print each track section and station of the route out then sleep for half second to stagger print out
			route.forEach(x -> System.out.print(x.toString()));
			System.out.println();
			try{Thread.sleep(500);}catch(InterruptedException e) {} 
		}
	}
	
	// Main method for the program.... main is throwing things too
	public static void main(String args[]){

		/*Linked list for the trains' route form Glasgow to Inverness
		it will store each station and the sections of track in between*/
		LinkedList<Track> route = new LinkedList<Track>();

		//adding the train stations
		String [] names = {"Glasgow","Stirling","Perth","Inverness"};
		for (int i=0;i<names.length;i++) {
			route.add(new Station (names.length-(i-1),names[i]));
		}
		// adding the track between the stations
		for (int i= 1; i<=5;i=i+2) {
			route.add(i,new Track());
		}
		//starting everything
		new Thread(new Runme(route)).start();
		printToConsole(route);//see above
	}	
}
