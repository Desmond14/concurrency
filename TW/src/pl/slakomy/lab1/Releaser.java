package pl.slakomy.lab1;

public class Releaser implements Runnable {

	private BadSemaphore semaphore;
	
	public Releaser(BadSemaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()) {
			semaphore.release();
		}	
		System.out.println("Releaser leaves run() method");
	}

}
