import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    public static LinkedList<String> task = new LinkedList<String>();
    public static LinkedList<Integer> priority = new LinkedList<Integer>();
    public static LinkedList<Integer> burst = new LinkedList<Integer>();


    public static void main(String[] args) throws FileNotFoundException {
        readInput();
        //PriorityScheduling();
        //SJF();
        //RoundRobin();
        //FCFS();
        FairShare();


    }
    public static void PriorityScheduling(){
        System.out.println("\nPRIORITY SCHEDULING\n");
        while (Priority.PriorityScheduling()) { //loop until false is returned.
        }
    }

    public static void SJF(){
        System.out.println("\nSJF SCHEDULING\n");
        while (SJF.SJF()) {
        }
    }
    
    public static void FCFS(){
         System.out.println("\nFCFS SCHEDULING\n");
        while (FCFS.FCFS()) {
        }
    }
    public static void RoundRobin(){
        System.out.println("\nRound Robin\n");
        while (RR.RoundRobin()) {
        }
    }

    public static void FairShare(){
        System.out.println("\nFair Share\n");
        while (FairShare.FairShare()) {
        }
    }


    public static void readInput() throws FileNotFoundException {

        Scanner input = new Scanner(new File("src/input.txt")); //read input file and put it in 3 different linked lists.
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] cols = line.split(", ");
            task.add(cols[0]);
            priority.add(Integer.parseInt(cols[1]));
            burst.add(Integer.parseInt(cols[2]));
        }
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

    public static void printLists() {
        System.out.println("Tasks: " + task);
        System.out.println("Priority: " + priority);
        System.out.println("Burst: " + burst);
    }


}
