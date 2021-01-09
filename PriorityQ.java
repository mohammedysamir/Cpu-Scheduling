import java.util.*;
import java.lang.*;
public class PriorityQ
{
  ArrayList<Process> processes;
  PriorityQ(ArrayList<Process> p){
    processes=new ArrayList<Process>(p);
  }
  /*
  1. get minimum Priority
  2. Counter+=process.get(index).BurstTime
  3. Calculate Turnaround and Waiting
        Turnaround=Counter-Arrival
        Waiting=Turnaround-BurstTime
  4. remove process.remove(index);
  */
  public void Schedule(){
  int Counter=0;
  ArrayList<Process> Result=new ArrayList<Process>();
  while(processes.isEmpty()==false){
    //need to handle ArrivalTime 
  int index=get_minPriority(Counter);
  if(index != -1){
  Counter+=processes.get(index).BurstTime;

  processes.get(index).Turnaround=Math.abs(Counter-processes.get(index).ArrivalTime);
  processes.get(index).Waiting=processes.get(index).Turnaround-processes.get(index).BurstTime;

  System.out.print(processes.get(index).Name+'|');
  Result.add(processes.get(index));
  processes.remove(index);
  }else {
    Counter++;
    continue;
  }
  }
  GetAvrg(Result);
  }
  
  
   public void GetAvrg(ArrayList<Process> R){
    float totalTurnarround=0.0f;
    float totalWaiting=0.0f;
    for(int i=0;i<R.size();i++)
    {
      totalTurnarround+=R.get(i).Turnaround;
      totalWaiting+=R.get(i).Waiting;
    }

    float TurnaroundAvg=totalTurnarround/(float)R.size();
    float WaitingAvg=totalWaiting/(float)R.size();
    
    System.out.println("in Priority, Average Turn Arround Time: "+TurnaroundAvg+" "+ "Average Waiting Time: "+WaitingAvg );
  }

  //Get minimum Priority of all processes
  int get_minPriority(int Timer){
    int min_index=0;
    int min_Priority=Integer.MAX_VALUE;
    boolean Found=false;
    for(int i=0;i<processes.size();i++){
      //if process was in interval from 0 to Timer and has the lowest proiortiy set index to i
      if(processes.get(i).Priority<=min_Priority &&processes.get(i).ArrivalTime <=Timer){
        min_Priority=processes.get(i).Priority;
        min_index=i;
        Found=true;
      }
    }
    if(Found)
      return min_index;
    else return -1;
  }
}