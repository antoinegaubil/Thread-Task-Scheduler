import java.util.*;

public class FairShare extends Algorithm {
    //get the data from the main
    private static LinkedList<String> task = Driver.getTasks();
    private static LinkedList<Integer> priority = Driver.getPriorities();
    private static LinkedList<Integer> burst = Driver.getBursts();
    private static LinkedList<String> pidTask = new LinkedList<>(task);
    private static LinkedList<Integer> turnaroundTime = new LinkedList<>();
    private static LinkedList<Integer> waitingTime = new LinkedList<>();
    private static LinkedList<String> taskQueue = new LinkedList<>(task);
    private static float currentBurst = 0;

    public static boolean FairShare() {

        if (task.size() == 0) { //if list is empty, set up the waiting time and turnaround time values
            int TurnaroundTime = 0;
            int WaitingTime = 0;

            for (int i = 0; i < pidTask.size(); i++) {
                int taskTurnaroundTime = turnaroundTime.get(i);//the turnaround time values at each step are stored in a list
                int taskWaitingTime = waitingTime.get(i);//the waiting time values at each step are stored in a list

                TurnaroundTime += taskTurnaroundTime; //get the sum
                WaitingTime += taskWaitingTime; //get the sum
            }

            int averageTurnaroundTime = TurnaroundTime / pidTask.size();//get the average
            int averageWaitingTime = WaitingTime / pidTask.size();//get the average

            System.out.println("Average times: waiting [" + averageWaitingTime + "ms], turnaround: [" + averageTurnaroundTime + "ms]");

            return false;//exit loop
        }


        Map<Integer, List<Integer>> result = findDuplicateIndexes(priority); //call the function at the bottom that will let us know where the user id duplicates are for fair sharing
        // if input is
        //T1, 4, 20
        //T2, 2, 25
        //T3, 3, 25
        //T4, 3, 15
        //T5, 2, 10
        //output will be {2=[1, 4], 3=[2, 3]} --> user id 2 are present at indexes 1 and 4


        float burstTime = 0;
        int realQuantum = 0;

        for (int i = 0; i < task.size(); i++) {

            burstTime = burst.get(i);
            System.out.println("Will run : " + task.get(i));
            System.out.println("Tid : " + pidTask.indexOf(task.get(i)));
            System.out.println("User Id : " + priority.get(i));
            System.out.println("Burst Time : " + burstTime);
            System.out.println("\n");

            if (result.containsKey(priority.get(i))) { //if the current priority user id is within the result list explained above
                List<Integer> indices = result.get(priority.get(i));
                realQuantum = (10 / (priority.size() - result.size())) / indices.size(); //set up a new quantum (10 / (unique amount of user ids) ) / how many processes in that specific user id

            } else {
                realQuantum = 10 / (priority.size() - result.size()); // if not simply set the quantum as 10 / number of unique user id
            }

            if (burstTime <= realQuantum) { //that means the task will finish in this loop.

                currentBurst += burstTime; //add all the burst times
                turnaroundTime.add((int) currentBurst); //burst time = CT - AT = CT since AT = 0
                waitingTime.add((int) (currentBurst - burstTime)); //waiting time = (CT - AT) - BT

                System.out.println("Task " + task.get(i) + " finished \n");

                task.remove(i); //remove from queue
                priority.remove(i);
                burst.remove(i);

            } else {
                currentBurst += realQuantum; //add the total burst time in order to determine the completion time at the end.
                turnaroundTime.add((int) currentBurst); //add to the list for average after
                waitingTime.add((int) (currentBurst - realQuantum));//same with waiting

                burst.set(i, (int) (burstTime - realQuantum));//set the new burst time in the burst linked list - the quantum.
            }
        }

        return true;

    }


    //this function finds duplicates from the priority list which is the user id list in this case.
    public static Map<Integer, List<Integer>> findDuplicateIndexes(LinkedList<Integer> priority) {

        Map<Integer, List<Integer>> result = new HashMap<>();//using HashMaps

        if (priority != null && priority.size() > 1) {//if list is not empty

            Map<Integer, List<Integer>> valueToIndexMap = new HashMap<>();//second hashmaps for the indexes

            for (int i = 0; i < priority.size(); i++) {//enter the loop to find duplicates


                if (valueToIndexMap.containsKey(priority.get(i))) { //if the index is already in the map
                    valueToIndexMap.get(priority.get(i)).add(i); //add another index at the specified key.
                } else {
                    List<Integer> indexList = new ArrayList<>();
                    indexList.add(i); // add a key to the map
                    valueToIndexMap.put(priority.get(i), indexList);//add the index corresponding to the key
                }
            }

            for (Map.Entry<Integer, List<Integer>> entry : valueToIndexMap.entrySet()) {
                List<Integer> indexList = entry.getValue();

                if (indexList.size() > 1) {
                    result.put(entry.getKey(), indexList); //add the value of the key to the result Map
                }
            }
        }

        return result;
    }

    @Override
    public void schedule() {

    }

    @Override
    public Task pickNextTask() {
        return null;
    }
}