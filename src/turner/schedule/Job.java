package turner.schedule;

public class Job {

	private String name;

	private Priority priority;
	private Priority dynamicPriority;

	private JobState state;
	private JobType type;
	private Integer timeLeftToRun;
	private long lastRanAtTime;
	private Long deadline;

	public Job(String name, Priority priority, JobType type, int timeLeftToRun, Long deadline) {
		this.name = name;
		this.priority = priority;
		this.type = type;
		this.timeLeftToRun = timeLeftToRun;
		this.deadline = deadline;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setDynamicPriority(Priority dynamicPriority) {
		this.dynamicPriority = dynamicPriority;
	}

	public void setState(JobState state) {
		this.state = state;
	}

	public void setTimeLeftToRun(Integer timeLeftToRun) {
		this.timeLeftToRun = timeLeftToRun;
	}

	public void setLastRanAtTime(long lastRanAtTime) {
		this.lastRanAtTime = lastRanAtTime;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}

	public String getName() {
		return name;
	}

	public Priority getPriority() {
		return priority;
	}

	public Priority getDynamicPriority() {
		return dynamicPriority;
	}

	public JobState getState() {
		return state;
	}

	public Integer getTimeLeftToRun() {
		return timeLeftToRun;
	}

	public long getLastRanAtTime() {
		return lastRanAtTime;
	}

	public Long getDeadline() {
		return deadline;
	}

	public void decrementTimeLeftToRun(int time) {
		timeLeftToRun -= time;
	}

	public boolean isFinished() {
		return timeLeftToRun <= 0;
	}

}
