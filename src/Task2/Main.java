package Task2;

import ProcessManagement.ProcessManager;

public class Main {
	public static void main(String[] args) {
		ProcessManager manager = new ProcessManager(10, 10, 100, 60, 30);

		manager.test();
	}
}
