package ProcessManagement;

import java.util.ArrayList;
import java.util.Random;

public class ProcessGenerator {
	private int processQuantity;
	private int maxCheckInTime;
	private int percentOfEdgeTasks;
	private int percentOfPriorityTasks;

	public ProcessGenerator(int processQuantity, int maxCheckInTime, int percentOfEdgeTasks, int percentOfPriorityTasks) {
		this.processQuantity = processQuantity;
		this.maxCheckInTime = maxCheckInTime;
		this.percentOfEdgeTasks = percentOfEdgeTasks;
		this.percentOfPriorityTasks = percentOfPriorityTasks;
	}


	public int getProcessQuantity() {
		return processQuantity;
	}

	public void setProcessQuantity(int processQuantity) {
		this.processQuantity = processQuantity;
	}

	public int getMaxCheckInTime() {
		return maxCheckInTime;
	}

	public void setMaxCheckInTime(int maxCheckInTime) {
		this.maxCheckInTime = maxCheckInTime;
	}


	public ArrayList<Process> generate() {
		ArrayList<Process> processes = new ArrayList<>();
		int deadline;
		int cylinder;
		for (int i = 0; i < processQuantity; i++) {
			Random r = new Random();
			if (r.nextInt(100) > percentOfPriorityTasks) {
				deadline = r.nextInt(50) + 10;
			} else {
				deadline = 0;
			}

			if (r.nextInt(100) < percentOfEdgeTasks) {
				if (r.nextBoolean()) {
					cylinder = r.nextInt(25);
				} else {
					cylinder = 99 - r.nextInt(25);
				}
			} else {
				cylinder = r.nextInt(40) + 30;
			}
			processes.add(new Process(i, r.nextInt(maxCheckInTime), cylinder, deadline));
		}
		return processes;
	}
}
