import java.util.*;

public class SJF{
  int ContextSwitching;
  ArrayList<Process> process=new ArrayList<Process>();

  SJF( ArrayList<Process> p , int C){
    ContextSwitching=C;
    process=p;
  }
  
  public void Schedule(){
     
  }

  public void GetAvrg(){
    float totalTurnarround=0;
    float totalWaiting=0;
    for(int i=0;i<process.size();i++)
    {
      totalTurnarround+=process.get(i).Turnaround;
      totalWaiting+=process.get(i).Waiting;
    }

    float TurnaroundAvg=totalTurnarround/(float)process.size();
    float WaitingAvg=totalWaiting/(float)process.size();
    
    System.out.println("in shortest job first, Average Turn Arround Time: "+TurnaroundAvg+" "+ "Average Waiting Time: "+WaitingAvg );
  }

}
