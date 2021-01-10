import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int Burst, Arrival, ContextSwitching, Priority, Quantum, Queue_num, ProcessNumbers;
    String Name;

    ArrayList<Process> Processes = new ArrayList<Process>();

    System.out.print("Enter number of processes: ");
    ProcessNumbers = scan.nextInt();
    System.out.print("Fill processes' info: ");
    for (int i = 0; i < ProcessNumbers; i++) {
      scan.nextLine();
      System.out.println("-------------------------------------------------");
      System.out.print("Enter Name of processes: ");
      Name = scan.nextLine();
      System.out.print("Enter Arrival Time of processes: ");
      Arrival = scan.nextInt();
      System.out.print("Enter Burst Time of processes: ");
      Burst = scan.nextInt();
      System.out.print("Enter Priority of processes: ");
      Priority = scan.nextInt();
      System.out.print("Enter Queue number of processes: ");
      Queue_num = scan.nextInt();
      Process p = new Process(Name, Arrival, Burst, Priority, Queue_num);// create process
      Processes.add(p);// add process to arraylist
    }
    System.out.println("-------------------------------------------------");

    System.out.print("Enter Context Switching time: ");
    ContextSwitching = scan.nextInt();
    System.out.print("Enter Quantum Time: ");
    Quantum = scan.nextInt();
    do
    {int Choice;
    System.out.print("1. SRTF" + "\n2. Round Robin" + "\n3. Priority Queue" + "\n4. Multi-level Queue\n");
    System.out.print("Enter your Choice:");
    Choice = scan.nextInt();
    switch (Choice) {
      case 1:
        System.out.println("In SRTF Queue :---------------------------");
        SJF sjf = new SJF(Processes, ContextSwitching);
        sjf.Schedule();
        break;
      case 2:
        System.out.println("In RR :---------------------------");
        RoundRobin r = new RoundRobin(Processes, ContextSwitching, Quantum);
        r.Schedule();
        break;
      case 3:
        System.out.println("In Priority Queue :---------------------------");
        PriorityQ p = new PriorityQ(Processes);
        p.Schedule();

        break;
      case 4:
        break;
      default:
      System.out.println("Not a available choice");
      break;
    }

    /*
     * System.out.println("In FCFS :---------------------------"); 
     * FCFS f = new
     * FCFS(Processes); f.Schedule();
     * 
     */
    scan.nextLine();
    String answer;
    System.out.println("do you want to continue with another algorithm");
    answer=scan.nextLine();
    if(answer.equalsIgnoreCase("no"))break;
    
  }while(true);
  scan.close();
  }

}