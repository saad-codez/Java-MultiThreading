public class WaitAndNotifyMethods {
    static void main() {

        Person person = new Person();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                person.withdraw(15000); //t1 tries to withdraw 15k
            }
        });
        t1.start();
        new Thread(new Runnable() {
            public void run() {
                person.deposit(10000);
            }
        }).start();

    }
}

class Person {
    int amount = 10000; // a person has 10k in his shared bank account

    // withdraws 15k
    synchronized public void withdraw(int amount) {
        if (amount > this.amount) {
            System.out.println("AMOUNT INSUFFICIENT! PLEASE WAIT!");
            System.out.println("WAITING...");
            try {
                wait(); // this pushes this thread in waiting list
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        this.amount -= amount;
        System.out.println("Withdrawal Successful!");
        System.out.println("Balance: " + this.amount);

    }

    synchronized public void deposit(int amount) {
        this.amount += amount;
        System.out.println("Final Amount post Deposit: " + this.amount);
        notify(); // notifies the above thread after the deposit
        System.out.println("Deposit Successful!");

    }


}
