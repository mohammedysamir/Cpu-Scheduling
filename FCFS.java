import java.util.*;

public class FCFS{
    ArrayList<Process> process;

  FCFS(ArrayList<Process> p){
    process=new ArrayList<Process>(p);
  }

  public void Schedule(){
    int counter=0;    //refer to current time
    //create Result ArrayList to store result
    ArrayList<Process> Result=new ArrayList<Process>(); 
    while(!process.isEmpty()){
      int index=get_minArrival();
      int burst=process.get(index).BurstTime;
      while(burst > 0){//having some time to do process
        counter++;
        burst--;
      }
      //assign Turnaround time
      process.get(index).Turnaround=counter-process.get(index).ArrivalTime;
      //assign Waiting time
      process.get(index).Waiting=process.get(index).Turnaround-process.get(index).BurstTime; 
      //Print data of Process
      process.get(index).ShowInfo();
      //remove from Queue
      Result.add(process.get(index));
      process.remove(index);
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
    
    System.out.println("in First-Come-First-Served, Average Turn Arround Time: "+TurnaroundAvg+" "+ "Average Waiting Time: "+WaitingAvg );
  }

  //get minimum arrival time from processes
  int get_minArrival(){
    int min_index=0;
    int min_Arrival=Integer.MAX_VALUE;

    for(int i=0;i<process.size();i++){
      if(process.get(i).ArrivalTime<=min_Arrival){
        min_Arrival=process.get(i).ArrivalTime;
        min_index=i;
      }
    }
    return min_index;
  }

}