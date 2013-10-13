package pl.slakomy.lab1;

public class BinarySemaphore {
	
	private boolean acquired = false;
	
	public BinarySemaphore(boolean initialState) {
		acquired = initialState;
	}
	
	public synchronized void acquire() throws InterruptedException {
		while(acquired)
			wait();
		if(acquired)
			System.out.println("Illegal state detected");
		acquired = true;
	}
	
	public synchronized void release() {
		acquired = false;
		notify();
	}
}
