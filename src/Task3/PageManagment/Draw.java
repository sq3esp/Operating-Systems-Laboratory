package PageManagment;

public class Draw {
	public static void draw(int pageNumber, Page[] frames) {

		for (Page x : frames) {
			if (x == null) {
				System.out.print("[   ]");
			} else if (x.getNumber() != pageNumber) {
				System.out.print("[ " + x.getNumber() + " ]");
			} else {
				System.out.print("\033[1m[-" + x.getNumber() + "-]\033[0m");
			}
		}
		System.out.println();

	}
}
