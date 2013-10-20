package pl.slakomy.lab2.ex1b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static int productsInBuffer = 0;
	static int bufferSize = 3;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		Object bufferDummy = new Object();
		exec.execute(new Consumer(bufferDummy));
		exec.execute(new Producer(bufferDummy));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
		
	}
}
