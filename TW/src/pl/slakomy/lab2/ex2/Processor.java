package pl.slakomy.lab2.ex2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Processor implements Runnable {
	
	private static int counter = 0;
	private int id = counter++;
	private static Random rand = new Random();
	private Semaphore current;
	private Semaphore next;
	private int currentPortion = 0;
	
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
		TimeUnit.MILLISECONDS.sleep(300 + rand.nextInt(500));
		System.out.format("Processor no %d processed portion no %d\n",
				id, currentPortion);
		currentPortion = (currentPortion + 1) % Pipelining.BUFFER_SIZE;
		
	}

}
