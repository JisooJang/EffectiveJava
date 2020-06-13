package thread;

public class JoinTest {
    public static void main(String[] args) {
        GCThread t = new GCThread();
        Thread gc = new Thread(t);
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;
        for(int i=0 ; i<20 ; i++) {
            requiredMemory = (int) (Math.random() * 10) * 20;
            if(t.freeMemory() < requiredMemory || t.freeMemory() < t.totalMemory() * 0.4) {
                gc.interrupt(); // 잠자고있는 gc 쓰레드를 깨운다.
                try {
                    gc.join(100);
                } catch(InterruptedException e) { }
            }

            t.usedMemory += requiredMemory;
            System.out.println("usedMemory: " + t.usedMemory);
        }

    }
}

class GCThread implements Runnable {
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;
    public void run() {
        while(true) {
            try {
                Thread.sleep(10 * 1000); // 10초 기다림
            } catch(InterruptedException e) {
                System.out.println("Awaken by Interrupt() method.");
            }

            gc();
            System.out.println("Garbage Collected. Free Memory :" + freeMemory());
        }
    }
    public void gc() {
        usedMemory -= 300;
        if(usedMemory < 0) usedMemory = 0;
    }
    public int totalMemory() { return MAX_MEMORY; }
    public int freeMemory() { return MAX_MEMORY - usedMemory; }
}
