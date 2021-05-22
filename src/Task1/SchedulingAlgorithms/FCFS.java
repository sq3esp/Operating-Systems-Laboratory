package Task1.SchedulingAlgorithms;

import Task1.ProcessManagement.Process;

import java.util.*;

public class FCFS {

	public static double FCFS(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());

		processList.sort(Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getNumber));


		double allWaitingTime = 0;
		int time = 0;

		for (Process x : processList) {
			if (x.getArrivalTime() > time) time = x.getArrivalTime();
			time += x.getBurstTime();
			allWaitingTime += time - x.getBurstTime() - x.getArrivalTime();
		}
		return allWaitingTime / processList.size();
	}
}
