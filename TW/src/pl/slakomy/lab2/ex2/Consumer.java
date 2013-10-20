package pl.slakomy.lab2.ex2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

	private Random rand = new Random();
	private Semaphore current;
	private Semaphore next;
	private int currentPortion = 0;
	
	public Consumer(Semaphore current, Semaphore next) {
		this.current = current;
		this.next = next;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				current.acquire();
				consume();
				next.release();
			}
		} catch(InterruptedException e) {
			System.err.println("Consumer has been interrupted");
		}
	}
	
	public void consume() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(300 + rand.nextInt(500));
		System.out.format("Consumer consumed portion no %d\n", currentPortion);
		currentPortion = (currentPortion + 1) % Pipelining.BUFFER_SIZE;
	}

}
