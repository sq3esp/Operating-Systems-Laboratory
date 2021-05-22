package Task1.ProcessManagement;

public class Process {
	private int number;
	private int burstTime;
	private int leftTime;
	private int arrivalTime;
	private int waitingCounter;

	public Process(int number, int burstTime, int arrivalTime) {
		this.number = number;
		this.burstTime = burstTime;
		this.leftTime = burstTime;
		this.arrivalTime = arrivalTime;
		this.waitingCounter = 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftDuration) {
		this.leftTime = leftDuration;
	}

	public void subtractLeftTime(int duration) {
		this.leftTime -= duration;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int checkInTime) {
		this.arrivalTime = checkInTime;
	}

	public int getWaitingCounter() {
		return waitingCounter;
	}

	public void setWaitingCounter(int waitingCounter) {
		this.waitingCounter = waitingCounter;
	}

	public Process clone(){
		return new Process(number, burstTime, arrivalTime);
	}

	@Override
	public String toString() {
		return "Process{" +
				"number=" + number +
				", duration=" + burstTime +
				", leftDuration=" + leftTime +
				", checkInTime=" + arrivalTime +
				", waitingCounter=" + waitingCounter +
				'}';
	}
}
