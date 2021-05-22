package Strategies;

import Tools.LRUAlgorithm;

import java.util.HashSet;

public class ZoneModel {
	private final LRUAlgorithm[] processes;
	private final int framesCount;
	private int pageFaultCounter;

	public ZoneModel(int[][] processes, int framesCount) {
		this.framesCount = framesCount;
		this.processes = new LRUAlgorithm[processes.length];
		for (int i = 0; i < processes.length; i++) {
			this.processes[i] = new LRUAlgorithm(processes[i]);
		}
	}

	public void doAlgorithm() {
		int framesPerProcess = framesCount / processes.length;

		HashSet<Integer>[] actualSet = new HashSet[processes.length];
		for (int i = 0; i < processes.length; i++) {
			processes[i].setFramesCount(framesPerProcess);
			actualSet[i] = new HashSet<>();
		}
		boolean allDone = false;
		int counter = 0;
		int avalivableCounter = 0;
		while (!allDone) {
			counter++;
			allDone = true;
			for (int i = 0; i < processes.length; i++) {
				if (!processes[i].runOnce()) {
					actualSet[i].add(processes[i].getLastUsed());
					allDone = false;
				}
			}
			if (counter >= 2 * framesPerProcess) {
				for (int i = 0; i < processes.length; i++) {
					if (processes[i].isDone()) {
						avalivableCounter += processes[i].getFramesCount();
						processes[i].setFramesCount(0);
					}
					for (int j = actualSet[i].size() - processes[i].getFramesCount(); j > 0 && processes[i].getFramesCount() > 1; j--) {
						avalivableCounter++;
						processes[i].removeFrame();
					}
				}
				while (avalivableCounter > 0 && counter > 0) {
					for (int i = 0; i < processes.length && avalivableCounter > 0; i++) {
						int neddedCounter = actualSet[i].size() - processes[i].getFramesCount();
						if (neddedCounter > 0) {
							if (avalivableCounter >= neddedCounter) {
								processes[i].addFrame();
								avalivableCounter--;
							} else {
								avalivableCounter += processes[i].getFramesCount();
								processes[i].setFramesCount(0);
							}
						}
					}
					counter--;
				}
				counter = 0;
				for (int i = 0; i < actualSet.length; i++)
					if (processes[i].getFramesCount() > 0 || processes[i].isDone())
						actualSet[i].clear();
			}

		}
		for (LRUAlgorithm process : processes) {
			pageFaultCounter += process.getPageFaultCounter();
		}
	}

	public int getResult() {
		return pageFaultCounter;
	}

	public int getResultForProcess(int processNumber) {
		return processes[processNumber].getPageFaultCounter();
	}
}
