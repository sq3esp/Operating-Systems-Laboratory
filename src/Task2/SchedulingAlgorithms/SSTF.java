package SchedulingAlgorithms;

import ProcessManagement.Draw;
import ProcessManagement.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class SSTF {

	public static double SSTF(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());

		processList.sort(Comparator.comparing(Process::getArrivalTime));

		int allMoves = 0;
		int position = 0;
		int time = 0;

		System.out.println("\n\n\n######SSTF######");
		Draw.draw(0);
		while (!processList.isEmpty()) {
			Process x = nearest(processList, position, time);
			if (x != null) {
				allMoves += Math.abs(x.getCylinder() - position);
				time += Math.abs(x.getCylinder() - position);
				position = x.getCylinder();
				Draw.draw(position);
			}
			else {
				time++;
			}
		}
		return allMoves;
	}

	private static Process nearest(ArrayList<Process> processes, int position, int time) {
		int index = -1;
		int distance = Integer.MAX_VALUE;

		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).getArrivalTime() <= time) {
				if (Math.abs(processes.get(i).getCylinder() - position) < distance) {
					distance = Math.abs(processes.get(i).getCylinder() - position);
					index = i;
				}
			}
		}
		if (index >= 0) return processes.remove(index);
		return null;
	}
}
