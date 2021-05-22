package PageManagment;

import PageReplaceAlgorithms.*;

public class PageManager {
	private int pagesQuantity;
	private int frameQuantity;
	private int referenceQuantity;

	public PageManager(int pagesQuantity, int frameQuantity, int referenceQuantity) {
		this.pagesQuantity = pagesQuantity;
		this.frameQuantity = frameQuantity;
		this.referenceQuantity = referenceQuantity;
	}

	public PageManager changeParameters(int pagesQuantity, int frameQuantity, int referenceQuantity) {
		this.pagesQuantity = pagesQuantity;
		this.frameQuantity = frameQuantity;
		this.referenceQuantity = referenceQuantity;
		return this;
	}

	public int getPagesQuantity() {
		return pagesQuantity;
	}

	public void setPagesQuantity(int pagesQuantity) {
		this.pagesQuantity = pagesQuantity;
	}

	public int getFrameQuantity() {
		return frameQuantity;
	}

	public void setFrameQuantity(int frameQuantity) {
		this.frameQuantity = frameQuantity;
	}

	public int getReferenceQuantity() {
		return referenceQuantity;
	}

	public void setReferenceQuantity(int referenceQuantity) {
		this.referenceQuantity = referenceQuantity;
	}

	public void test() {

		Memory memory = new Memory(pagesQuantity, frameQuantity, referenceQuantity);
		int fifo = 0;
		int opt = 0;
		int lru = 0;
		int alru = 0;
		int rand = 0;


		fifo = FIFO.FIFO(memory.clone());
		opt = OPT.OPT(memory.clone());
		lru = LRU.LRU(memory.clone());
		alru = ALRU.ALRU(memory.clone());
		rand = RAND.RAND(memory.clone());


		System.out.println("Liczba braków stron: FIFO: " + fifo + "  OPT:" + opt + "  LRU:" + lru + "  ALRU:" + alru + "  RAND:" + rand);


	}

	public void test(int loops) {
		System.out.println();
		for(int i=0;i<loops;i++){
			test();
		}
	}
}
