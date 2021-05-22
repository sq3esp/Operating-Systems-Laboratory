package PageManagment;

import java.util.ArrayList;
import java.util.Random;

public class ReferenceGenerator {
	public static ArrayList<Integer> generate(int pagesQuantity, int referenceQuantity) {
		Random random = new Random();
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < referenceQuantity; i++) {
			result.add(random.nextInt(pagesQuantity) + 1);
		}
		return result;
	}
}
