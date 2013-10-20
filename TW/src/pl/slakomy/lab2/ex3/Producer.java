package pl.slakomy.lab2.ex3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	private static Random rand = new Random();
	private Buffer buffer;
	
	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				buffer.put(produce());
			}
		} catch(InterruptedException e) {
			System.err.println("Producer has been interrupted");
		}
	}
	
	public int produce() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
		return rand.nextInt(buffer.getBufferSize() / 2) + 1;
	}

}
