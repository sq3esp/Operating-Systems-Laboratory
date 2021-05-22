package PageReplaceAlgorithms;

import PageManagment.Memory;
import PageManagment.Page;

import java.util.ArrayList;

public class FIFO {

	public static int FIFO(Memory memory) {
		ArrayList<Integer> referencesCopy = memory.getCopyOfReferences();
		memory.fillEmpty();
		while (!memory.getReferences().isEmpty()) {
			memory.doAllDoableTasks();
			if (!memory.getReferences().isEmpty()) {
				while (!memory.isInLoadedPages(referencesCopy.get(0))) referencesCopy.remove(0);
				memory.changePageFromTo(referencesCopy.remove(0), memory.getNextReference());
			}
		}

		return memory.getReplaces();
	}

}
