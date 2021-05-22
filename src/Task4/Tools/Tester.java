package Tools;

import Strategies.*;

public class Tester {
	private int processesCount;
	private int pagesCount;
	private int minimumNumberOfReferences;
	private int maximumNumberOfReferences;
	private int localityOfReferences;
	private int framesCount;

	public Tester() {
		processesCount = 20;
		pagesCount = 1000;
		minimumNumberOfReferences = 100;
		maximumNumberOfReferences = 500;
		localityOfReferences = 10;
		framesCount = 200;
	}

	public Tester(int processesCount, int pagesCount, int minimumNumberOfReferences, int maximumNumberOfReferences, int localityOfReferences, int framesCount) {
		this.processesCount = processesCount;
		this.pagesCount = pagesCount;
		this.minimumNumberOfReferences = minimumNumberOfReferences;
		this.maximumNumberOfReferences = maximumNumberOfReferences;
		this.localityOfReferences = localityOfReferences;
		this.framesCount = framesCount;
	}


	public void DoTest() {
		Generator generator = new Generator(processesCount, pagesCount, minimumNumberOfReferences, maximumNumberOfReferences, localityOfReferences);
		int[][] processes = generator.generateProcesses();


		ProportionalAllocation proportionalAllocation = new ProportionalAllocation(copyArray(processes), framesCount);
		EqualAllocation equalAllocation = new EqualAllocation(copyArray(processes), framesCount);
		ControllingFaultFrequency controllingFaultFrequency = new ControllingFaultFrequency(copyArray(processes), framesCount);
		ZoneModel zoneModel = new ZoneModel(copyArray(processes), framesCount);

		proportionalAllocation.doAlgorithm();
		equalAllocation.doAlgorithm();
		controllingFaultFrequency.doAlgorithm();
		zoneModel.doAlgorithm();

		for (int i = 0; i < processesCount; i++) {
			System.out.println("\nWyniki dla procesu " + (i + 1) + ": ");
			System.out.println("Przydział proporcjonalny:               " + proportionalAllocation.getResultForProcess(i));
			System.out.println("Przydział równy:                        " + equalAllocation.getResultForProcess(i));
			System.out.println("Sterowanie częstością błędów strony:    " + controllingFaultFrequency.getResultForProcess(i));
			System.out.println("Model strefowy:                         " + zoneModel.getResultForProcess(i));
		}


		System.out.println("\nWyniki sumaryczny dla wszystkich procesów: ");
		System.out.println("Przydział proporcjonalny:               " + proportionalAllocation.getResult());
		System.out.println("Przydział równy:                        " + equalAllocation.getResult());
		System.out.println("Sterowanie częstością błędów strony:    " + controllingFaultFrequency.getResult());
		System.out.println("Model strefowy:                         " + zoneModel.getResult());


	}

	public int[][] copyArray(int[][] array) {
		int[][] newArray = new int[array.length][];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = new int[array[i].length];
			System.arraycopy(array[i], 0, newArray[i], 0, array[i].length);
		}
		return newArray;
	}

	public int getProcessesCount() {
		return processesCount;
	}

	public void setProcessesCount(int processesCount) {
		this.processesCount = processesCount;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public int getMinimumNumberOfReferences() {
		return minimumNumberOfReferences;
	}

	public void setMinimumNumberOfReferences(int minimumNumberOfReferences) {
		this.minimumNumberOfReferences = minimumNumberOfReferences;
	}

	public int getMaximumNumberOfReferences() {
		return maximumNumberOfReferences;
	}

	public void setMaximumNumberOfReferences(int maximumNumberOfReferences) {
		this.maximumNumberOfReferences = maximumNumberOfReferences;
	}

	public int getLocalityOfReferences() {
		return localityOfReferences;
	}

	public void setLocalityOfReferences(int localityOfReferences) {
		this.localityOfReferences = localityOfReferences;
	}

	public int getFramesCount() {
		return framesCount;
	}

	public void setFramesCount(int framesCount) {
		this.framesCount = framesCount;
	}

	public void changeParameters(int processesCount, int pagesCount, int minimumNumberOfReferences, int maximumNumberOfReferences, int localityOfReferences, int framesCount) {
		this.processesCount = processesCount;
		this.pagesCount = pagesCount;
		this.minimumNumberOfReferences = minimumNumberOfReferences;
		this.maximumNumberOfReferences = maximumNumberOfReferences;
		this.localityOfReferences = localityOfReferences;
		this.framesCount = framesCount;
	}
}
