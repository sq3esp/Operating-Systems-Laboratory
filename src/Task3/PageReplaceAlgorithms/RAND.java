package PageReplaceAlgorithms;

import PageManagment.Memory;
import PageManagment.Page;

import java.util.ArrayList;
import java.util.Random;

public class RAND {

	public static int RAND(Memory memory) {
		Random random = new Random();
		int rand;
		memory.fillEmpty();
		while (!memory.getReferences().isEmpty()) {
			memory.doAllDoableTasks();
			if (!memory.getReferences().isEmpty()) {
				do {
					rand = random.nextInt(memory.getPagesQuantity()) + 1;
				} while (!memory.isInLoadedPages(rand));
				memory.changePageFromTo(rand, memory.getNextReference());
			}
		}

		return memory.getReplaces();

	}

}
