package turner.mco243;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadLotsOfImages {

	static AtomicInteger total = new AtomicInteger(0); //does the same as synchronized
	//can only be accessed by one thread at a time.

	public static void add() {
		total.incrementAndGet();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(6); //create thread pool

		for (int i = 0; i < 10000; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					add();
				}
			};

			service.execute(runnable); // service has a queue of runnables. When it
										// finishes, gives it another runnable
										// to use. (in each loop)

		}

		service.shutdown(); //tells service to complete everything in
							// queue and then shut down all threads/runnables

		// shuts down service when all runnables finish executing.
		// It doesn’t block out thread though. You can block thread with
		// thread.sleep (for a specific amount of time.) Or: awaitTermination

		service.awaitTermination(10, TimeUnit.SECONDS); // wait for the shutdown
														// to occur
		// blocks until all tasks complete execution after a shutdown request.

		System.out.println(total);

		Thread.sleep(5000);

		System.out.println(total);
	}

}
