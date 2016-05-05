package turner.schedule;

import java.util.ArrayList;

public class RoundRobinJobScheduler extends JobScheduler {

	public RoundRobinJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new FifoJobComparator());
	}

	@Override
	public void run() {

		while (!jobs.isEmpty()) {
			Job job = jobs.remove(0);
			int actualTimeSlice = executeJob(job);
			totalTime += actualTimeSlice;

			if (!job.isFinished()) {
				jobs.add(job);
			}
		}
	}
}
