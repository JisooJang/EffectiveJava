package functionalinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);

        Predicate<String> isEmptyStr = s -> s.length() == 0;
        String s = "";
        if(isEmptyStr.test(s)) System.out.println("This is an empty String.");

        // 매개변수가 2개인 함수형 인터페이스는 앞에 접두사 "Bi"가 붙는다.
        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println(s1 + s2);
        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.equals(s2);
        BiFunction<String, String, Integer> biFunction = (s1, s2) -> s1.length() + s2.length();

        // Collection Framework의 함수형 인터페이스
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++) {
            list.add(i+1);
        }

        list.forEach(i -> System.out.print(i + " ")); // 각 요소 출력 (모든 요소에 action을 수행)
        System.out.println();

        list.replaceAll(i -> i *= 10); // 각 요소를 변환(10을 곱함)하여 대체
        System.out.println(list.toString());

        list.removeIf(x -> x % 2 == 0 || x % 3 == 0); // list에서 조건(2 또는 3의 배수)에 맞는 요소들을 제거한다.
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        map.forEach((k,v) -> System.out.println(k + ":" + v));

    }
}

class Outer {
    int val = 10;

    class Inner {
        int val = 20;

        void method(int i) { // void method(final int i)
            int val = 30; // final int val = 30;
            //i = 10; // error. 상수의 값을 변경할 수 없읍!
            // i와 val값은 람다식 내에서 참조하고 있는 지역변수이므로 final(상수)로 간주된다.
            // 따라서 람다식 내에서든 외부에서든 이 변수들의 값 변경이 불가능하다.

            MyFunction f = () -> {
                System.out.println("i:" + i);
                System.out.println("val:" + val);
                System.out.println("this.val:" + ++this.val);
                System.out.println("Outer.this.val:" + ++Outer.this.val);
            };

            f.myMethod();
        }
    }
}
