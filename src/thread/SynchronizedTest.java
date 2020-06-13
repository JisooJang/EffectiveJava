package thread;

public class SynchronizedTest {
    public static void main(String[] args) {
        Runnable r = new RunnableEx21();
        new Thread(r).start();
        new Thread(r).start(); // 두 쓰레드는 같은 Runnable을 공유하므로 같은 Account를 공유
    }
}

class Account {
    private int balance = 1000;
    public int getBalance() { return balance; }
    public void withdraw(int money) {
        synchronized (this) {
            if (balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupt.");
                }
                balance -= money;
            }
        }
    }
}

class RunnableEx21 implements Runnable {
    Account acc = new Account();
    public void run() {
        while(acc.getBalance() > 0) {
            int money = (int)(Math.random() * 3 + 1) * 100; // 100, 200, 300중 랜덤
            acc.withdraw(money);
            System.out.println("balance : " + acc.getBalance() + ", Thread : " + Thread.currentThread().getName());
        }
    }
}

