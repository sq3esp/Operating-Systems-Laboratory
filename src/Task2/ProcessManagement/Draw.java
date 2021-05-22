package ProcessManagement;

public class Draw {
	public static void draw(int headPosition) {
		char b = 9607, c = 9601;
		String result = "";

		for (int i = 0; i < 100; i++) {
			if (i == headPosition) {
				result += b;
				continue;
			}
			result += c;
		}
		System.out.println(result);
	}
}
