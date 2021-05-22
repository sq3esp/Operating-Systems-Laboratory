package Task1;

import Task1.ProcessManagement.ProcessManager;

public class Main {
	public static void main(String[] args) {
		ProcessManager manager = new ProcessManager(10, 10, 10, 10, 100);
		/*Jako parametr funkcji test podaję jedną z trzech wartości:
		 * - 0 - dla testu losowego bez wyświetlania wszystkich procesów
		 * - 1 - dla testu losowego z wyświetlaniem wszystkich procesów
		 * - 2 - dla testu z pięcioma procesami z prezentacji
		* */
		manager.test(2);


	}
}
