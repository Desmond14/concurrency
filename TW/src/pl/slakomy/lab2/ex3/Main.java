package pl.slakomy.lab2.ex3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Buffer buffer = new Buffer();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Producer(buffer));
		exec.execute(new Producer(buffer));
		exec.execute(new Consumer(buffer));
		exec.execute(new Consumer(buffer));
		exec.execute(new Producer(buffer));
		exec.execute(new Consumer(buffer));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();

	}

}
