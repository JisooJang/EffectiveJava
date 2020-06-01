package composition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;
    public InstrumentedSet() {
        super(new HashSet<>());
    }
    public InstrumentedSet(Set<E> set) {
        super(set);
    }

    @Override public boolean add(E e) {
        this.addCount++;
        return super.add(e);
    }
    @Override public boolean addAll(Collection<? extends E> c) {
        this.addCount += c.size();
        return super.addAll(c);
    }
    public int getCount() { return addCount; }
}
