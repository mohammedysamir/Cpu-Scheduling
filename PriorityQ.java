import java.util.*;

public class PriorityQ {
  ArrayList<Process> processes;
  ArrayList<Integer> BurstTimes = new ArrayList<Integer>(); // Burst times of processes

  PriorityQ(ArrayList<Process> p) {
    processes = new ArrayList<Process>(p);
    for (int i = 0; i < p.size(); i++)
      BurstTimes.add(processes.get(i).BurstTime);
  }

  /*
   * 1. get minimum Priority 2. Counter+=process.get(index).BurstTime 3. Calculate
   * Turnaround and Waiting Turnaround=Counter-Arrival
   * Waiting=Turnaround-BurstTime 4. remove process.remove(index);
   */
  public void Schedule() {
    int FinishedProcesses = 0; // counter for number of finished processes
    int Counter = 0;
    int current_index = 0;
    int prv_index = 0;
    while (FinishedProcesses < processes.size()) {
      current_index = get_minPriority(Counter, BurstTimes);
      if (current_index != -1) {
        if (prv_index != current_index) {
          System.out.print(processes.get(prv_index).Name + '|');// print name of new process after switch
          // solve starvation problem
          if (processes.get(prv_index).Priority > 1) {
            int nPriority = processes.get(prv_index).Priority;
            nPriority--;
            processes.get(prv_index).setPriority(nPriority);
          }
        }
        Counter++;
        // decreament BurstTime of process
        BurstTimes.set(current_index, BurstTimes.get(current_index) - 1);

        if (BurstTimes.get(current_index) == 0) // remove if finished
        {
          // calculate Trunaround and waiting
          processes.get(current_index).Turnaround = Math.abs(Counter - processes.get(current_index).ArrivalTime);
          processes.get(current_index).Waiting = processes.get(current_index).Turnaround
              - processes.get(current_index).BurstTime;
          FinishedProcesses++;
        }
      } else {
        Counter++;
        continue;
      }
      prv_index = current_index;
    }
    // print last process executed
    System.out.print(processes.get(prv_index).Name);
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
        "in Priority, Average Turn Arround Time: " + TurnaroundAvg + " " + "Average Waiting Time: " + WaitingAvg);
  }

  // Get minimum Priority of all processes
  int get_minPriority(int Timer, ArrayList<Integer> Bursts) {
    int min_index = 0;
    int min_Priority = Integer.MAX_VALUE;
    boolean Found = false;
    for (int i = 0; i < processes.size(); i++) {
      // if process was in interval from 0 to Timer and has the lowest proiortiy set
      // index to i
      if (processes.get(i).Priority <= min_Priority && processes.get(i).ArrivalTime <= Timer && Bursts.get(i) > 0) {
        min_Priority = processes.get(i).Priority;
        min_index = i;
        Found = true;
      }
    }
    if (Found)
      return min_index;
    else
      return -1;
  }
}