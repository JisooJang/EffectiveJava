package functionalinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

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

        Supplier<Integer> random = () -> (int)(Math.random() * 100) + 1;
        Consumer<Integer> c = i -> System.out.print(i + ", ");
        Predicate<Integer> p = i -> i % 2 == 0;
        Function<Integer, Integer> f = i -> i / 10 * 10; // 1의 자리를 없앤다.

        List<Integer> ranList = new ArrayList<>();
        makeRandomList(random, ranList);
        System.out.println(ranList);

        printEvenNum(p, c, ranList);

        List<Integer> newList = doSomething(f, ranList);
        System.out.println(newList);

        Function<String, Integer> f2 = (str) -> Integer.parseInt(str, 16);
        Function<Integer, String> g = (i) -> Integer.toBinaryString(i);

        // Function 두 람다식의 합성
        Function<String, String> h = f2.andThen(g); // f를 먼저 적용하고 그다음 g 적용
        Function<Integer, Integer> h2 = f2.compose(g); // g를 먼저 적용하고 그다음 f 적용

        System.out.println(h.apply("FF"));  //"FF" -> 255 -> "11111111"
        System.out.println(h2.apply(2)); // 2 -> "10" -> 16

        Function<String, String> f3 = x -> x; // 항등 함수 (identity function)
        System.out.println(f3.apply("abc")); // "abc"가 그대로 출력

        Predicate<Integer> p1 = i -> i < 100;
        Predicate<Integer> q1 = i -> i < 200;
        Predicate<Integer> r = i -> i%2 == 0;
        Predicate<Integer> notP = p1.negate(); //i >= 100

        Predicate<Integer> all = notP.and(q1.or(r));
        System.out.println(all.test(150));

        String str1 = "abc";
        String str2 = "abc";

        Predicate<String> predicate = Predicate.isEqual(str1);
        System.out.println(predicate.test(str2));


    }

    static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<>(list.size());
        for(T t : list) {
            newList.add(f.apply(t));
        }
        return newList;
    }

    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        System.out.print("[");
        for(T t : list) {
            if(p.test(t)) {
                c.accept(t);
            }
        }
        System.out.println("]");
    }

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for(int i=0 ; i<10 ; i++) {
            list.add(s.get());
        }
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
