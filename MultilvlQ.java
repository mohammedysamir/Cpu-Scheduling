/*
1. First Q is RoundRobin
2. SecondQ is FCFS
3. Enters second Q if RoundRobin is empty
4. don't change Q
*/
import java.util.*;
public class MultilvlQ{
  ArrayList<Process>RR=new ArrayList<Process>(); //Q number ==1
  ArrayList<Process>fcfs=new ArrayList<Process>();//Q number ==2
  int Quantum;
  RoundRobin Q1;
  FCFS Q2;
  float TurnaroundAvg ;
  float WaitingAvg; 
  MultilvlQ(int Q,ArrayList<Process> p)
  {
    Quantum=Q;
    TurnaroundAvg = 0.0f;
    WaitingAvg = 0.0f;
    //assign processes
    for(int i=0;i<p.size();i++)
    {
      if(p.get(i).QueueNumber==1)
      {
        RR.add(p.get(i));
      }
      else if(p.get(i).QueueNumber==2)
      {
        fcfs.add(p.get(i));
      }
    }
  }
  public void Schedule()
  {
    Q1=new RoundRobin(RR,0,Quantum);
    Q2=new FCFS(fcfs);
    Q1.Schedule();
    Q2.Schedule();
    GetAvrg();
  }

  public void GetAvrg(){
    float turnaround=(Q1.Get_TAavg()+Q2.Get_TAavg())/2;
    float Waiting=(Q1.Get_Waitingavg()+Q2.Get_Waitingavg())/2;
    System.out.println(
      "in Multi_Level_Q, Average Turn Arround Time: " + turnaround + " " + "Average Waiting Time: " + Waiting);
  }
  public float Get_TAavg(){
    return TurnaroundAvg;
  }
  public float Get_Waitingavg(){
    return WaitingAvg;
  }
}