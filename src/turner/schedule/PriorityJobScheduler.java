package turner.schedule;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityJobScheduler extends JobScheduler {

	public PriorityJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new PriorityJobComparator());
	}

	@Override
	public void run() {

		Job lastJob = null;
		
		while (!jobs.isEmpty()) {
			Collections.sort(jobs, new PriorityJobComparator());
			Job job = jobs.get(0);

			int actualTimeSlice = executeJob(job);

			totalTime += actualTimeSlice;
			
			if(job != lastJob){
				totalTime += OVERHEAD;
				lastJob = job;
			}
		}
	}
}
