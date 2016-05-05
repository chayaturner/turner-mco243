package turner.schedule;

import java.util.Comparator;

public class DeadlineJobComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		return a.getDeadline().compareTo(b.getDeadline());
	}
}
