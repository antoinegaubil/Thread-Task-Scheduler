# Task Scheduler
This Java program serves as a task scheduler implementing various scheduling algorithms. The scheduler takes input from a file containing task details, including thread names, arrival times, execution times, and priority numbers. The supported scheduling algorithms include:

### First-Come, First-Serve (FCFS)
The FCFS algorithm schedules tasks based on their arrival time, ensuring that the first task to arrive is the first to be executed.

### Shortest Job First (SJF)
SJF prioritizes tasks with the shortest execution time first, minimizing waiting times and enhancing efficiency.

### Priority Scheduling (PRI)
Priority Scheduling assigns priority numbers to tasks, executing them in order of priority. Lower priority numbers indicate higher priority.

### Round Robin (RR)
Round Robin allocates a fixed time slice to each task in a cyclic manner, providing fair execution among tasks.

### Fair Share
The Fair Share algorithm ensures equitable distribution of resources among tasks, preventing resource monopolization.
