package immutable;

public class Test {
    public static void main(String[] args) {
        Complex a = Complex.valueOf(1, 2);
        Complex b = Complex.valueOf(1, 2);

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));
    }
}
