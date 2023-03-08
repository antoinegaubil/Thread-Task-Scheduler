import java.util.*;
import java.io.*;

public class Driver
{
    public static LinkedList<String> task = new LinkedList<String>();
    public static LinkedList<Integer> priority = new LinkedList<Integer>();
    public static LinkedList<Integer> burst = new LinkedList<Integer>();
    public static LinkedList<Integer> taskQueue = new LinkedList<Integer>();
    public static void main(String[] args) throws IOException {

        /*if (args.length != 2) {
            System.err.println("java Driver FCFS schedule.txt");
            System.exit(0);
        }
*/
        List<Task> queue = new ArrayList<Task>();

        Scanner input = new Scanner(new File("src/input.txt")); //read input file and put it in 3 different linked lists.
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] cols = line.split(", ");
            task.add(cols[0]);
            priority.add(Integer.parseInt(cols[1]));
            burst.add(Integer.parseInt(cols[2]));
            queue.add(new Task(cols[0], Integer.parseInt(cols[1]), Integer.parseInt(cols[2])));
        }


        Algorithm scheduler = null;
        //String choice = args[0].toUpperCase();

        switch("FCFS") {
            case "FCFS":
                scheduler = new Algorithm.FCFS();
                break;
            case "SJF":
                scheduler = new Algorithm.SJF();
                break;
            case "PRI":
                scheduler = new Algorithm.PRI();
                break;
            case "RR":
                scheduler = new Algorithm.RR();;
                break;
            case "FAIR SHARE":
                scheduler = new Algorithm.FairShare();
                break;
            default:
                System.err.println("Invalid algorithm");
                System.exit(0);
        }

        scheduler.schedule();
    }

    public static boolean PriorityScheduling(){
        while (PRI.PRI()) { //loop until false is returned.
        }
        return false;
    }

    public static boolean SJF(){
        while (SJF.SJF()) {
        }
        return false;
    }

    public static boolean FCFS(){
        while (FCFS.FCFS()) {
        }
        return false;
    }
    public static boolean RoundRobin(){
        while (RR.RR()) {
        }
        return false;
    }

    public static boolean FairShare(){
        while (FairShare.FairShare()) {
        }
        return false;
    }

    public static LinkedList<String> getTasks() {
        return task;
    }

    public static LinkedList<Integer> getPriorities() {
        return priority;
    }

    public static LinkedList<Integer> getBursts() {
        return burst;
    }

    public static LinkedList<Integer> getTaskQueue() {return taskQueue; }


}