package commonmethod;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        PhoneNumber p1 = new PhoneNumber(707, 867, 5309);
        PhoneNumber p2 = new PhoneNumber(707, 867, 5309);
        PhoneNumber p3 =  new PhoneNumber(707, 867, 5309);

        PhoneNumber p4 =  new PhoneNumber(701, 867, 5309);

        System.out.println(p1.equals(p2)); // true
        System.out.println(p1.equals(p3)); // true
        System.out.println(p2.equals(p3)); // true

        System.out.println(p1.equals(p4)); // false

        Map<PhoneNumber, String> map = new HashMap<>();
        map.put(new PhoneNumber(707, 867, 1234), "jenny");
        String value = map.get(new PhoneNumber(707, 867, 1234));
        System.out.println(value); // value is "jenny"

        System.out.println(p1.toString());
        System.out.println(p4.toString());

        PhoneNumber cloneNumber = p1.clone();
        System.out.println(p1.equals(cloneNumber));
        System.out.println(p1.getClass() == cloneNumber.getClass());
        System.out.println(p1.hashCode());
        System.out.println(cloneNumber.hashCode());
        System.out.println(p1 != cloneNumber);

        String a = "abc";
        String b = "abc";

        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        Integer n1 = 123;
        Integer n2 = 123;
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n1 == n2);
        System.out.println(n1.equals(n2));
        System.out.println(n1.hashCode());
        System.out.println(n2.hashCode());

        System.out.println("Comparable Test...");
        System.out.println(p1.compareTo(p2)); // 0
        System.out.println(p1.compareTo(p3)); // 0
        System.out.println(p2.compareTo(p3)); // 0

        System.out.println(p1.compareTo(p4)); // 양수
    }
}
