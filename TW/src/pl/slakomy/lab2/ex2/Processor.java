package pl.slakomy.lab2.ex2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Processor implements Runnable {
	
	private static Random rand = new Random();
	private Semaphore current;
	private Semaphore next;
	
	public Processor(Semaphore current, Semaphore next) {
		this.current = current;
		this.next = next;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				current.acquire();
				processNextPortion();
				next.release();
			}
		} catch(InterruptedException e) {
			System.err.println("Process has been interrupted");
		}
	}
	
	public void processNextPortion() throws InterruptedException{
		TimeUnit.SECONDS.sleep(rand.nextInt(1000));
	}

}
