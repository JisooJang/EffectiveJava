package thread;

public class MultiThreadTest {
    static long startTime = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadTest());
        t1.start();
        startTime = System.currentTimeMillis();

        for(int i=0 ; i<300 ; i++) {
            System.out.printf("%s", new String("|"));
        }
        System.out.println("소요시간 : " + (System.currentTimeMillis() - MultiThreadTest.startTime));
    }
}

class ThreadTest implements Runnable {
    public void run() {
        for(int i=0 ; i<300 ; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.println("소요시간2 : " + (System.currentTimeMillis() - MultiThreadTest.startTime));
    }
}
