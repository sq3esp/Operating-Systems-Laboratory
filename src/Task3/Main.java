package Task3;

import PageManagment.PageManager;

public class Main {
	public static void main(String[] args) {
		PageManager manager = new PageManager(20, 19, 1000);
		manager.test(5);

		manager.changeParameters(20, 20, 1000).test(5);

		manager.changeParameters(20, 10, 1000).test(5);
	}
}
