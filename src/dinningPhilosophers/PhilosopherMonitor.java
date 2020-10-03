package dinningPhilosophers;

public class PhilosopherMonitor implements Runnable {
	public int thinkingTime, eatingTime, eating, id;
	
	PhilosopherMonitor(int id, int thinkingTime, int eatingTime) {
		this.thinkingTime = thinkingTime;
		this.eatingTime = eatingTime;
		this.eating = 0;
		this.id = id;
		new Thread(this, "Philosopher " + id).start();
	}

	public boolean isEating() {
		return (this.eating == 1);
	}
	
	private void requestToEat() throws InterruptedException {
		SolutionMonitor.requestToEat(id);
		eat();
	}
	
	public void eat() throws InterruptedException {
		System.out.println("Philosopher " + id + " will now EAT for " + eatingTime + " ms.");
		eating = 1;
		Thread.sleep(eatingTime);
		eating = 0;
		SolutionMonitor.finishedEating(id);
		System.out.println("Philosopher " + id + " finished EAT action.");
	}
	
	private void think() throws InterruptedException {
		System.out.println("Philosopher " + id + " will now THINK for " + thinkingTime + " ms.");
		Thread.sleep(thinkingTime);
		System.out.println("Philosopher " + id + " finished THINK action.");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				think();
				requestToEat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
