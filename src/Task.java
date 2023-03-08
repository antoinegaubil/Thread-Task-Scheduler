import java.util.concurrent.atomic.AtomicInteger;

public class Task
{
    // the representation of each task
    private String name;
    private int tid;
    private int priority;
    private int burst;

    /**
     * We use an atomic integer to assign each task a unique task id.
     */
    private static AtomicInteger tidAllocator = new AtomicInteger();

    public Task(String name, int priority, int burst) {
        this.name = name;
        this.priority = priority;
        this.burst = burst;

        this.tid = tidAllocator.getAndIncrement();
    }

    /**
     * Appropriate getters
     */
    public String getName() {
        return name;
    }

    public int getTid() {
        return tid;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurst() {
        return burst;
    }

    public int setPriority(int priority) {
        this.priority = priority;

        return priority;
    }

    public int setBurst(int burst) {
        this.burst = burst;

        return burst;
    }

    public boolean equals(Object other) {
        if (other == this)
            return true;

        if (!(other instanceof Task))
            return false;

        Task rhs = (Task)other;
        return (this.tid == rhs.tid) ? true : false;
    }

    public String toString() {
        return
                "Name: " + name + "\n" +
                        "Tid: " + tid + "\n" +
                        "Priority: " + priority + "\n" +
                        "Burst: " + burst + "\n";
    }
}

