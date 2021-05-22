package SchedulingAlgorithms;

import ProcessManagement.Draw;
import ProcessManagement.Process;

import java.util.*;

public class FCFS {

	public static double FCFS(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());

		processList.sort(Comparator.comparing(Process::getArrivalTime));

		int allMoves = 0;
		int position = 0;

		System.out.println("\n\n\n######FCFS######");
		Draw.draw(0);
		while (!processList.isEmpty()) {
			Process x = processList.get(0);
			allMoves += Math.abs(x.getCylinder() - position);
			position = x.getCylinder();
			processList.remove(0);
			Draw.draw(position);
		}
		return allMoves;
	}
}
