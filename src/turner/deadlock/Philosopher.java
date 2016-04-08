package turner.deadlock;

import java.util.logging.Logger;

public class Philosopher extends Thread {

	private static final Logger LOG = Logger.getLogger(Philosopher.class.getName());
	private Fork f1;
	private Fork f2;
	private String name;
	private Waiter waiter;

	public Philosopher(String name, Waiter waiter, Fork f1, Fork f2) {
		this.name = name;
		this.waiter = waiter;
		this.f1 = f1;
		this.f2 = f2;
	}

	@Override
	public void run() {
		while (true) {
			think();
			eat();
		}
	}

	public void eat() {

		LOG.info(this.toString() + " attempting to eat");
		if (waiter.tryEat(f1, f2)) {
			LOG.info(this.toString() + " eating");
			waitForAFewSeconds(10);
			f1.setInUse(false);
			f2.setInUse(false);
		}

		LOG.info(this.toString() + " done eating");

	}

	public void think() {
		waitForAFewSeconds(5);
	}

	private void waitForAFewSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + "]";
	}

}
