package functionalinterface;

@FunctionalInterface
public interface MyFunction {
    void myMethod();
}

// 두 개 이상의 매개변수를 갖는 함수형 인터페이스가 필요하면 아래와 같이 직접 만들어 써야함.
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
