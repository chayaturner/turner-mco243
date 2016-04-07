package turner.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock {

	private static final long serialVersionUID = 1L;

	private int number;

	public Fork(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Fork [number=" + number + "]";
	}

}
