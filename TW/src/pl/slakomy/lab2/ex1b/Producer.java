package pl.slakomy.lab2.ex1b;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	private Object sharedBuffer;
	private Random rand = new Random();
	
	public Producer(Object sharedBuffer) {
		this.sharedBuffer = sharedBuffer;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				produce();
			}
		} catch(InterruptedException e) {
			System.err.println("Producer has been interrupted");
		}
	}
	
	public void produce() throws InterruptedException {
		//time required to produce product:
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
		
		synchronized(sharedBuffer) {
			while(Main.productsInBuffer == Main.bufferSize) {
				sharedBuffer.wait();
			}
			//placing new product in a container goes here
			Main.productsInBuffer++;
			System.out.format("Added product to buffer. Currrently in buffer: %d\n",
					Main.productsInBuffer);
			sharedBuffer.notify();
		}	
	}
		
}
