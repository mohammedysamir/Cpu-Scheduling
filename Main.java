import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner scan=new Scanner(System.in);
    int Burst,Arrival,ContextSwitching,Priority,Quantum,Queue_num,ProcessNumbers;
    String Name;
    
    ArrayList<Process> Processes=new ArrayList<Process>();

    System.out.print("Enter number of processes: ");
    ProcessNumbers=scan.nextInt();
    for(int i=0;i<ProcessNumbers;i++)
    {
    scan.nextLine();
    
    System.out.print("Enter Name of processes: ");
    Name=scan.nextLine(); 
    System.out.print("Enter Arrival Time of processes: ");
    Arrival=scan.nextInt();
    System.out.print("Enter Burst Time of processes: ");
    Burst=scan.nextInt();
    System.out.print("Enter Priority of processes: ");
    Priority=scan.nextInt();
    System.out.print("Enter Queue number of processes: ");
    Queue_num=scan.nextInt();
    System.out.println("-------------------------------------------------");
    Process p=new Process(Name,Arrival,Burst,Priority,Queue_num);//create process
    Processes.add(p);//add process to arraylist
    }
    System.out.print("Enter Context Switching time: ");
    ContextSwitching=scan.nextInt();
    System.out.print("Enter Quantum Time: ");
    Quantum=scan.nextInt();

  }
  
}