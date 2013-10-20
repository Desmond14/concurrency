package pl.slakomy.lab2.ex1b;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	
	private Object sharedBuffer;
	private Random rand = new Random();
	
	public Consumer(Object sharedBuffer) {
		this.sharedBuffer = sharedBuffer;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				consume();
			}
		} catch(InterruptedException e) {
			System.err.println("Consumer has been interrupted");
		}
	}
	
	public void consume() throws InterruptedException {
		synchronized(sharedBuffer) {
			while(Main.productsInBuffer == 0)
				sharedBuffer.wait();
			//taking product from buffer goes here
			Main.productsInBuffer--;
			System.out.format("Removed product from buffer. Currently in buffer: %d\n",
					Main.productsInBuffer);
			sharedBuffer.notify();
		}
		//Time required to consume:
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
	}

}
