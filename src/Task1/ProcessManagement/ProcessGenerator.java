package Task1.ProcessManagement;

import java.util.ArrayList;
import java.util.Random;

public class ProcessGenerator {
	private int processQuantity;
	private int maxDuration;
	private int maxCheckInTime;

	public ProcessGenerator(int processQuantity, int maxDuration, int maxCheckInTime) {
		this.processQuantity = processQuantity;
		this.maxDuration = maxDuration;
		this.maxCheckInTime = maxCheckInTime;
	}

	public int getProcessQuantity() {
		return processQuantity;
	}

	public void setProcessQuantity(int processQuantity) {
		this.processQuantity = processQuantity;
	}

	public int getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

	public int getMaxCheckInTime() {
		return maxCheckInTime;
	}

	public void setMaxCheckInTime(int maxCheckInTime) {
		this.maxCheckInTime = maxCheckInTime;
	}

	public ArrayList<Process> generate() {
		ArrayList<Process> processes = new ArrayList<>();
		for (int i = 0; i < processQuantity; i++) {
			Random r = new Random();
			processes.add(new Process(i, r.nextInt(maxDuration) + 1, r.nextInt(maxCheckInTime)));
		}
		return processes;
	}
}
