package pl.slakomy.lab2.ex2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Pipelining {

	private final static int NUMBER_OF_PROCESSES = 5;
	final static int BUFFER_SIZE = 7;
	private static Semaphore[] semaphores = new Semaphore[NUMBER_OF_PROCESSES];
	
	public static void main(String[] args) throws InterruptedException {
		
		semaphores[0] = new Semaphore(BUFFER_SIZE);
		for (int i = 1; i < NUMBER_OF_PROCESSES; i++)
			semaphores[i] = new Semaphore(0);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < NUMBER_OF_PROCESSES; i++)
			exec.execute(new Processor(semaphores[i], 
					semaphores[(i+1) % NUMBER_OF_PROCESSES]));
		
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
