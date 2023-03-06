import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.*;


public class Schedulers {
    private static LinkedList<String> task = Main.getTasks();
    private static LinkedList<Integer> priority = Main.getPriorities();
    private static LinkedList<Integer> burst = Main.getBursts();
    private static LinkedList<String> pidTask = new LinkedList<>(task);
    private static LinkedList<Integer> turnaroundTime = new LinkedList<>();
    private static LinkedList<Integer> waitingTime = new LinkedList<>();
    static LinkedList<String> waitingQueue = new LinkedList<String>();
    private static int currentBurst = 0;

    public static void printLists() {
        System.out.println("Tasks: " + task);
        System.out.println("Priority: " + priority);
        System.out.println("Burst: " + burst);
    }

    public static boolean PriorityScheduling() {
        int maximum = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < priority.size(); i++) {
            int currPriority = priority.get(i);
            if (currPriority > maximum) {
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

    public static boolean SJF() {
        int index = -1;
        // create a list of indices to sort the burst times
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < burst.size(); i++) {
            indexList.add(i);
        }
        // sort the indices based on the burst times
        Collections.sort(indexList, (a, b) -> burst.get(a) - burst.get(b));

        int time = 0; // current time
        int totalTasks = task.size(); // total number of tasks
        int completedTasks = 0; // number of completed tasks

        while (completedTasks < totalTasks) {
            // check for tasks with arrival time <= current time
            for (int i = 0; i < task.size(); i++) {
                if (burst.get(i) > 0 && time >= currentBurst && time >= priority.get(i)) {
                    waitingQueue.add(String.valueOf(i));
                }
            }
            // check if waiting queue is empty
            if (waitingQueue.isEmpty()) {
                time++; // increment time if no tasks are waiting


            } else {
                int shortestBurstIndex = Integer.parseInt(waitingQueue.getFirst());
                int shortestBurstTime = burst.get(shortestBurstIndex);
                // select the task with shortest burst time
                for (int i = 1; i < waitingQueue.size(); i++) {
                    index = Integer.parseInt(waitingQueue.get(i));
                    int burstTime = burst.get(index);
                    if (burstTime < shortestBurstTime) {
                        shortestBurstIndex = index;
                        shortestBurstTime = burstTime;
                    }
                }
                // update waiting time and turnaround time for the selected task
                waitingTime.add(time - priority.get(shortestBurstIndex));
                turnaroundTime.add(time + shortestBurstTime - priority.get(shortestBurstIndex));
                // decrement burst time and update currentBurst time
                burst.set(shortestBurstIndex, 0);
                currentBurst = time + shortestBurstTime;
                // remove task from waiting queue and increment completedTasks count
                waitingQueue.remove((Integer) shortestBurstIndex);
                completedTasks++;


            }

        }
        return true;
    }

    public static boolean fairShare() {
        int quantum = 0;
        int time = 0;
        int processCount = task.size();
        HashMap<String, Integer> userCounts = new HashMap<String, Integer>();
        HashMap<String, Integer> processCounts = new HashMap<String, Integer>();

        // Count the number of processes and users
        for (int i = 0; i < processCount; i++) {
            String user = pidTask.get(i).split(" ")[1];
            if (!userCounts.containsKey(user)) {
                userCounts.put(user, 1);
                processCounts.put(user, 0);
            } else {
                userCounts.put(user, userCounts.get(user) + 1);
            }
        }

        // Calculate the time quantum for each user
        HashMap<String, Integer> quantumMap = new HashMap<String, Integer>();
        for (String user : userCounts.keySet()) {
            int userCount = userCounts.get(user);
            quantumMap.put(user, Math.max(10, burst.get(currentBurst) * userCount / processCount));
        }

        // Run the scheduling algorithm
        while (true) {
            boolean done = true;
            for (String user : userCounts.keySet()) {
                int userCount = userCounts.get(user);
                int quantumSize = quantumMap.get(user);
                int userQuantum = 0;
                for (int i = 0; i < processCount; i++) {
                    String pid = pidTask.get(i).split(" ")[0];
                    String processUser = pidTask.get(i).split(" ")[1];
                    int processBurst = burst.get(i);
                    if (user.equals(processUser) && processBurst > 0) {
                        done = false;
                        if (processCounts.get(user) >= userCount) {
                            break;
                        }
                        int timeDiff = Math.min(quantumSize - userQuantum, processBurst);
                        processBurst -= timeDiff;
                        burst.set(i, processBurst);
                        userQuantum += timeDiff;
                        time += timeDiff;
                        if (processBurst == 0) {
                            waitingTime.add(time - processCount - burst.get(i));
                            turnaroundTime.add(time - processCount);
                            currentBurst++;
                            processCounts.put(user, processCounts.get(user) + 1);
                        }
                    }
                }
            }
            if (done) {
                break;
            }
        }

        // Print the results
        return true;
    }
}



