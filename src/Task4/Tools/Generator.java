package Tools;

import java.util.Random;

public class Generator {
	private final int processesCount;
	private final int pagesCount;
	private final int minNumberOfReferences;
	private final int maxNumberOfReferences;
	private final int localityOfReferences;

	public Generator(int processesCount, int pagesCount, int minNumberOfReferences, int maxNumberOfReferences, int localityOfReferences) {
		this.processesCount = processesCount;
		this.pagesCount = pagesCount;
		this.minNumberOfReferences = minNumberOfReferences;
		this.maxNumberOfReferences = maxNumberOfReferences;
		this.localityOfReferences = localityOfReferences;
	}

	public int[][] generateProcesses() {
		int[][] processesArray = new int[processesCount][];
		for (int j = 0; j < processesArray.length; j++) {
			int referencesCount = myRandom(minNumberOfReferences, maxNumberOfReferences);
			int[] referencesArray = new int[referencesCount];
			int temp = pagesCount / processesCount;
			referencesArray[0] = myRandom(temp * j, temp * (j + 1));

			for (int i = 0; i < referencesCount - 1; i++) {
				int min = Math.max(0, referencesArray[i] - localityOfReferences);
				int max = Math.min(pagesCount, referencesArray[i] + localityOfReferences);
				referencesArray[i + 1] = myRandom(min, max);
			}
			processesArray[j] = referencesArray;
		}
		return processesArray;
	}

	public int myRandom(int min, int max) {
		Random random = new Random();
		if (max < min) {
			int temp = min;
			min = max;
			max = temp;
		}
		if (max <= 0) return 0;
		if (min == max) return max;
		return random.nextInt(max - min) + min;
	}
}
