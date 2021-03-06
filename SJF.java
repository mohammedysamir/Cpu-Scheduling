import java.util.*;

public class SJF {
  int ContextSwitching;
  ArrayList<Process> processes;
  ArrayList<Integer> BurstTimes = new ArrayList<Integer>();
  float TurnaroundAvg ;
  float WaitingAvg; 

  SJF(ArrayList<Process> p, int C) {
    ContextSwitching = C;
    processes = new ArrayList<Process>(p);
    TurnaroundAvg = 0.0f;
    WaitingAvg = 0.0f;
    for (int i = 0; i < processes.size(); i++) { // copy Burst times of processes
      BurstTimes.add(processes.get(i).BurstTime);
    }
  }

  public void Schedule() {
    int FinishedProcesses = 0; // counter for number of finished processes
    int Counter = 0;
    int current_index = 0;
    int prv_index = 0;
    while (FinishedProcesses < processes.size()) {
      current_index = get_minArrival(Counter, BurstTimes);
      if (current_index != -1) {
        if (prv_index != current_index) {
          System.out.print(processes.get(prv_index).Name + '|');// print name of new process after switch
          // solve starvation problem
          Counter += ContextSwitching;
        }
        // decreament BurstTime of process
        BurstTimes.set(current_index, BurstTimes.get(current_index) - 1);
        Counter++;

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
    System.out.println(processes.get(current_index).Name);// print name of last process executed
    //Show info of each process
    System.out.println("Process Name"+"     "+"ArrivalTime"+"     "+"BurstTime"+"     "+"Priority"+"      "+"Queue_Number"+"      "
    +"TurnAround Time"+"      "+"Waiting Time");
    for(int i=0;i<processes.size();i++){
      System.out.println(processes.get(i).Name+"                    "+processes.get(i).ArrivalTime+"              "+
      processes.get(i).BurstTime+"           "+processes.get(i).Priority+"               "+
      processes.get(i).QueueNumber+"                "+processes.get(i).Turnaround+
      "                       "+processes.get(i).Waiting);
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
        "in Priority, Average Turn Arround Time: " + TurnaroundAvg + " " + "Average Waiting Time: " + WaitingAvg);
  }

  // Get minimum Arrival of all processes
  int get_minArrival(int Timer, ArrayList<Integer> Bursts) {
    int min_index = 0;
    int min_Burst=Integer.MAX_VALUE;
    boolean Found = false;
    for (int i = 0; i < processes.size(); i++) {
      // if process was in interval from 0 to Timer and has the lowest proiortiy set
      // index to i
      if (processes.get(i).ArrivalTime <= Timer 
      && Bursts.get(i) > 0
      &&Bursts.get(i) <min_Burst) {
        min_index = i;
        min_Burst=Bursts.get(i);
        Found = true;
      }
    }
    if (Found)
      return min_index;
    else
      return -1;
  }

  public float Get_TAavg(){
    return TurnaroundAvg;
  }
  public float Get_Waitingavg(){
    return WaitingAvg;
  }

}
