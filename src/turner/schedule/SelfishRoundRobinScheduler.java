package turner.schedule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SelfishRoundRobinScheduler extends JobScheduler {

	private ArrayList<Job> active = new ArrayList<Job>();
	private ArrayList<Job> holding = new ArrayList<Job>();

	public SelfishRoundRobinScheduler(List<Job> jobs, Comparator<Job> comparator) {
		super(jobs, comparator);
		divideIntoQueues();
	}

	private void divideIntoQueues() {
		for (Job j : jobs) {
			if (j.getPriority() == Priority.High) {
				active.add(j);
			} else {
				holding.add(j);
			}
		}
	}

	@Override
	public void run() {
		while (active.size() != 0) {
			execute();
		}

		while (holding.size() != 0) {
			increasePriority();
			execute();
		}

	}

	private void execute() {
		Job job = active.remove(0);
		int actualTimeSlice = executeJob(job);
		totalTime += actualTimeSlice;
		if (!job.isFinished() && job.getPriority() == Priority.High) {
			active.add(job);
		}
	}

	private void increasePriority() {
		Iterator<Job> iter = holding.iterator();

		while (iter.hasNext()) {
			Job j = iter.next();
			int nextOrdinal = j.getPriority().ordinal() + 1;
			if (nextOrdinal < Priority.values().length) {
				j.setPriority(Priority.values()[nextOrdinal]);

			}
			if (j.getPriority() == Priority.High) {
				active.add(j);
				iter.remove();
			}

		}

	}
}