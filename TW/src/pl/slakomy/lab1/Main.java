package pl.slakomy.lab1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static volatile int number = 0;

	public static void main(String[] args) throws InterruptedException {
		//semaphoreUse();
		badSemaphoreUse();
	}
	
	public static void semaphoreUse() throws InterruptedException{
		System.out.println("***Decrementer and Incrementer with BinarySemaphore***");
		BinarySemaphore semaphore = new BinarySemaphore(false);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Decrementer(semaphore));
		exec.execute(new Incrementer(semaphore));
		exec.shutdown();
		while(!exec.isTerminated())
			TimeUnit.SECONDS.sleep(1);
		System.out.println("Result: " + number);
	}
	
	public static void badSemaphoreUse() throws InterruptedException {
		BadSemaphore semaphore = new BadSemaphore(false);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Acquirer(semaphore));
		exec.execute(new Acquirer(semaphore));
		exec.execute(new Releaser(semaphore));
		exec.execute(new Releaser(semaphore));
		exec.execute(new Releaser(semaphore));
		exec.execute(new Releaser(semaphore));
		TimeUnit.MILLISECONDS.sleep(100);
		exec.shutdownNow();
	}
}
