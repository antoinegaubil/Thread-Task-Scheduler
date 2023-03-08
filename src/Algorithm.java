import java.util.LinkedList;

public abstract class Algorithm {
    public abstract void schedule();

    public abstract Task pickNextTask();

    public static class FCFS extends Algorithm {
        private LinkedList<Task> taskQueue;

        public void schedule() {
            while (CPU.run("FCFS",2)) {
            }
        }

        public Task pickNextTask() {
            return taskQueue.removeFirst();
        }

    }
    public static class SJF extends Algorithm {
        private LinkedList<Task> taskQueue;

        public void schedule() {
            while (CPU.run("SJF",2)) {
            }
        }

        public Task pickNextTask() {
            return taskQueue.removeFirst();
        }

    }
    public static class PRI extends Algorithm {
        private LinkedList<Task> taskQueue;

        public void schedule() {
            while (CPU.run("PRI",2)) {
            }
        }

        public Task pickNextTask() {
            return taskQueue.removeFirst();
        }

    }
    public static class RR extends Algorithm {
        private LinkedList<Task> taskQueue;

        public void schedule() {
            while (CPU.run("RR",2)) {
            }
        }

        public Task pickNextTask() {
            return taskQueue.removeFirst();
        }

    }

    public static class FairShare extends Algorithm {
        private LinkedList<Task> taskQueue;

        public void schedule() {
            while (CPU.run("FairShare",2)) {
            }
        }

        public Task pickNextTask() {
            return taskQueue.removeFirst();
        }

    }
}
