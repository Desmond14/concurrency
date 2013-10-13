package pl.slakomy.lab1;

public class Decrementer implements Runnable {

	private BinarySemaphore semaphore;
	
	public Decrementer(BinarySemaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10000000; i++) {
				semaphore.acquire();
				Main.number--;
				semaphore.release();
			}
		} catch(InterruptedException e) {
			System.out.println(e);
		}
	}
}
