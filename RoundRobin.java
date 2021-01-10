import java.util.*;

public class RoundRobin {
  int ContextSwitching;
  int Quantum;
  ArrayList<Process> processes;

  RoundRobin(ArrayList<Process> p, int c, int Q) {
    processes = new ArrayList<Process>(p);
    ContextSwitching = c;
    Quantum = Q;
  }

  public void Schedule() {
    /*
     * 1. we work on First index of ArrayList 2. if ArrayList.isEmpty() ===>work is
     * done 3. else process.get(0).BurstTime-=Quantum;
     * Sysout(process.get(0)->Name+'|') if(process.get(0)->BurstTime > 0) 1.remove
     * 2.add else{ 1.remove only }
     * 
     */
    ArrayList<Process> Result = new ArrayList<Process>();
    ArrayList<Integer> BurstTimes = new ArrayList<Integer>();
    for (int i = 0; i < processes.size(); i++)
      BurstTimes.add(processes.get(i).BurstTime); // adding bursttimes of each processes to BurstTimes

    int Counter = 0; // counting time for the system
    int FinishedProcesses = 0;
    int index = -1; // index of processes
    int Size;
    while (FinishedProcesses < processes.size()) {
      Size = processes.size();
      index = (index + 1) % Size; // circular
      if (processes.get(index).ArrivalTime <= Counter && BurstTimes.get(index) > 0) { // arrived process and has some
                                                                                      // work to do
        BurstTimes.set(index, BurstTimes.get(index) - Quantum);
      } else {
        Counter++;
        continue;
      }
      System.out.print(processes.get(index).Name + '|');// Print order of execution
      Counter += Quantum + ContextSwitching;
      if (BurstTimes.get(index) <= 0) {
        // Process finished work
        FinishedProcesses++;
        processes.get(index).Turnaround = Counter - processes.get(index).ArrivalTime;
        processes.get(index).Waiting = processes.get(index).Turnaround - processes.get(index).BurstTime;
      }
    }
    System.out.println("\n");
    System.out.println("Process Name" + "     " + "ArrivalTime" + "     " + "BurstTime" + "     " + "Priority"
        + "      " + "Queue_Number" + "      " + "TurnAround Time" + "      " + "Waiting Time");
    for (int i = 0; i < processes.size(); i++) {
      System.out.println(processes.get(i).Name + "                    " + processes.get(i).ArrivalTime
          + "              " + processes.get(i).BurstTime + "           " + processes.get(i).Priority
          + "               " + processes.get(i).QueueNumber + "                " + processes.get(i).Turnaround
          + "                       " + processes.get(i).Waiting);
    }

    GetAvrg(processes);
  }

  public void GetAvrg(ArrayList<Process> R) {
    float totalTurnarround = 0.0f;
    float totalWaiting = 0.0f;
    for (int i = 0; i < R.size(); i++) {
      totalTurnarround += R.get(i).Turnaround;
      totalWaiting += R.get(i).Waiting;
    }

    float TurnaroundAvg = totalTurnarround / (float) R.size();
    float WaitingAvg = totalWaiting / (float) R.size();

    System.out.println(
        "in RoundRobin, Average Turn Arround Time: " + TurnaroundAvg + " " + "Average Waiting Time: " + WaitingAvg);
  }
}