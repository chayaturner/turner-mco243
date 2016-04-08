package turner.deadlock;

// Philosophers must ask a waiter before picking up forks
public class Waiter {

	// return true if successfully pick up forks, otherwise return false
	public synchronized boolean tryEat(Fork a, Fork b) {
		if (!a.isInUse() && !b.isInUse()) {
			a.setInUse(true);
			b.setInUse(true);
			return true;
		}
		return false;
	}

}
