package CommonMethod;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
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
    }
}
