package Strategies;

import Tools.LRUAlgorithm;

public class ProportionalAllocation {
	private final LRUAlgorithm[] processes;
	private final int framesCount;
	private int pageFaultCounter;

	public ProportionalAllocation(int[][] processes, int framesCount) {
		this.framesCount = framesCount;
		this.processes = new LRUAlgorithm[processes.length];
		for (int i = 0; i < processes.length; i++) {
			this.processes[i] = new LRUAlgorithm(processes[i]);
		}
	}

	public void doAlgorithm() {
		int processesLength = 0;

		for (LRUAlgorithm process : processes) {
			processesLength += process.getPageReferenceArray().length;
		}

		for (LRUAlgorithm process : processes) {
			int framesPerProcess = framesCount * process.getPageReferenceArray().length / processesLength;
			if (framesPerProcess == 0) framesPerProcess = 1;
			process.setFramesCount(framesPerProcess);
		}

		for (LRUAlgorithm process : processes) {
			process.doAll();
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
