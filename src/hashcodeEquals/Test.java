package hashcodeEquals;

import jdk.nashorn.api.tree.BreakTree;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");

        System.out.println(a.hashCode()); // 해시코드값은 a, b, c 모두 같다. String class의 hashcode() 메서드는 재정의되어있음. (hashcode() 메서드에서 value값을 기준으로 생성)
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());

        System.out.println(a == b); // true (객체의 주소값 비교)
        System.out.println(a.equals(b)); // true(객체의 값 비교)
        System.out.println(a == c); // false
        System.out.println(b == c); // false
        System.out.println(b.equals(c)); // true

        Employee e1 = new Employee("jisoo", 28);
        Employee e2 = new Employee("jisoo", 28);

        System.out.println(e1.hashCode());
        System.out.println(e2.hashCode());

        System.out.println(e1.equals(e2));
        System.out.println(e1 == e2);

        System.out.println("Hash test");
        HashSet<Employee> hs = new HashSet<>();
        hs.add(e1);
        System.out.println(hs.contains(e1));
        System.out.println(hs.contains(e2));

        hs.add(e2);
        System.out.println(hs.size());

        Employee2 e3 = new Employee2("jisoo", 28);
        Employee2 e4 = new Employee2("jisoo", 28);

        System.out.println("Hash code test");

        System.out.println(e3.hashCode()); // e3과 e4의 해시코드는 같다. (hashcode() 메서드 재정의)
        System.out.println(e4.hashCode());

        System.out.println(e3.equals(e4)); // 동등성 비교(객체의 값 비교) true (equals() 메서드 재정의)
        System.out.println(e3 == e4);  // 동일성 비교 (객체의 주소값 비교) false

        HashSet<Employee2> hs2 = new HashSet<>();
        hs2.add(e3);

        System.out.println("Hash test");
        System.out.println(hs2.contains(e3)); // true
        System.out.println(hs2.contains(e4)); // true
        boolean result = hs2.add(e4);
        System.out.println("HashSet에 e4 원소 추가시 성공여부 : " + result);
        System.out.println(hs2.size()); // 1 (e3, e4가 같다고 판단하여 e4가 저장되지 않음.)


        System.out.println("HashMap test");
        Map<Employee2, Integer> map = new HashMap<>();
        map.put(e3, 1);
        System.out.println(map.get(e4)); // e3과 e4가 같다고 판단하여 e3을 키로 넣었던 값인 1이 출력된다.

        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Collections.sort(list, (s1, s2) -> s2.compareTo(s1));
        System.out.println(list.toString());
    }
}
