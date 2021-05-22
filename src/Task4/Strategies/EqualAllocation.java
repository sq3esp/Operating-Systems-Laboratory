package Strategies;

import Tools.LRUAlgorithm;

public class EqualAllocation {
	private final LRUAlgorithm[] processes;
	private final int framesCount;
	private int pageFaultCounter;

	public EqualAllocation(int[][] processes, int framesCount) {
		this.framesCount = framesCount;
		this.processes = new LRUAlgorithm[processes.length];
		for (int i = 0; i < processes.length; i++) {
			this.processes[i] = new LRUAlgorithm(processes[i]);
		}
	}

	public void doAlgorithm() {
		int framesPerProcess = framesCount / processes.length;

		for (LRUAlgorithm process : processes) {
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
