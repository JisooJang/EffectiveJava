package composition;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// 이 클래스는 기존클래스(Set)를 필드로 가지는 forwarding 클래스이다.
// 각 인스턴스 메서드에서 기존클래스에 대응하는 메서드를 그대로 호출하여 사용한다. (=forwarding)
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> set;
    public ForwardingSet(Set<E> set) { this.set = set; }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
