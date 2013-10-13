package pl.slakomy.lab1;

public class Incrementer implements Runnable {
	
	BinarySemaphore semaphore;
	
	public Incrementer(BinarySemaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10000000; i++) {
				semaphore.acquire();
				Main.number++;
				semaphore.release();
			}
		} catch(InterruptedException e) {
			System.out.println(e);
		}
	}

}
