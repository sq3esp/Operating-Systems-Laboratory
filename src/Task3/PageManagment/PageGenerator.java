package PageManagment;

import java.util.ArrayList;

public class PageGenerator {

	public static Page[] generate(int pagesQuantity) {
		Page[] pages = new Page[pagesQuantity];

		for (int i = 0; i < pagesQuantity; i++) {
			pages[i] = new Page(i + 1);
		}
		return pages;
	}
}
