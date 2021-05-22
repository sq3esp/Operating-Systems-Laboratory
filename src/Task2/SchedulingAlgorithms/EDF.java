package SchedulingAlgorithms;

import ProcessManagement.Draw;
import ProcessManagement.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class EDF {

	public static double EDF(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		ArrayList<Process> priorityList = new ArrayList<>();
		ArrayList<Process> normalList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());
		Process x;

		int allMoves = 0;
		int position = 0;
		int time = 0;
		int index = 0;

		System.out.println("\n\n\n######EDF######");
		Draw.draw(0);

		updateList(processList, priorityList, time, true);
		updateList(processList, normalList, time, false);

		while (!processList.isEmpty() || !priorityList.isEmpty() || !normalList.isEmpty()) {
			if (!priorityList.isEmpty() || !normalList.isEmpty()) {
				if (!priorityList.isEmpty()) {
					x = priorityList.remove(0);
				} else {
					x = normalList.remove(0);
				}
				allMoves += Math.abs(x.getCylinder() - position);
				time += Math.abs(x.getCylinder() - position);
				position = x.getCylinder();
				Draw.draw(position);
				updateList(processList, priorityList, time, true);
				updateList(processList, normalList, time, false);
			} else {
				time++;
				updateList(processList, priorityList, time, true);
				updateList(processList, normalList, time, false);
			}
		}
		return allMoves;
	}

	private static void updateList(ArrayList<Process> in, ArrayList<Process> out, int time, boolean priority) {
		int index = 0;
		while (index < in.size()) {
			if (in.get(index).getArrivalTime() <= time && (in.get(index).getDeadline() > 0) == priority) {
				out.add(in.remove(index));
			} else {
				index++;
			}
		}
		if (priority) {
			out.sort(Comparator.comparing(Process::getDeadline));
		} else {
			out.sort(Comparator.comparing(Process::getArrivalTime));
		}
	}
}
