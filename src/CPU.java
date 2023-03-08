public class CPU
{

    public static boolean run(String task, int slice) {

        if(task == "FCFS")
        {
            System.out.println("\nFCFS SCHEDULING\n");
            Driver.FCFS();
        }
        if(task == "SJF")
        {
            System.out.println("\nSJF SCHEDULING\n");
            Driver.SJF();
        }
        if(task == "PRI")
        {
            System.out.println("\nPRIORITY SCHEDULING\n");
            Driver.PriorityScheduling();
        }
        if(task == "FairShare")
        {
            System.out.println("\nFAIR SHARE SCHEDULING\n");
            Driver.FairShare();
        }
        if(task == "RR")
        {
            System.out.println("\nROUND ROBIN SCHEDULING\n");
            Driver.RoundRobin();
        }


        return false;
    }
}