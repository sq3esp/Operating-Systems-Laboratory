package Task1.ProcessManagement;

import Task1.SchedulingAlgorithms.FCFS;
import Task1.SchedulingAlgorithms.RR;
import Task1.SchedulingAlgorithms.SJF;
import Task1.SchedulingAlgorithms.SRTF;
import Task1.ProcessManagement.Process;

import java.util.ArrayList;

public class ProcessManager {
	private int cycles;
	private int quantum;
	private int processQuantity;
	private int maxDuration;
	private int maxCheckInTime;


	public ProcessManager(int cycles, int quantum, int processQuantity, int maxDuration, int maxCheckInTime) {
		this.cycles = cycles;
		this.quantum = quantum;
		this.processQuantity = processQuantity;
		this.maxDuration = maxDuration;
		this.maxCheckInTime = maxCheckInTime;
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

	public int getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

	public int getMaxCheckInTime() {
		return maxCheckInTime;
	}

	public void setMaxCheckInTime(int maxCheckInTime) {
		this.maxCheckInTime = maxCheckInTime;
	}

	public void test(int debug) {
		double sumFCFS = 0;
		double sumSJF = 0;
		double sumSRTF = 0;
		double sumRR = 0;

		ProcessGenerator generator = new ProcessGenerator(processQuantity, maxDuration, maxCheckInTime);

		System.out.println("Poniższe dane są średnimi wartościami czasu oczekiwania");

		for (int i = 0; i < cycles; i++) {
			ArrayList<Process> processes = new ArrayList<>();
			double fcfs=0;
			double sjf=0;
			double srtf=0;
			double rr=0;

			if (debug == 0) {
				processes = generator.generate();
				fcfs = FCFS.FCFS(processes);
				sjf = SJF.SJF(processes);
				srtf = SRTF.SRTF(processes, quantum);
				rr = RR.RR(processes, quantum);
			}
			else if (debug == 1) {
				processes = generator.generate();

				for (Process x : processes) {
					System.out.println("\t"+x);
				}

				fcfs = FCFS.FCFS(processes);
				sjf = SJF.SJF(processes);
				srtf = SRTF.SRTF(processes, quantum);
				rr = RR.RR(processes, quantum);
			}
			else if (debug == 2) {
				processes = new ArrayList<>();

				processes.add(new Process(1, 1, 4));
				processes.add(new Process(2, 5, 2));
				processes.add(new Process(3, 8, 0));
				processes.add(new Process(4, 2, 5));
				processes.add(new Process(5, 4, 7));
				System.out.println("Wartości procesów dla przebiegu "+(i+1));
				for (Process x : processes) {
					System.out.println("\t"+x);
				}


				fcfs = FCFS.FCFS(processes);
				sjf = SJF.SJF(processes);
				srtf = SRTF.SRTF(processes, 2);
				rr = RR.RR(processes, 5);
			}
			else{
				System.out.println("NIEPOPRAWNY PARAMETR FUNKCJI");
				return;
			}



			System.out.println("\nPrzebieg "+(i+1)+":  FCFS: " + fcfs + "  SJF:" + sjf + "  SRTF:" + srtf + "  RR:" + rr + "\n");

			sumFCFS += fcfs;
			sumSJF += sjf;
			sumSRTF += srtf;
			sumRR += rr;
		}

		System.out.println("\n\n\n\nPodsumowanie średnie wszystkich przebiegów:");
		System.out.println("FCFS: " + sumFCFS / cycles + "  SJF:" + sumSJF / cycles + "  SRTF:" + sumSRTF / cycles + "  RR:" + sumRR / cycles);


	}
}
