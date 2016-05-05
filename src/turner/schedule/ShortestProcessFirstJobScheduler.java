package turner.schedule;

import java.util.ArrayList;

public class ShortestProcessFirstJobScheduler extends JobScheduler {


	public ShortestProcessFirstJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new ShortestProcessFirstJobComparator());
	}

	@Override
	public void run() {

		while (jobs.size() != 0) {
			Job job = jobs.remove(0);
			while (job.getTimeLeftToRun() > 0) {
				totalTime += executeJob(job);
			}
		}
	}
}

