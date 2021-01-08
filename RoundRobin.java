import java.util.*;
import java.lang.*;

public class RoundRobin{
int ContextSwitching;
int Quantum;
ArrayList<Process> process;
RoundRobin(ArrayList<Process> p,int c,int Q)
{
process=new ArrayList<Process>(p);
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
  ArrayList<Process> Result=new ArrayList<Process>();
  ArrayList<Integer> BurstTimes=new ArrayList<Integer>();
  for(int i=0;i<process.size();i++)
    BurstTimes.add(process.get(i).BurstTime); //adding bursttimes of each process to BurstTimes
  
  int Counter=0;    //counting time for the system

  int index=-1;  //index of processes
  int Size;
  while(!process.isEmpty()){
    Size=process.size();
    index=(index+1)%Size; //circular
    //calculate new Burst
    int Burst=BurstTimes.get(index);
    Burst-=Quantum;
    BurstTimes.remove(index);
    BurstTimes.add(index,Burst);

    System.out.print(process.get(index).Name+'|');//Print order of execution
    Counter+=Quantum+ContextSwitching;
    if(BurstTimes.get(index)<=0){
      //Process finished work
     process.get(index).Turnaround=Counter-process.get(index).ArrivalTime;
     process.get(index).Waiting=process.get(index).Turnaround-process.get(index).BurstTime;
     Result.add(process.get(index));
     process.remove(index); //remove from Queue
     BurstTimes.remove(index);
    }

  }


  GetAvrg(Result);

  /*while(process.isEmpty()==false)
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
     process.get(0).Turnaround=Counter-process.get(0).ArrivalTime;
     process.get(0).Waiting=process.get(0).Turnaround-process.get(0).BurstTime;
     /*temp.Turnaround=Counter-temp.ArrivalTime;
     temp.Waiting=temp.Turnaround-temp.BurstTime;
     process.remove(0);
     Result.add(process.get(0));
     }
  }*/

  
  /*int counter=0;
  int minus=BurstTime-Quantum;
  ArrayList<Process> quantum=Process;
  while(minus<=Quantum)
  { 
 counter++;
 minus--;

  }*/
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