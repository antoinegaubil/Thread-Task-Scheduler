import java.util.LinkedList;

public class Schedulers {
    private static LinkedList<String> task = Main.getTasks();
    private static LinkedList<Integer> priority = Main.getPriorities();
    private static LinkedList<Integer> burst = Main.getBursts();
    private static LinkedList<String> pidTask = new LinkedList<>(task);
    private static LinkedList<Integer> turnaroundTime = new LinkedList<>();
    private static LinkedList<Integer> waitingTime = new LinkedList<>();
    private static int currentBurst = 0;

    public static void printLists() {
        System.out.println("Tasks: " + task);
        System.out.println("Priority: " + priority);
        System.out.println("Burst: " + burst);
    }

    public static boolean PriorityScheduling() {
        int minimum = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < priority.size(); i++) {
            int currPriority = priority.get(i);
            if (currPriority < minimum) {
                minimum = currPriority;
                index = i;
            }
        }

        if (index != -1) {

            System.out.println("Will run : " + task.get(index));
            System.out.println("Tid : " + pidTask.indexOf(task.get(index)));
            System.out.println("Priority : " + priority.get(index));
            System.out.println("Burst Time : " + burst.get(index));
            System.out.println("\n");

            currentBurst += burst.get(index);
            turnaroundTime.add(currentBurst);
            waitingTime.add(currentBurst - burst.get(index));

            task.remove(index);
            priority.remove(index);
            burst.remove(index);

            return true;
        } else {

            int TurnaroundTime = 0;
            int WaitingTime = 0;

            for (int i = 0; i < pidTask.size(); i++) {
                int taskTurnaroundTime = turnaroundTime.get(i);
                int taskWaitingTime = waitingTime.get(i);

                TurnaroundTime += taskTurnaroundTime;
                WaitingTime += taskWaitingTime;
            }

            int averageTurnaroundTime = TurnaroundTime / pidTask.size();
            int averageWaitingTime = WaitingTime / pidTask.size();

            System.out.println("Average times: waiting [" + averageWaitingTime + "ms], turnaround: [" + averageTurnaroundTime + "ms]");

            return false;
        }
    }
}

