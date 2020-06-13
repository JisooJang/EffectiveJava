package thread;

public class Test {
    public static void main(String[] args) {
        //Runnable r = new ThreadTest();
        Thread t1 = new Thread(new ThreadTest()); // Runnable을 구현한 클래스는 Thread(Runnable target) 의 생성자를 이용해서 사용해야 한다.
        Thread t2 = new ThreadTest2();

        t1.setName("ThreadTest-0"); // 쓰레드 이름 지정

        // 하나의 쓰레드에 대해 start() 메서드는 한번만 호출 가능. 즉 한번 실행이 종료된 쓰레드는 다시 실행 불가.
        t1.start();
        t2.start();

        // 1) start()를 호춯하면 새로운 쓰레드를 생성하고, 쓰레드가 작업하는데 사용될 호출 스택을 생성
        // 2) 새로 생성된 호출스택에 run()이 호출되어, 쓰레드가 독립적인 공간에서 작업을 수행
        // 3) 2개의 각 쓰레드마다 start를 한번씩 호출했으므로 호출스택이 2개이므로 스케줄러가 정한 순서에 의해 번갈아 가면서 실행됨.
    }

    public static class ThreadTest implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class ThreadTest2 extends Thread {
        public void run() {
            System.out.println(getName()); // Thread-{num}
        }
    }
}
