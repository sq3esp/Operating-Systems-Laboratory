package Strategies;


import Tools.LRUAlgorithm;

public class ControllingFaultFrequency {
	private final LRUAlgorithm[] processes;
	private final int framesCount;
	private int pageFaultCounter;

	public ControllingFaultFrequency(int[][] processes, int framesCount) {
		this.framesCount = framesCount;
		this.processes = new LRUAlgorithm[processes.length];
		for (int i = 0; i < processes.length; i++) {
			this.processes[i] = new LRUAlgorithm(processes[i]);
		}
	}

	public void doAlgorithm() {
		boolean isAllDone = false;
		int counter = 0;
		int[] pageFaultsArray = new int[processes.length];
		int[] priorityArray = new int[processes.length];
		int availableCounter = 0;
		int framesPerProcess = framesCount / processes.length;

		for (LRUAlgorithm process : processes) {
			process.setFramesCount(framesPerProcess);
		}

		while (!isAllDone) {
			counter++;
			isAllDone = true;

			for (LRUAlgorithm process : processes) {
				if (!process.runOnce()) {
					isAllDone = false;
				}
			}

			if (counter >= framesPerProcess) {
				for (int i = 0; i < processes.length; i++) {
					priorityArray[i] = processes[i].getPageFaultCounter() - pageFaultsArray[i];
					pageFaultsArray[i] = processes[i].getPageFaultCounter();
					if (priorityArray[i] <= counter / 5 && processes[i].getFramesCount() > 1) {
						processes[i].removeFrame();
						availableCounter++;
					}
				}
				while (availableCounter > 0 && counter > 0) {
					for (int i = 0; i < processes.length && availableCounter > 0; i++) {
						if (priorityArray[i] >= counter) {
							processes[i].addFrame();
							availableCounter--;
						}
					}
					counter--;
				}
				counter = 0;
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
