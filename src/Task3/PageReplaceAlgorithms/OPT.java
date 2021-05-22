package PageReplaceAlgorithms;

import PageManagment.Memory;
import PageManagment.Page;

import java.util.ArrayList;

public class OPT {

	public static int OPT(Memory memory) {
		memory.fillEmpty();
		while (!memory.getReferences().isEmpty()) {
			memory.doAllDoableTasks();
			if (!memory.getReferences().isEmpty()) {
				ArrayList<Integer> referencesCopy = memory.getCopyOfReferences();
				ArrayList<Page> frameCopy = memory.getCopyOfFrames();
				Page[] pages = memory.getPages();

				for (int x : referencesCopy) {
					if (frameCopy.size() > 1) {
						frameCopy.remove(pages[x - 1]);
					} else {
						break;
					}
				}

				for (Page x : frameCopy) {
					if (memory.isInLoadedPages(x.getNumber())) {
						memory.changePageFromTo(x.getNumber(), memory.getNextReference());
						break;
					}
				}
			}
		}

		return memory.getReplaces();
	}

}
