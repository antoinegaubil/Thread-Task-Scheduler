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
        PriorityScheduling();


    }
    public static void PriorityScheduling(){
        System.out.println("\nPRIORITY SCHEDULING\n");
        while (Schedulers.PriorityScheduling()) {
        }
    }

    public static void readInput() throws FileNotFoundException {

        Scanner input = new Scanner(new File("src/input.txt"));
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


}