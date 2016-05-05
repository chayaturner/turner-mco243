package turner.schedule;

import java.util.Comparator;

public class PriorityJobComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		return a.getPriority().compareTo(b.getPriority());
	}

}
