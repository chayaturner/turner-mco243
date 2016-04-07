package turner.deadlock;

public class Philosopher extends Thread {

	private Fork f1;
	private Fork f2;
	private String name;

	public Philosopher(String name, Fork f1, Fork f2) {
		this.name = name;
		this.f1 = f1;
		this.f2 = f2;
	}

	public void run() {
		while (true) {
			think();
			eat();
		}
	}
	
	public void eat() {
		System.out.println(this + " trying to pick up " + f1);
		synchronized(f1) {
			waitForAFewSeconds(10);
			System.out.println(this + " trying to pick up " + f2);
			synchronized(f2) {
				System.out.println(this + " eating...");
				waitForAFewSeconds(10);
			}
			System.out.println(this + " put down " + f1);
		}
		System.out.println(this + " put down " + f2);
	}
	
	public void think() {
		waitForAFewSeconds(5);
	}

	private void waitForAFewSeconds(int seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + "]";
	}
	
	
}
