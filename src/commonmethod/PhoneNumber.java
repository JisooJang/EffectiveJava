package commonmethod;

import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    private final int areaCode;
    private final int prefix;
    private final int lineNum;
    private int hashcode = 0;
    private static final Comparator<PhoneNumber> COMPARATOR =
            comparingInt((PhoneNumber p) -> p.areaCode)
            .thenComparingInt(p -> p.prefix)
            .thenComparingInt(p -> p.lineNum);

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
        // 인스턴스ㄵㅂ가 생성자를 통해 만들어질 때 해시코드를 계산해둔다.
        this.hashcode = hashCode();
    }

    @Override
    public int hashCode() {
        //해시 코드값이 캐싱해 둔 결과가 있다면 캐싱값을 가져온다.
        int result = hashcode;
        if(result == 0) {
            result = Integer.hashCode(areaCode);
            result = 31 * result + Integer.hashCode(prefix);
            result = 31 * result + Integer.hashCode(lineNum);
        }
        return result;
    }

    @Override
    public boolean equals(Object phoneNumber) {
        if(phoneNumber instanceof PhoneNumber) {
            return this.hashCode() == phoneNumber.hashCode();
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%3d-%3d-%4d", areaCode, prefix, lineNum);
    }

    // 재정의한 메서드의 반환 타입은 상위 클래스의 메서드가 반환하는 타입(Object)의 하위 타입(PhoneNumber)일 수 있다.
    @Override public PhoneNumber clone() throws CloneNotSupportedException {
        try {
            return (PhoneNumber) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // Comparable<T> 인터페이스의 구현 메소드.
    // 본 객체가 인자 객체보다 작으면 음수, 같으면 0, 크면 양수를 return한다.
//    @Override public int compareTo(PhoneNumber p) {
//        int result = Integer.compare(this.areaCode, p.areaCode);
//        if(result == 0) {
//            result = Integer.compare(this.prefix, p.prefix);
//            if(result == 0) {
//                result = Integer.compare(this.lineNum, p.lineNum);
//            }
//        }
//        return result;
//    }

    // Comparable version 2. 자바 8에서는 비교자 생성 메서드와 팀을 꾸려 메서드 연쇄방식으로 비교자(Comparator) 생성가능.
    @Override public int compareTo(PhoneNumber p) {
        return COMPARATOR.compare(this, p);
    }
}
