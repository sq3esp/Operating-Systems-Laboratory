package ProcessManagement;

import SchedulingAlgorithms.FCFS;
import SchedulingAlgorithms.SSTF;
import SchedulingAlgorithms.SCAN;
import SchedulingAlgorithms.CSCAN;
import SchedulingAlgorithms.LOOK;
import SchedulingAlgorithms.CLOOK;
import SchedulingAlgorithms.EDF;
import SchedulingAlgorithms.FDSCAN;

import java.util.ArrayList;

public class ProcessManager {
	private int cycles;
	private int quantum;
	private int processQuantity;
	private int maxCheckInTime;
	private int percentOfEdgeTasks;
	private int percentOfPriorityTasks;


	public ProcessManager(int cycles, int processQuantity, int maxCheckInTime, int percentOfEdgeTasks, int percentOfPriorityTasks) {
		this.cycles = cycles;
		this.processQuantity = processQuantity;
		this.maxCheckInTime = maxCheckInTime;
		this.percentOfEdgeTasks = percentOfEdgeTasks;
		this.percentOfPriorityTasks = percentOfPriorityTasks;
	}

	public int getCycles() {
		return cycles;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public int getProcessQuantity() {
		return processQuantity;
	}

	public void setProcessQuantity(int processQuantity) {
		this.processQuantity = processQuantity;
	}

	public int getMaxCheckInTime() {
		return maxCheckInTime;
	}

	public void setMaxCheckInTime(int maxCheckInTime) {
		this.maxCheckInTime = maxCheckInTime;
	}

	public void test() {
		double sumFCFS = 0;
		double sumSSTF = 0;
		double sumSCAN = 0;
		double sumCSCAN = 0;
		double sumLOOK = 0;
		double sumCLOOK = 0;
		double sumEDF = 0;
		double sumFDSCAN = 0;


		ProcessGenerator generator = new ProcessGenerator(processQuantity, maxCheckInTime, percentOfEdgeTasks, percentOfPriorityTasks);

		System.out.println("Poniższe dane są średnimi wartościami czasu oczekiwania");

		for (int i = 0; i < cycles; i++) {
			ArrayList<Process> processes = new ArrayList<>();
			double fcfs = 0;
			double sstf = 0;
			double scan = 0;
			double cscan = 0;
			double look = 0;
			double clook = 0;
			double edf = 0;
			double fdscan = 0;

			processes = generator.generate();

			for (Process x : processes) {
				System.out.println("\t" + x);
			}

			fcfs = FCFS.FCFS(processes);
			sstf = SSTF.SSTF(processes);
			scan = SCAN.SCAN(processes);
			cscan = CSCAN.CSCAN(processes);
			look = LOOK.LOOK(processes);
			clook = CLOOK.CLOOK(processes);
			edf = EDF.EDF(processes);
			fdscan = FDSCAN.FDSCAN(processes);


			System.out.println("\nPrzebieg " + (i + 1) + ":  FCFS: " + fcfs + "  SSTF:" + sstf + "  SCAN:" + scan + "  CSCAN:" + cscan + "  LOOK:" + look + "  CLOOK:" + clook);
			System.out.println("\t\t\tEDF: " + edf + "  FDSCAN: " + fdscan + "\n");


			sumFCFS += fcfs;
			sumSSTF += sstf;
			sumSCAN += scan;
			sumCSCAN += cscan;
			sumLOOK += look;
			sumCLOOK += clook;
			sumEDF += edf;
			sumFDSCAN += fdscan;
		}

		System.out.println("\n\n\n\nPodsumowanie wszystkich przebiegów:");
		System.out.println("FCFS: " + sumFCFS + "  SSTF:" + sumSSTF + "  SCAN:" + sumSCAN + "  CSCAN:" + sumCSCAN + "  LOOK:" + sumLOOK + "  CLOOK:" + sumCLOOK);
		System.out.println("EDF: " + sumEDF + "  FDSCAN: " + sumFDSCAN);
	}
}
