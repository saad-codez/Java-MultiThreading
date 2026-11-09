// Class demonstrating synchronized methods in Java threads
public class Synchronization {
    // main method - entry point of the program
    static void main() throws Exception {

        // Create a shared object both threads will use
        Sync1 sync1 = new Sync1();

        // Create first thread using an anonymous Runnable
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Loop 100 times, incrementing shared count via counting()
                for (int i = 1; i <= 100; i++) {
                    sync1.counting();
                }
            }
        });

        // Create second thread (anonymous) and start it immediately
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Same as t1: increment shared count 100 times
                for (int i = 1; i <= 100; i++) {
                    sync1.counting();
                }
            }
        }).start();

        // Start first thread
        t1.start();
        // Wait for t1 to finish before printing result (NOTE: does NOT wait for the second thread!)
        t1.join();

        // Print final counter value. Expected 200, but may be less because
        // the second thread is not joined and might still be running.
        System.out.println("Counter is: " + sync1.count);
    }
}

// Shared resource class
class Sync1 {

    // Shared counter, incremented by both threads
    int count = 0;

    // synchronized ensures only one thread executes this method at a time,
    // preventing race conditions on count++
    // Method 1- by using synchronized method
    public synchronized void counting() {
        count++; // not atomic by itself, but safe here due to 'synchronized'
    }

//    // Method 2 by using synchronized block
//    public void counting1() {
//     System.out.println("HI");
//        synchronized (this) { //code after this block is synchronized
//            count++;
//        }// not atomic by itself, but safe here due to 'synchronized'
//    }

}
