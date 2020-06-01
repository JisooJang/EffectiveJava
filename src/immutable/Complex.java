package immutable;

/*
Item 17 : 변경 가능성을 최소화 해라.
불변 객체는 생성된 시점의 상태를 파괴할 때까지 그대로 간직한다.
불변 객체는 근본적으로 스레드 안전하여 따로 동기화가 필요없다. 따라서 안심하고 공유할 수 있다.
불변 객체는 한번 만든 인스턴스를 최대한 재활용해야 한다.
가장 쉬운 방법은 자주 쓰이는 값들을 상수로 제공하는 것이다.

불변 객체는 맵의 키와 집합(Set)의 원소로 쓰기에 안성맞춤이다.
단점은 값이 다르면 반드시 독립된 객체로 만들어야 한다는 것이다.

클래스는 꼭 필요한 경우가 아니라면 불변이어야 한다.
단순한 값 객체는 항상 불변으로 만들자.

자바의 util.Date, awt.Point 클래스는 불변이어야 했지만 그렇지 않게 만들어진 객체이다.
String이나 BigInteger와 같은 무거운 값 객체도 불변으로 만들 수 있는지 고심해야 한다.

또한 불변으로 만들수 없는 클래스라도 변경할 수 있는 부분을 최소한으로 줄이자.
생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다.
 */
public class Complex {
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    private final double re;
    private final double im;

    // 클래스가 불변임을 보장하려면 상속을 금지시켜야 한다. -> 생성자 private
    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // 정적 팩터리 메서드 추가
    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    public double getRe() { return this.re; }
    public double getIm() { return this.im; }

    // 사칙 연상 메서드들이 자기 자신을 수정하지 않고 새로운 인스턴스를 만들어 반환한다.
    // -> 피연산자에 함수를 적용해 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍 패턴 : 함수형 프로그래밍
    public Complex plus(Complex c) {
        return new Complex(this.re + c.re, this.im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(this.re - c.re, this.im - c.im);
    }

    @Override public boolean equals(Object o) {
        if(!(o instanceof Complex)) {
            return false;
        }
        if(o == this) {
            return true;
        }
        Complex target = (Complex)o;
        return Double.compare(this.im, target.getIm()) == 0 && Double.compare(this.re, target.getRe()) == 0;
    }

    @Override public int hashCode() {
        return 31 * Double.hashCode(this.re) + Double.hashCode(this.im);
    }

    @Override public String toString() {
        return "(" + this.im + "," + this.re + ")";
    }
}
