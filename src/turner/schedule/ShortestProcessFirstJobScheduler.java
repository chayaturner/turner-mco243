package turner.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShortestProcessFirstJobScheduler extends JobScheduler {

	private Comparator<Job> comparator;

	public ShortestProcessFirstJobScheduler(ArrayList<Job> jobs, Comparator<Job> comparator) {
		super(jobs, comparator);
		this.comparator = comparator;
	}

	@Override
	public void run() {
		Job lastJob = null;
		while (!jobs.isEmpty()) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);
			int actualTimeSlice = executeJob(job);

			totalTime += actualTimeSlice;

			if (job != lastJob) {
				totalTime += OVERHEAD;
				lastJob = job;
			}
		}
	}
}