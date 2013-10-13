package pl.slakomy.lab1;

public class CountingSemaphore {
	
	private int semaphoreValue;
	private BinarySemaphore access = new BinarySemaphore(false);
	private BinarySemaphore wait = new BinarySemaphore(true);
	
	
	public CountingSemaphore(int initialValue) {
		semaphoreValue = initialValue;
	}
	
	public void acquire() throws InterruptedException {
		access.acquire();
		semaphoreValue--;
		if (semaphoreValue < 0) {
			access.release();
			wait.acquire();
		} 
		access.release();
	}
	
	public void release() throws InterruptedException {
		access.acquire();
		semaphoreValue++;
		if (semaphoreValue <= 0)
			wait.release();
		else
			access.release();
	}

}
