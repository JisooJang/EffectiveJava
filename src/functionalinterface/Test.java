package functionalinterface;

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
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
