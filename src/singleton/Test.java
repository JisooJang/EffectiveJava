package singleton;

public class Test {
    public static void main(String[] args) {
        Elvis el = Elvis.INSTANCE;
        Atom atom = Atom.getInstance();
    }
}
