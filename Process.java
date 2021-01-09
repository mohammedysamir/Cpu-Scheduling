public class Process{
public String Name;
public int ArrivalTime,BurstTime,Priority,QueueNumber;
public int Turnaround,Waiting;
Process(String N,int A,int B,int P,int Q)
{
  Name=N;
  ArrivalTime=A;
  BurstTime=B;
  Priority=P;
  QueueNumber=Q;
  Turnaround=0;
  Waiting=0;
}

public void ShowInfo(){
  //print info of process
  System.out.println("Name: "+Name+" Arrival: "+ArrivalTime+" Burst: "+BurstTime+" TurnAround: "+Turnaround+" Wating: "+Waiting);
}
public void setPriority(int newPriority){
  Priority=newPriority;
}

}