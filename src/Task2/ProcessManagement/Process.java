package ProcessManagement;

public class Process {
	private int number;
	private int arrivalTime;
	private int cylinder;
	private int deadline;


	public Process(int number, int arrivalTime, int cylinder, int deadline) {
		this.number = number;
		this.arrivalTime = arrivalTime;
		this.cylinder = cylinder;
		this.deadline = deadline;
	}


	public Process(int number, int arrivalTime, int cylinder) {
		this.number = number;
		this.arrivalTime = arrivalTime;
		this.cylinder = cylinder;
		this.deadline = 0;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getCylinder() {
		return cylinder;
	}

	public void setCylinder(int cylinder) {
		this.cylinder = cylinder;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}


	public Process clone() {
		return new Process(number, arrivalTime, cylinder, deadline);
	}


	@Override
	public String toString() {
		return "Process{" +
				"number=" + number +
				", arrivalTime=" + arrivalTime +
				", cylinder=" + cylinder +
				", deadline=" + deadline +
				'}';
	}
}
