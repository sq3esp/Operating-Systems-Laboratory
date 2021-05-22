package PageReplaceAlgorithms;

import PageManagment.Memory;
import PageManagment.Page;

import java.util.ArrayList;

public class ALRU {

	public static int ALRU(Memory memory) {
		ArrayList<Page> queue;
		memory.fillEmpty();
		while (!memory.getReferences().isEmpty()) {
			memory.doAllDoableTasks();
			if (!memory.getReferences().isEmpty()) {
				queue = memory.getLastReferenceQueue();
				boolean done = false;
				while (!done) {
					for (Page x : queue) {
						if (x.getBitUsed() == 0) {
							memory.changePageFromTo(x.getNumber(), memory.getNextReference());
							done = true;
							break;
						} else {
							x.setBitUsed(0);
						}
					}
				}
			}
		}
		return memory.getReplaces();
	}

}
