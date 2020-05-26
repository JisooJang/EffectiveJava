package observer;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
https://www.youtube.com/watch?v=8fenTR3KOJo
 */
public class ObserverTest {
    // Iterable(pull) <---> Observable(push) : (duality)
    public static class IntObservable extends Observable implements Runnable {
        @Override
        public void run() {
            for(int i=0 ; i<10 ; i++) {
                setChanged();
                notifyObservers(Thread.currentThread().getName() + " send data to observers : " + (i+1));
            }
        }
    }
    public static void main(String[] args) {
        Iterable<Integer> iter = () ->
            new Iterator<Integer>() {
                int i = 0;
                final static int MAX = 10;
                public boolean hasNext() {
                    return i < MAX;
                }

                public Integer next() {
                    return ++i;
                }
            };

        for(Integer element : iter) {
            System.out.println(element);
        }

        for(Iterator<Integer> it = iter.iterator() ; it.hasNext() ;) {
            System.out.println(it.next());
        }

        Observer ob = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        };

        IntObservable observable = new IntObservable();
        observable.addObserver(ob);

        // 메인쓰레드 이외의 별도의 단일 쓰레드에 Observable task를 할당하여 실행
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(observable);

        System.out.println(Thread.currentThread().getName() + " EXIT");

    }
}
