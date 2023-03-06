public class RoundRobin implements Algorithm {
    
    private List<Task> queue;
    private int quantum;
    private CPU cpu;
    
    public RoundRobin(List<Task> queue, int quantum) {
        this.queue = queue;
        this.quantum = quantum;
        cpu = new CPU();
    }

    @Override
    public void schedule() {
        int[] waitTime = new int[queue.size()];
        int[] turnAroundTime = new int[queue.size()];
        int time = 0;
        int sumWaitTime = 0;
        int sumTurnAroundTime = 0;
        double averageWaitTime;
        double averageTurnAroundTime;
        System.out.println("Round Robin Scheduling");
        System.out.println();
        while (!queue.isEmpty()) {
            Task currentTask = queue.get(0);
            int burst = currentTask.getBurst();
            if (burst > quantum) {
                cpu.run(currentTask, quantum);
                time += quantum;
                queue.remove(0);
                queue.add(currentTask);
            } else {
                cpu.run(currentTask, burst);
                time += burst;
                queue.remove(0);
                waitTime[currentTask.getId()] = time - currentTask.getBurst() - currentTask.getArrivalTime();
                turnAroundTime[currentTask.getId()] = time - currentTask.getArrivalTime();
                sumWaitTime += waitTime[currentTask.getId()];
                sumTurnAroundTime += turnAroundTime[currentTask.getId()];
            }
        }
        averageWaitTime = (double) sumWaitTime / queue.size();
        averageTurnAroundTime = (double) sumTurnAroundTime / queue.size();
        System.out.println("Average times: waiting " + averageWaitTime + ", turnaround: " + averageTurnAroundTime);
    }

    @Override
    public Task pickNextTask() {
        if (queue.isEmpty()) {
            return null;
        } else {
            return queue.get(0);
        }
    }
}
