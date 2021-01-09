import java.util.*;

public class SJF{
  int ContextSwitching;
  ArrayList<Process> processes;
  ArrayList<Integer> BurstTimes=new ArrayList<Integer>();

  SJF( ArrayList<Process> p , int C){
    ContextSwitching=C;
    processes=new ArrayList<Process> (p);

    for(int i=0;i<processes.size();i++){ //copy Burst times of processes
      BurstTimes.add(processes.get(i).BurstTime);
    }
  }
  
  public void Schedule(){
     //to save result after assigning TA time and waiting time to get avg
     ArrayList<Process> Result=new ArrayList<Process>(); 
     int Counter=0; //Timer
     int index=0; //refer to index of process
     while(!processes.isEmpty()){
       index=get_minArrival(Counter);
       if(index != -1){

        System.out.print(processes.get(index).Name + '|');// print name of new process after switch
        // Counter+=processes.get(index).BurstTime;
        Counter++;
        // decreament BurstTime of process
        BurstTimes.set(index, BurstTimes.get(index) -1 );

        if (BurstTimes.get(index) == 0) // remove if finished
        // calculate Trunaround and waiting
        {
          processes.get(index).Turnaround = Math.abs(Counter - processes.get(index).ArrivalTime);
          processes.get(index).Waiting = processes.get(index).Turnaround - processes.get(index).BurstTime;
          Result.add(processes.get(index));
          processes.remove(index);
          BurstTimes.remove(index);
        }
       }
       else{
         Counter++;
         continue;
       }
     }
     GetAvrg(Result);
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
  int get_minArrival(int Timer) {
    int min_index = 0;
    int min_Arrival = Integer.MAX_VALUE;
    boolean Found = false;
    for (int i = 0; i < processes.size(); i++) {
      // if process was in interval from 0 to Timer and has the lowest proiortiy set
      // index to i
      if (processes.get(i).ArrivalTime <= min_Arrival && processes.get(i).ArrivalTime <= Timer) {
        min_Arrival = processes.get(i).ArrivalTime;
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
