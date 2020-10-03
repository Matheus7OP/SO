package dinningPhilosophers;

public class SolutionMonitor {
	public static final int numberOfPhilosophers = 5;
	public static PhilosopherMonitor[] philosophers = {
			new PhilosopherMonitor(0, 4000, 6000),
			new PhilosopherMonitor(1, 3000, 5200),
			new PhilosopherMonitor(2, 5000, 8000),
			new PhilosopherMonitor(3, 6000, 6000),
			new PhilosopherMonitor(4, 8000, 12000)
	};
	
	public static PhilosopherMonitor getLeft(int i) {
		return philosophers[ (i - 1 + philosophers.length) % philosophers.length ];
	}
	
	public static PhilosopherMonitor getRight(int i) {
		return philosophers[ (i + 1) % philosophers.length ];
	}
	
	public static void finishedEating(int i) {
		if( !getLeft( getLeft(i).id ).isEating() ) { 
			synchronized (getLeft(i)) {
				getLeft(i).notify();
			}
		}
		
		if( !getRight( getRight(i).id ).isEating() ) { 
			synchronized (getRight(i)) {
				getRight(i).notify();
			}
		}
	}
	
	public static void requestToEat(int i) throws InterruptedException {
		if(getLeft(i).isEating() || getRight(i).isEating()) { 
			synchronized (philosophers[i]) {
				philosophers[i].wait();
			} 
		}
		else philosophers[i].eat();
	}
	
	public static void start() {
		
	}
}
