public class FCFS implements Algorithm{
    private static LinkedList<String> task = Main.getTasks();
    List<Task> queue = new ArrayList<Task>();
    int index = 0;
    CPU cpu = new CPU();
    public FCFS(List<Task> queue) {
        this.queue = queue;
        int size = queue.size();
    }

    @Override
    public void schedule() {
        int[] waitTime = new int[size];
        int[] turnAroundTime = new int[size];
        int sumWaitTime = 0;
        int sumTurnAroundTime = 0;
        double averageWaitTime;
        double averageTurnAroundTime;
        System.out.println("FCFS Scheduling");
        System.out.println();
        Task nextTask;
        waitTime[0] = 0;
        for (int i = 0; i < size; i++) {
            nextTask = pickNextTask();
            cpu.run(nextTask, 0);
            System.out.println("Task " + nextTask.getName() + " has finished.");
            System.out.println();
            if ( i < size - 1){
                waitTime[i + 1] = waitTime[i] + nextTask.getBurst();
            }

            turnAroundTime[i] = waitTime[i] + nextTask.getBurst();
            sumWaitTime = sumWaitTime + waitTime[i];
            sumTurnAroundTime = sumTurnAroundTime + turnAroundTime[i];
        }
        averageWaitTime = (double) sumWaitTime/size;
        averageTurnAroundTime = (double) sumTurnAroundTime/size;
        System.out.println("Average times: waiting " + averageWaitTime + ", turnaround: " + averageTurnAroundTime);
    }

    @Override
    public Task pickNextTask() {
        if (index < size){
            index++;
            return queue.get(index - 1);
        }
        else
            return null;
    }
}
