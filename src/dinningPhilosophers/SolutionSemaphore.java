package dinningPhilosophers;

import java.util.concurrent.Semaphore;

public class SolutionSemaphore {
	public static Semaphore sem = new Semaphore(1);
	public static final int numberOfPhilosophers = 5;
	
	public static PhilosopherSemaphore[] philosophers = {
			new PhilosopherSemaphore(0, 4000, 6000),
			new PhilosopherSemaphore(1, 3000, 5200),
			new PhilosopherSemaphore(2, 5000, 8000),
			new PhilosopherSemaphore(3, 6000, 6000),
			new PhilosopherSemaphore(4, 8000, 12000)
	};
	
	public static PhilosopherSemaphore getLeft(int i) {
		return philosophers[ (i - 1 + philosophers.length) % philosophers.length ];
	}
	
	public static PhilosopherSemaphore getRight(int i) {
		return philosophers[ (i + 1) % philosophers.length ];
	}
	
	public static void requestToEat(int i) throws InterruptedException {
		sem.acquire();
		if(!getLeft(i).isEating() && !getRight(i).isEating() && philosophers[i].hungry) { 
			philosophers[i].eat();
		}
		else sem.release();
	}
	
	public static void start() {
		
	}
}
