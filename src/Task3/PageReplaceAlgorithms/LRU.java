package PageReplaceAlgorithms;

import PageManagment.Memory;


public class LRU {

	public static int LRU(Memory memory) {
		memory.fillEmpty();
		while (!memory.getReferences().isEmpty()) {
			memory.doAllDoableTasks();
			if (!memory.getReferences().isEmpty()) {
				memory.replaceOldestReference();
			}
		}

		return memory.getReplaces();
	}

}
