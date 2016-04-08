package turner.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock {

	private static final long serialVersionUID = 1L;

	private int number;
	private boolean inUse;

	public void setNumber(int number) {
		this.number = number;
	}

	public Fork(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Fork [number=" + number + "]";
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
}
