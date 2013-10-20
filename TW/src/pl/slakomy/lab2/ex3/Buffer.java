package pl.slakomy.lab2.ex3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

	private static final int DEFAULT_BUFFER_SIZE = 8;
	private int bufferSize = DEFAULT_BUFFER_SIZE;
	private int freeSlots = bufferSize;

	
	private Object firstConsumerMonitor = new Object();
	private Object firstProducerMonitor = new Object();
	private Lock consumers = new ReentrantLock(true);
	private Lock producers = new ReentrantLock(true);
	
	public void put(int itemsNumber) throws InterruptedException {
		producers.lock();
		try {
			while(freeSlots < itemsNumber)
				synchronized(firstProducerMonitor) {
					firstProducerMonitor.wait();
				}
			
			freeSlots = freeSlots - itemsNumber;	
			//Placing new items in the buffer
			System.out.format("%d items were successfully put to buffer\n",
					itemsNumber);
			synchronized(firstConsumerMonitor) {
				firstConsumerMonitor.notify();
			}
		} finally {
			producers.unlock();
		}
	}
		
	public void get(int itemsNumber) throws InterruptedException{
		consumers.lock();
		try {
			while (bufferSize - freeSlots < itemsNumber) {
				synchronized(firstConsumerMonitor) {
					firstConsumerMonitor.wait();
				}
			}
			
			freeSlots += itemsNumber;
			//taking products from buffer goes here
			System.out.format("%d items were successfully got from buffer\n",
					itemsNumber);
			synchronized(firstProducerMonitor) {
				firstProducerMonitor.notify();
			}
			
			
		} finally {
			consumers.unlock();
		}
	}
	
	public int getBufferSize() {
		return bufferSize;
	}
	
}
