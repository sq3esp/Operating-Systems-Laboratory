package Task1.SchedulingAlgorithms;

import Task1.ProcessManagement.Process;
import java.util.*;

public class SJF {

	public static double SJF(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());
		processList.sort(Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getNumber));

		ArrayList<Process> queue = new ArrayList<>();

		double allWaitingTime = 0;
		int time = 0;

		while (!queue.isEmpty() || !processList.isEmpty()) {
			if (!processList.isEmpty()) {
				while (processList.get(0).getArrivalTime() <= time) {
					queue.add(processList.remove(0));
					if (processList.isEmpty()) break;
				}
			}
			if (!queue.isEmpty()) {
				queue.sort(Comparator.comparing(Process::getBurstTime).thenComparing(Process::getNumber));
			}


			if (queue.isEmpty()) {
				time++;
			} else {
				time += queue.get(0).getBurstTime();
				allWaitingTime += time - queue.get(0).getArrivalTime() - queue.remove(0).getBurstTime();
			}


		}
		return allWaitingTime / processes.size();
	}
}
