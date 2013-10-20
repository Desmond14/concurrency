package pl.slakomy.lab2.ex2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Pipelining {

	private final static int NUMBER_OF_PROCESSES = 3;
	final static int BUFFER_SIZE = 4;
	private static Semaphore[] semaphores = new Semaphore[NUMBER_OF_PROCESSES + 1];
	
	public static void main(String[] args) throws InterruptedException {
		
		semaphores[0] = new Semaphore(BUFFER_SIZE);
		for (int i = 1; i < semaphores.length; i++)
			semaphores[i] = new Semaphore(0);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < NUMBER_OF_PROCESSES; i++)
			exec.execute(new Processor(semaphores[i], 
					semaphores[i+1]));
		exec.execute(new Consumer(semaphores[semaphores.length - 1], semaphores[0]));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
