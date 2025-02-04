import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {

        final int chunkSize = 10000;
        final int maxNumber = 1000000;


        AtomicLong totalSum = new AtomicLong(0);

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i<=maxNumber; i+=chunkSize){
            int chunkStart = i;
            int  chunkEnd = Math.min(i+chunkSize-1, maxNumber);

            Thread thread = new Thread( () ->{
                long sum = 0;
                for (int j = chunkStart; j<=chunkEnd; j++){

                    sum +=j;

                }
                System.out.println("Sum of chunk [" + chunkStart + " - " + chunkEnd + "] is: " + sum);

                totalSum.addAndGet(sum);
            }

            );
            threads.add(thread)
            ;
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the total sum
        System.out.println("Total sum of numbers from 1 to " + maxNumber + " is: " + totalSum.get());

    }
}