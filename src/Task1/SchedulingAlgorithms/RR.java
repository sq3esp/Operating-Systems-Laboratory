package Task1.SchedulingAlgorithms;

import Task1.ProcessManagement.Process;
import java.util.ArrayList;
import java.util.Comparator;

public class RR {

	public static double RR(ArrayList<Process> processes, int quantum) {
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

			if (queue.isEmpty()) {
				time++;
			}
			else {
				if (queue.get(0).getLeftTime() > quantum) {
					time += quantum;

					if (!processList.isEmpty()) {
						while (processList.get(0).getArrivalTime() <= time) {
							queue.add(processList.remove(0));
							if (processList.isEmpty()) break;
						}
					}

					queue.get(0).subtractLeftTime(quantum);

					Process temp = queue.remove(0);
					queue.add(temp);

				}
				else {
					time += queue.get(0).getLeftTime();
					allWaitingTime += time - queue.get(0).getArrivalTime() - queue.remove(0).getBurstTime();
				}

			}


		}
		return allWaitingTime / processes.size();
	}
}
