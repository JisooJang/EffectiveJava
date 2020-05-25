package freeReference;

import java.util.Arrays;
import java.util.EmptyStackException;

public class CustomStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /*
    스택이 커졌다가 줄어들었을 때, 스택에서 꺼내진 객체들을 아래의 null처리를 해주지 않으면 가비디 컬렉터가 회수하지 않는다.
    이 스ㅐㄱ이 그 객체들의 다 쓴 참조를 여전히 가지고 있기 때문이다.
    elements 배열은 여전히 "활성 영역" 밖의 참조들을 가지고 있음. = 인덱스가 size보다 같거나 큰 참조들
     */
    public Object pop() {
        if(size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제. 이렇게 해야 가비지 컬렉터의 회수 대상이 된다.
        return result;
    }

    public void ensureCapacity() {
        if(elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
