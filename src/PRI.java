import java.util.LinkedList;

public class PRI extends Algorithm {

    //get the data from the main
    private static LinkedList<String> task = Driver.getTasks();
    private static LinkedList<Integer> priority = Driver.getPriorities();
    private static LinkedList<Integer> burst = Driver.getBursts();
    private static LinkedList<String> pidTask = new LinkedList<>(task);
    private static LinkedList<Integer> turnaroundTime = new LinkedList<>();
    private static LinkedList<Integer> waitingTime = new LinkedList<>();
    private static int currentBurst = 0;

    public static boolean PRI() {
        int maximum = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < priority.size(); i++) {
            int currPriority = priority.get(i);
            if (currPriority > maximum) { //find maximum priority
                maximum = currPriority;
                index = i;
            }
        }


        if (index != -1) {

            System.out.println("Will run : " + task.get(index));
            System.out.println("Tid : " + pidTask.indexOf(task.get(index)));
            System.out.println("Priority : " + priority.get(index));
            System.out.println("Burst Time : " + burst.get(index));
            System.out.println("\n");
            System.out.println("Task " + task.get(index)+ " finished.\n");

            currentBurst += burst.get(index); //add all the burst times
            turnaroundTime.add(currentBurst); //burst time = CT - AT = CT since AT = 0
            waitingTime.add(currentBurst - burst.get(index)); //waiting time = (CT - AT) - BT


            task.remove(index); //remove from queue since it is done.
            priority.remove(index);
            burst.remove(index);
            return true;
        } else {

            int TurnaroundTime = 0;
            int WaitingTime = 0;

            for (int i = 0; i < pidTask.size(); i++) {//calculate average waiting and turnaround times
                int taskTurnaroundTime = turnaroundTime.get(i);
                int taskWaitingTime = waitingTime.get(i);

                TurnaroundTime += taskTurnaroundTime;//sum
                WaitingTime += taskWaitingTime;//sum
            }

            int averageTurnaroundTime = TurnaroundTime / pidTask.size();//average
            int averageWaitingTime = WaitingTime / pidTask.size();//average

            System.out.println("Average times: waiting [" + averageWaitingTime + "ms], turnaround: [" + averageTurnaroundTime + "ms]");

            return false;
        }
    }

    @Override
    public void schedule() {

    }

    @Override
    public Task pickNextTask() {
        return null;
    }
}
