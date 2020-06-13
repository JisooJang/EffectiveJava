package thread;

public class MultiThreadTest {
    static long startTime = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadTest());
        Thread t2 = new Thread(new ThreadTest2());

        t1.start();
        t2.start();
        startTime = System.currentTimeMillis();
        try {
            t1.join(); // main 쓰레드가 t1의 작업이 끝날때까지 기다림
            t2.join(); // main 쓰레드가 t2의 작업이 끝날때까지 기다림
        } catch(InterruptedException e) {}

    }
}

class ThreadTest implements Runnable {
    public void run() {
        for(int i=0 ; i<300 ; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.println("소요시간1 : " + (System.currentTimeMillis() - MultiThreadTest.startTime));
    }
}

class ThreadTest2 implements Runnable {
    public void run() {
        for(int i=0 ; i<300 ; i++) {
            System.out.printf("%s", new String("|"));
        }
        System.out.println("소요시간2 : " + (System.currentTimeMillis() - MultiThreadTest.startTime));
    }
}
