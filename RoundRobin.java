import java.util.*;

public class RoundRobin {
  int ContextSwitching;
  int Quantum;
  ArrayList<Process> processes;
  ArrayList<Integer> BurstTimes = new ArrayList<Integer>();
  float TurnaroundAvg ;
  float WaitingAvg; 

  RoundRobin(ArrayList<Process> p, int c, int Q) {
    processes = new ArrayList<Process>(p);
    ContextSwitching = c;
    Quantum = Q;
    for (int i = 0; i < processes.size(); i++)
      BurstTimes.add(processes.get(i).BurstTime); // adding bursttimes of each processes to BurstTimes
    TurnaroundAvg = 0.0f;
    WaitingAvg = 0.0f;
  }

  public void Schedule() {
    int Counter = 0; // counting time for the system
    int FinishedProcesses = 0;
    int index = -1; // index of processes
    int Size = processes.size();
    while (FinishedProcesses < processes.size()) {
      if (processes.get((index + 1) % Size).BurstTime == 0) {
        while (processes.get((index + 1) % Size).BurstTime == 0) {
          index = (index + 1) % Size; // circular
        }
      } else
        index = (index + 1) % Size;
    if (processes.get(index).ArrivalTime <= Counter && BurstTimes.get(index) > 0) { // arrived process and has some
                                                                                    // work to do
      BurstTimes.set(index, BurstTimes.get(index) - Quantum);
    } else {
      Counter++;
      continue;
    }
    System.out.print(processes.get(index).Name + '|');// Print order of execution
    Counter += Quantum;
    if (BurstTimes.get(index) <= 0) {
      // Process finished work
      FinishedProcesses++;
      processes.get(index).Turnaround = Counter - processes.get(index).ArrivalTime;
      processes.get(index).Waiting = processes.get(index).Turnaround - processes.get(index).BurstTime;
    }
    Counter += ContextSwitching;
  }System.out.println("\n");System.out.println("Process Name"+"     "+"ArrivalTime"+"     "+"BurstTime"+"     "+"Priority"+"      "+"Queue_Number"+"      "+"TurnAround Time"+"      "+"Waiting Time"+"      "+"Quantum"+"       "+"Context");for(

  int i = 0;i<processes.size();i++)
  {
    System.out.println(processes.get(i).Name + "                    " + processes.get(i).ArrivalTime + "              "
        + processes.get(i).BurstTime + "           " + processes.get(i).Priority + "               "
        + processes.get(i).QueueNumber + "                " + processes.get(i).Turnaround + "                     "
        + processes.get(i).Waiting + "              " + Quantum + "            " + ContextSwitching);
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

    TurnaroundAvg = totalTurnarround / (float) R.size();
    WaitingAvg = totalWaiting / (float) R.size();

    System.out.println(
        "in RoundRobin, Average Turn Arround Time: " + TurnaroundAvg + " " + "Average Waiting Time: " + WaitingAvg);
  }
  public float Get_TAavg(){
    return TurnaroundAvg;
  }
  public float Get_Waitingavg(){
    return WaitingAvg;
  }
}