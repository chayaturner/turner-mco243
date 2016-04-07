package turner.deadlock;

import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {

	private Fork f1;
	private Fork f2;
	private String name;

	public Philosopher(String name, Fork f1, Fork f2) {
		this.name = name;
		this.f1 = f1;
		this.f2 = f2;
	}

	@Override
	public void run() {
		while (true) {
			think();
			try {
				eat();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void eat() throws InterruptedException {
		// if more than one fork available, pick up
		// pick up fork if have one and need one more to eat.
		// if no fork, wait.

		System.out.println(this + " trying to pick up " + f1);

		f1.tryLock(10, TimeUnit.SECONDS);
		System.out.println(this + " trying to pick up " + f2);
		f2.tryLock(10, TimeUnit.SECONDS);

		System.out.println(this + " eating");

		if (f1.isLocked()) {
			f2.unlock();
			System.out.println(this + " put down " + f1);
		}
		if (f2.isLocked()) {
			f1.unlock();
			System.out.println(this + " put down " + f2);
		}
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
