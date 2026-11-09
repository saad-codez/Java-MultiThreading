class TotalEarning extends Thread {
    int total;
    @Override
    public void run(){
        synchronized (this) { // synchronized block helps this thread to perform the task completely
            for (int i = 1; i <= 10; i++) {
                total = total + 100;
            }
            this.notify(); // after this thread performs the task, this will notify the main thread to then execute.
        }
    }
}

public class InterThreadCommunication {

    static void main() throws InterruptedException {
        TotalEarning totalEarning = new TotalEarning();

        totalEarning.start();
//        System.out.println("Total Earnings: $"+totalEarning.total); // this shows 0 or 1000, because the main thread has been executed before the TotalEarning class thread.

        synchronized (totalEarning) { // here we are passing totalEarning object in the thread so that the main thread waits until this is executed.
            totalEarning.wait();
            System.out.println("Total Earnings: $"+totalEarning.total);
        }
    }


}
