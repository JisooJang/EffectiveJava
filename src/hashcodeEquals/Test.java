package hashcodeEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

        System.out.println(e3.hashCode());
        System.out.println(e4.hashCode());

        System.out.println(e3.equals(e4));
        System.out.println(e3 == e4);

        HashSet<Employee2> hs2 = new HashSet<>();
        hs2.add(e3);

        System.out.println("Hash test");
        System.out.println(hs2.contains(e3));
        System.out.println(hs2.contains(e4));
        hs2.add(e4);
        System.out.println(hs.size());


        System.out.println("HashMap test");
        Map<Employee2, Integer> map = new HashMap<>();
        map.put(e3, 1);
        System.out.println(map.get(e4));

    }
}
