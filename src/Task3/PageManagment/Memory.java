package PageManagment;

import java.util.ArrayList;

public class Memory {
	private Page[] pages;
	private Page[] frames;
	private ArrayList<Integer> references;
	private int pagesQuantity;
	private int framesQuantity;
	private int referenceQuantity;
	private int replaces = 0;
	private ArrayList<Page> lastReferences = new ArrayList<>();
	private ArrayList<Page> lastReferencesQueue = new ArrayList<>();


	public Memory(int pagesQuantity, int framesQuantity, int referenceQuantity) {
		this.pages = PageGenerator.generate(pagesQuantity);
		this.frames = new Page[framesQuantity];
		this.references = ReferenceGenerator.generate(pagesQuantity, referenceQuantity);
		this.pagesQuantity = pagesQuantity;
		this.framesQuantity = framesQuantity;
		this.referenceQuantity = referenceQuantity;
	}

	private Memory(Page[] pages, Page[] frames, ArrayList<Integer> references, int pagesQuantity, int framesQuantity, int referenceQuantity, ArrayList<Page> lastReferences, ArrayList<Page> lastReferencesQueue, int replaces) {
		this.pages = pages;
		this.frames = frames;
		this.references = references;
		this.pagesQuantity = pagesQuantity;
		this.framesQuantity = framesQuantity;
		this.referenceQuantity = referenceQuantity;
		this.lastReferences = lastReferences;
		this.lastReferencesQueue = lastReferencesQueue;
		this.replaces = replaces;
	}

	public Memory clone() {
		return new Memory(cloneTable(pages), cloneTable(frames), cloneArrayListInteger(references), pagesQuantity, framesQuantity, referenceQuantity, cloneArrayListPage(lastReferences), cloneArrayListPage(lastReferencesQueue), replaces);
	}

	private Page[] cloneTable(Page[] table) {
		Page[] result = new Page[table.length];
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) result[i] = table[i].clone();
		}
		return result;
	}

	private ArrayList<Page> cloneArrayListPage(ArrayList<Page> arrayList) {
		ArrayList<Page> result = new ArrayList<>();
		for (Page x : arrayList) {
			result.add(x.clone());
		}
		return result;
	}

	private ArrayList<Integer> cloneArrayListInteger(ArrayList<Integer> arrayList) {
		ArrayList<Integer> result = new ArrayList<>();
		for (Integer x : arrayList) {
			result.add(x);
		}
		return result;
	}

	public Page[] getPages() {
		return pages;
	}

	public void setPages(Page[] pages) {
		this.pages = pages;
	}

	public Page[] getFrames() {
		return frames;
	}

	public void setFrames(Page[] frames) {
		this.frames = frames;
	}

	public ArrayList<Integer> getReferences() {
		return references;
	}

	public void setReferences(ArrayList<Integer> references) {
		this.references = references;
	}

	public int getReplaces() {
		return replaces;
	}

	public void setReplaces(int replaces) {
		this.replaces = replaces;
	}

	public int getPagesQuantity() {
		return pagesQuantity;
	}

	public void setPagesQuantity(int pagesQuantity) {
		this.pagesQuantity = pagesQuantity;
	}

	public int getFramesQuantity() {
		return framesQuantity;
	}

	public void setFramesQuantity(int framesQuantity) {
		this.framesQuantity = framesQuantity;
	}

	public int getReferenceQuantity() {
		return referenceQuantity;
	}

	public void setReferenceQuantity(int referenceQuantity) {
		this.referenceQuantity = referenceQuantity;
	}

	public ArrayList<Page> getLastReferences() {
		return lastReferences;
	}

	public void setLastReferences(ArrayList<Page> lastReferences) {
		this.lastReferences = lastReferences;
	}

	public ArrayList<Page> getLastReferencesQueue() {
		return lastReferencesQueue;
	}

	public void setLastReferencesQueue(ArrayList<Page> lastReferencesQueue) {
		this.lastReferencesQueue = lastReferencesQueue;
	}

	public void doOperation() {
		Draw.draw(references.remove(0), frames);
	}

	public void draw(int x) {
		Draw.draw(x, frames);
	}

	public void fillEmpty() {
		for (int i = 0; i < frames.length; i++) {
			boolean test = true;
			for (int j = 0; j < i; j++) {
				if (!references.isEmpty() && references.get(0) == frames[j].getNumber()) {
					test = false;
					break;
				}
			}
			if (test == false) {
				i--;
				doOperation();
			} else if (!references.isEmpty()) {
				frames[i] = pages[references.get(0) - 1];
				addReference(pages[references.get(0) - 1]);
				replaces++;
				doOperation();
			}
		}
	}

	public void changePageFromTo(int from, int to) {
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].getNumber() == from) {
				frames[i] = pages[to - 1];
				draw(to);
				replaces++;
				addReference(pages[to - 1]);
				lastReferencesQueue.remove(pages[from - 1]);
				break;
			}
		}
	}

	public void changePageFromPositionXToValue(int position, int to) {
		frames[position] = pages[to - 1];
		replaces++;
		addReference(pages[to - 1]);
		lastReferencesQueue.remove(frames[position]);
	}

	public ArrayList<Integer> getCopyOfReferences() {
		ArrayList<Integer> result = new ArrayList<>();
		for (Integer x : references) {
			result.add(x);
		}
		return result;
	}

	public ArrayList<Page> getCopyOfFrames() {
		ArrayList<Page> result = new ArrayList<>();
		for (Page x : frames) {
			result.add(x);
		}
		return result;
	}

	public int getNextReference() {
		return references.remove(0);
	}

	public boolean isInLoadedPages(int page) {
		for (Page x : frames) {
			if (x.getNumber() == page) return true;
		}
		return false;
	}

	public void doAllDoableTasks() {
		while (!references.isEmpty() && isInLoadedPages(references.get(0))) {
			addReference(pages[references.get(0) - 1]);
			doOperation();
		}
	}

	private void addReference(Page x) {
		lastReferences.remove(x);
		lastReferences.add(x);
		if (lastReferences.size() > framesQuantity) lastReferences.remove(0);

		lastReferencesQueue.remove(x);
		x.setBitUsed(1);
		lastReferencesQueue.add(x);

	}

	public void replaceOldestReference() {
		changePageFromTo(lastReferences.remove(0).getNumber(), getNextReference());
	}

	public ArrayList<Page> getLastReferenceQueue() {
		return lastReferencesQueue;
	}

}
