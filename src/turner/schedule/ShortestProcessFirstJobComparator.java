package turner.schedule;

import java.util.Comparator;

public class ShortestProcessFirstJobComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		return a.getTimeLeftToRun().compareTo(b.getTimeLeftToRun());
	}
}