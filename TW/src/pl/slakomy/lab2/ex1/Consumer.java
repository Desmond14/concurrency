package pl.slakomy.lab2.ex1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

	Semaphore full;
	Semaphore empty;
	Random rand = new Random();
	
	public Consumer(Semaphore full, Semaphore empty) {
		this.full = full;
		this.empty = empty;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted())
				consume();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Consumer has been interrupted!");
		}
	}
	
	public void consume() throws InterruptedException {
		full.acquire();
		//taking product from a container goes here
		empty.release();
		TimeUnit.MILLISECONDS.sleep((rand.nextInt(1000)));	//time required to consume
	}

}
