public class CPU  {
    static int time = 1000;
    //Clock class for keeping track of time
    public void run() {
        while (true) {

            try {
                Thread.sleep(10L);
                time+=10;
            } catch (InterruptedException e) {
                System.out.println("Clock Sleep Exception");
            }

        }


    }

public static void run(Task task, int time){

  System.out.println("Will run" +task );
}

}
