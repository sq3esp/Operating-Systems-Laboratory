package SchedulingAlgorithms;

import ProcessManagement.Draw;
import ProcessManagement.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class FDSCAN {

	public static double FDSCAN(ArrayList<Process> processes) {
		ArrayList<Process> processList = new ArrayList<>();
		ArrayList<Process> priorityList = new ArrayList<>();
		ArrayList<Process> normalList = new ArrayList<>();
		for (Process x : processes) processList.add(x.clone());
		Process x;

		int allMoves = 0;
		int position = 0;
		int time = 0;
		int index = 0;

		System.out.println("\n\n\n######FDSCAN######");
		Draw.draw(0);

		updateList(processList, priorityList, time, true);
		updateList(processList, normalList, time, false);

		while (!processList.isEmpty() || !priorityList.isEmpty() || !normalList.isEmpty()) {
			if (!priorityList.isEmpty() || !normalList.isEmpty()) {
				if (!priorityList.isEmpty()) {
					x = searchPossible(priorityList, position, time);
					if (x == null) {
						x = priorityList.remove(0);
					} else {
						priorityList.remove(x);
					}
					deleteBetween(normalList, position, x.getCylinder());
				} else {
					x = normalList.remove(0);
					deleteBetween(normalList, position, x.getCylinder());
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
	}

	private static Process searchPossible(ArrayList<Process> priorityList, int head, int time) {
		int minimum = Integer.MAX_VALUE;
		Process result = null;
		for (int i = 0; i < priorityList.size(); i++) {
			if (priorityList.get(i).getDeadline() >= time + Math.abs(priorityList.get(i).getCylinder() - head) && Math.abs(priorityList.get(i).getCylinder() - head) < minimum) {
				minimum = priorityList.get(i).getCylinder() - head;
				result = priorityList.get(i);
			}
		}
		return result;
	}

	private static void deleteBetween(ArrayList<Process> normalList, int start, int stop) {
		int index = 0;
		if (start > stop) {
			int temp = stop;
			stop = start;
			start = temp;
		}
		while (index < normalList.size()) {
			if (start >= normalList.get(index).getCylinder() && stop <= normalList.get(index).getCylinder()) {
				normalList.remove(index);
			} else {
				index++;
			}
		}
	}
}
