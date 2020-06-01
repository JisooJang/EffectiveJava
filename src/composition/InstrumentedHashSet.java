package composition;

import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;
    public InstrumentedHashSet() {

    }
    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }
    @Override public boolean add(E e) {
        this.addCount++;
        return super.add(e);
    }
    // 부모 클래스인 HashSet의 addAll 메소드는 내부적으로 상속한 클래스 InstrumentedHashSet의 add 메소드를 각 원소당 한번씩 호출한다.
    @Override public boolean addAll(Collection<? extends E> c) {
        this.addCount += c.size();
        return super.addAll(c);
    }
    public int getCount() { return this.addCount; }
}