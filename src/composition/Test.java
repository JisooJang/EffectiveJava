package composition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("a", "aa", "aaa"));
        System.out.println(s.getCount()); // 기대한 값인 3이 아니라 6이다.

        InstrumentedSet<String> s2 = new InstrumentedSet<>();
        s2.addAll(List.of("b", "bb,", "bbb"));
        System.out.println(s2.getCount()); // 컴포지션 클래스인 InstrumentedSet을 이용하여 기대한 갑인 3을 리턴한다.

        System.out.println("======================");

        Set<Integer> s3 = new InstrumentedSet<>(new TreeSet<>());
        Set<Integer> s4 = new InstrumentedSet<>(new HashSet<>(10));

        Sub sub = new Sub();

        System.out.println("======================");
        sub.overrideMe();
    }
}
