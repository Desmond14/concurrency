package pl.slakomy.lab1;

public class BadSemaphore {
	
	private boolean acquired = false;
	
	public BadSemaphore(boolean initialState) {
		acquired = initialState;
	}
	
	public synchronized void acquire() throws InterruptedException {
		if(acquired)
			wait();
		if(acquired)
			System.out.println("Illegal state detected!");
		acquired = true;
	}
	
	public synchronized void release() {
		acquired = false;
		notify();
	}
	
}