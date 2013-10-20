package pl.slakomy.lab2.ex1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	Semaphore full;
	Semaphore empty;
	Random rand = new Random();
	
	public Producer(Semaphore full, Semaphore empty) {
		this.full = full;
		this.empty = empty;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				produce();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
			System.err.println("Producer has been interrupted");
		}
	}
	
	public void produce() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt( 1000));	//time required to produce
		empty.acquire();
		//placing product in a container goes here
		full.release();
	}
	
}
