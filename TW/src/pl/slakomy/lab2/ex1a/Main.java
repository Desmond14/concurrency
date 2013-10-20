package pl.slakomy.lab2.ex1a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

	private static final int BUFFOR_SIZE = 4;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		Semaphore full = new Semaphore(0);
		Semaphore empty = new Semaphore(BUFFOR_SIZE);
		exec.execute(new Producer(full, empty));
		exec.execute(new Consumer(full, empty));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
	
}
