package Tools;

import java.util.ArrayList;

public class LRUAlgorithm {
	private int pageFaultCounter;
	private final int[] pageReferenceArray;
	private int framesCount;
	private final ArrayList<Integer> frameList;
	private int finishedCounter = 0;

	public LRUAlgorithm(int[] pageReferencesArray) {
		this.pageReferenceArray = pageReferencesArray;
		this.pageFaultCounter = 0;
		this.framesCount = 0;
		frameList = new ArrayList<>();
	}

	public int getLastUsed() {
		return pageReferenceArray[finishedCounter - 1];
	}

	public int getFramesCount() {
		return framesCount;
	}

	public void setFramesCount(int framesCount) {
		this.framesCount = framesCount;
	}

	public int getPageFaultCounter() {
		return pageFaultCounter;
	}

	public int[] getPageReferenceArray() {
		return pageReferenceArray;
	}

	public boolean isDone() {
		return finishedCounter >= pageReferenceArray.length;
	}

	public void addFrame() {
		framesCount++;
	}

	public void removeFrame() {
		framesCount--;
	}

	public void doAll() {
		while (finishedCounter < pageReferenceArray.length) {
			runOnce();
		}
	}

	public boolean runOnce() {
		while (frameList.size() > framesCount) frameList.remove(0);
		if (finishedCounter < pageReferenceArray.length) {
			if (framesCount != 0) {
				if (frameList.contains(pageReferenceArray[finishedCounter])) {
					int x = frameList.remove(frameList.indexOf(pageReferenceArray[finishedCounter]));
					frameList.add(x);
				} else {
					pageFaultCounter++;
					if (frameList.size() >= framesCount) {
						frameList.remove(0);
					}
					frameList.add(pageReferenceArray[finishedCounter]);
				}
				finishedCounter++;
			}
			return false;
		}
		return true;
	}
}
