package SchedulingAlgorithms;

import ProcessManagement.Draw;
import ProcessManagement.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class CLOOK {

	public static double CLOOK(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());

		processList.sort(Comparator.comparing(Process::getCylinder));


		int allMoves = 0;
		int position = 0;
		int time = 0;
		int index = 0;

		System.out.println("\n\n\n######CLOOK######");
		Draw.draw(0);
		while (!processList.isEmpty()) {
			time = scrollTime(processList, time);
			index = 0;

			for (Process x : processList) {
				if (x.getArrivalTime() <= time) {
					time += Math.abs(x.getCylinder() - position);
					allMoves += Math.abs(x.getCylinder() - position);
					position = x.getCylinder();
					break;
				}
			}
			while (index < processList.size()) {
				Process x = processList.get(index);
				if (x.getArrivalTime() <= time && x.getCylinder() >= position) {
					allMoves += Math.abs(x.getCylinder() - position);
					time += Math.abs(x.getCylinder() - position);
					position = x.getCylinder();
					processList.remove(x);
					Draw.draw(position);
				} else {
					index++;
				}
			}

		}
		return allMoves;
	}

	private static int scrollTime(ArrayList<Process> processes, int time) {
		int minimal = Integer.MAX_VALUE;
		for (Process x : processes) {
			if (x.getArrivalTime() <= time) return time;
			if (x.getArrivalTime() < minimal) minimal = x.getArrivalTime();
		}
		return minimal;
	}
}
