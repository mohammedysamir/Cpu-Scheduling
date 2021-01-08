import java.util.*;

public class RoundRobin{
int ContextSwitching;
int Quantum;
ArrayList<Process> process=new ArrayList<Process>();
RoundRobin(ArrayList<Process> P,int c,int Q)
{
process=P;
ContextSwitching=c;
Quantum=Q;
}

public void Schedule()
{
  /*
  1. we work on First index of ArrayList
  2. if ArrayList.isEmpty() ===>work is done
  3. else 
        process.get(0).BurstTime-=Quantum;
        Sysout(process.get(0)->Name+'|')
        if(process.get(0)->BurstTime > 0)
          1.remove
          2.add
        else{
          1.remove only
        }
  
  */
  //Loop if at least there is one process
  ArrayList<Process> Result=new ArrayList<Process>();
  int Counter=0;    //counting time for the system
  while(process.isEmpty()==false)
  {
    process.get(0).BurstTime-=Quantum;
    //Increase Counter
    Counter+=Quantum+ContextSwitching;
    Process temp=process.get(0);
    System.out.print(temp.Name+'|');
    if(temp.BurstTime>0)
    {
      process.remove(0);
      process.add(temp);
    }
    else
     {
     /*process.get(0).Turnaround=Counter-process.get(0).ArrivalTime;
     process.get(0).Waiting=process.get(0).Turnaround-process.get(0).BurstTime;
     */
     temp.Turnaround=Counter-temp.ArrivalTime;
     temp.Waiting=temp.Turnaround-temp.BurstTime;
     process.remove(0);
     Result.add(temp);
     }
  }

  GetAvrg(Result);



  /*int counter=0;
  int minus=BurstTime-Quantum;
  ArrayList<Process> quantum=Process;
  while(minus<=Quantum)
  { 
 counter++;
 minus--;

  }*/
  //assign Turnaround



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
    
    System.out.println("in RoundRobin, Average Turn Arround Time: "+TurnaroundAvg+" "+ "Average Waiting Time: "+WaitingAvg );
  }
}