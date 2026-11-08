import java.util.Scanner;

class Test1 extends Thread {
    Scanner scanner = new Scanner(System.in);
    //Overriding the predefined method of Runnable interface which is implemented by Thread class
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Enter a number for Test1 class: ");
        int a = scanner.nextInt();
        System.out.println("The number is: "+a);
    }
}

class Test2 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Run method in Test2 class is working");

        }
        System.out.println(Thread.currentThread().getName());
    }
}


public class TreadAndRunnable {
    static void main() {

        Test1 test1 = new Test1(); // thread 0
     //   Thread t1 = new Thread(new Test1(), "Thread 1"); // name of Test1 class thread is Thread 1
        Thread t2 = new Thread(new Test2(), "Thread 2"); // name of Test2 class thread is Thread 2

        // Both the threads will be executed.

        test1.start();

        t2.start();

        System.out.println(Thread.currentThread().getName()); // returns main, because this is main method

    }
}
