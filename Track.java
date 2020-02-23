import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*This class creates pieces of track and is also responsible for the system of determining
 * where trains are allowed to move from one piece of track of a station to the next*/

public class  Track {
	// ... These vars are protected so they can be easily accessed w/o getters from within this package
	//length in metres, protected so that it can be changed by inheriting classes (stations)
	//capacity can be changed for stations 
	//name can be changed for stations
	protected int length = 5000;
	protected int capacity = 1;
	protected String name = "track";

	// Linked list to store references to trains in track section
	// integer to keep track of trains on the track
	private LinkedList<Train> trainList = new LinkedList<Train>();
	private int trainsOnTrack = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition full = lock.newCondition();

	// add a train to the track section
	public void addTrain(Train tr) {
		// get the lock
		lock.lock();
		try {
			/*condition that causes train to be held up if the station or piece of track is at capacity*/
			while (trainsOnTrack == capacity) {
				full.await();
			}
			// add the train to the array list of trains in this section
			trainList.add(tr);
			trainsOnTrack +=1;

		} catch (InterruptedException e) {}
		//release the lock
		lock.unlock();
	}
	// remove a train from this section
	public void removeTrain(Train tr) {
		//get the lock
		lock.lock();
		// remove the train t from the array list
		trainList.remove(tr);
		trainsOnTrack-=1;
		// signal the next train
		full.signalAll();
		// release the lock
		lock.unlock();
	}

	// this toString creates the display on the console for individual pieces of track
	public String toString() {		
		return "|---- "+ name + trainList + "----|";
	}
}
