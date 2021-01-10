import java.util.*;

public class FCFS {
  ArrayList<Process> processes;
  ArrayList<Integer>BurstTimes=new ArrayList<Integer>();
  float TurnaroundAvg ;
  float WaitingAvg; 
  FCFS(ArrayList<Process> p) {
    processes = new ArrayList<Process>(p);
    for(int i=0;i<processes.size();i++)
      BurstTimes.add(processes.get(i).BurstTime);
      TurnaroundAvg = 0.0f;
      WaitingAvg = 0.0f;
  }

  public void Schedule() {
    int counter = 0; // refer to current time
    int FinishedProcesses = 0;
    // create Result ArrayList to store result
    while (FinishedProcesses < processes.size()) {
      int index = get_minArrival(counter);
      if(index == -1 ){
        counter++;
        continue;
      }
      //int burst = processes.get(index).BurstTime;
      while (BurstTimes.get(index) > 0) {// having some time to do process
        counter++;
        BurstTimes.set(index,BurstTimes.get(index) -1 );
      }
      FinishedProcesses++;
      // assign Turnaround time
      processes.get(index).Turnaround = Math.abs(counter - processes.get(index).ArrivalTime);
      // assign Waiting time
      processes.get(index).Waiting = processes.get(index).Turnaround - processes.get(index).BurstTime;
    }
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
    float totalTurnarround=0.0f;
    float totalWaiting=0.0f ;
    for (int i = 0; i < R.size(); i++) {
      totalTurnarround += R.get(i).Turnaround;
      totalWaiting += R.get(i).Waiting;
    }

    TurnaroundAvg = totalTurnarround / (float) R.size();
    WaitingAvg = totalWaiting / (float) R.size();

    System.out.println("in First-Come-First-Served, Average Turn Arround Time: " + TurnaroundAvg + " "
        + "Average Waiting Time: " + WaitingAvg);
  }

  // get minimum arrival time from processes
  int get_minArrival(int Timer) {
    int min_index = 0;
    int min_Arrival = Integer.MAX_VALUE;
    boolean Found=false;
    for (int i = 0; i < processes.size(); i++) {
      if (processes.get(i).ArrivalTime <= min_Arrival 
          && processes.get(i).ArrivalTime <= Timer
          && BurstTimes.get(i)> 0) {
        min_Arrival = processes.get(i).ArrivalTime;
        min_index = i;
        Found=true;
        break;
      }
    }
    if(Found)
      return min_index;
    return -1;
  }
  
  public float Get_TAavg(){
    return TurnaroundAvg;
  }
  public float Get_Waitingavg(){
    return WaitingAvg;
  }
}