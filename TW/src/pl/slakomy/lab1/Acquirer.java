package pl.slakomy.lab1;

public class Acquirer implements Runnable {

	private BadSemaphore semaphore;
	
	public Acquirer(BadSemaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				semaphore.acquire();
			}
		} catch(InterruptedException e) {
			System.out.println("Acquirer has been interrupted");
		}
		System.out.println("Acquirer leaves run() method");
	}
}
