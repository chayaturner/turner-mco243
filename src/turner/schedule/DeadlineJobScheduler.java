package turner.schedule;

import java.util.ArrayList;
import java.util.Collections;

public class DeadlineJobScheduler extends JobScheduler {

	private DeadlineJobComparator comparator;
	private Job lastJob;

	public DeadlineJobScheduler(ArrayList<Job> jobs, DeadlineJobComparator comparator) {
		super(jobs, comparator);
	}

	@Override
	public void run() {
		while (jobs.size() != 0) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);
			totalTime += executeJob(job);
			if (job.isFinished()) {
				jobs.remove(0);
			}
			if (job != lastJob) {
				this.totalTime += OVERHEAD;
				lastJob = job;
			}

		}
	}
}
