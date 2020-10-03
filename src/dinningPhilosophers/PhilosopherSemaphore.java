package dinningPhilosophers;

public class PhilosopherSemaphore implements Runnable {
	public int thinkingTime, eatingTime, eating, id;
	public boolean hungry;
	
	PhilosopherSemaphore(int id, int thinkingTime, int eatingTime) {
		this.thinkingTime = thinkingTime;
		this.eatingTime = eatingTime;
		this.eating = 0;
		this.id = id;
		this.hungry = false;
		new Thread(this, "Philosopher " + id).start();
	}

	public boolean isEating() {
		return (this.eating == 1);
	}
	
	private void requestToEat() throws InterruptedException {
		SolutionSemaphore.requestToEat(id);
	}
	
	public void eat() throws InterruptedException {
		System.out.println("Philosopher " + id + " will now EAT for " + eatingTime + " ms.");
		eating = 1;
		SolutionSemaphore.sem.release();
		Thread.sleep(eatingTime);
		eating = 0;
		hungry = false;
		System.out.println("Philosopher " + id + " finished EAT action.");
	}
	
	private void think() throws InterruptedException {
		System.out.println("Philosopher " + id + " will now THINK for " + thinkingTime + " ms.");
		Thread.sleep(thinkingTime);
		System.out.println("Philosopher " + id + " finished THINK action.");
		hungry = true;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(!hungry) think();
				requestToEat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
