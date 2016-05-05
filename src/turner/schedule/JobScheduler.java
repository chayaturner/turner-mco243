package turner.schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class JobScheduler implements Runnable {

	// Max amount of times each job runs for.
	private static final int TIME_SLICE = 10;

	// amount of time it takes to switch jobs.
	protected static final int OVERHEAD = 3;

	private static final Random RAND = new Random();

	protected List<Job> jobs;
	private Comparator<Job> comparator;
	protected int numJobsCompleted;
	protected int totalTime;

	public JobScheduler(List<Job> jobs2, Comparator<Job> comparator) {
		jobs = new ArrayList<Job>();
		this.comparator = comparator;
	}

	@Override
	public void run() {

		Job lastJob = null;

		while (!jobs.isEmpty()) {
			Collections.sort(jobs, comparator);

			Job job = jobs.get(0);

			int actualTimeSlice = executeJob(job);
			if (job != lastJob) {
				totalTime += OVERHEAD;
				lastJob = job;
			}
			totalTime += actualTimeSlice;
		}

	}

	/**
	 * @param job
	 * @return the amount of time used by the job
	 */
	protected int executeJob(Job job) {
		job.setState(JobState.Running);

		job.setLastRanAtTime(System.currentTimeMillis());

		int actualTimeSlice = computeActualSlice(job);

		job.decrementTimeLeftToRun(actualTimeSlice);

		if (job.isFinished()) {
			jobs.remove(0);
			numJobsCompleted++;
		} else {
			job.setState(JobState.Ready);
		}
		return actualTimeSlice;
	}

	private int computeActualSlice(Job job) {
		int timeLeftToRun = job.getTimeLeftToRun();
		int actualTimeSlice;
		if (job.getType() == JobType.IO) {
			// rand num btwn 0 and timeSlice
			actualTimeSlice = Math.min(timeLeftToRun, RAND.nextInt(TIME_SLICE));

		} else {
			actualTimeSlice = Math.min(timeLeftToRun, TIME_SLICE);
		}
		return actualTimeSlice;
	}

	private int getTotalTime() {
		return totalTime;
	}

	private int getNumJobsCompleted() {
		return numJobsCompleted;
	}

	public static void main(String[] args) {

		List<Job> jobs = Arrays.asList(new Job("1", Priority.High, JobType.Computation, 100, 1L), new Job("2",
				Priority.Low, JobType.IO, 200, 2L), new Job("3", Priority.Medium, JobType.Computation, 100, 3L),
				new Job("4", Priority.High, JobType.Computation, 1000, 2L), new Job("5", Priority.Medium,
						JobType.Computation, 350, 1L), new Job("6", Priority.Medium, JobType.IO, 100, 3L), new Job("7",
						Priority.Low, JobType.IO, 500, 2L), new Job("8", Priority.High, JobType.Computation, 20, 3L),
				new Job("9", Priority.Medium, JobType.IO, 30, 1L), new Job("10", Priority.Medium, JobType.Computation,
						600, 2L), new Job("11", Priority.Low, JobType.IO, 100, 3L), new Job("12", Priority.Low,
						JobType.Computation, 700, 3L)

		);

		JobScheduler scheduler = new JobScheduler(new ArrayList<Job>(jobs), new PriorityJobComparator());
		scheduler.run();
		System.out.println(String.format("numJobsCompleted = %d totalTime = %d", scheduler.getNumJobsCompleted(),
				scheduler.getTotalTime()));
	}

}
