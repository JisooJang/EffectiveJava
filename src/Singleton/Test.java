package Singleton;

import java.lang.reflect.AccessibleObject;

public class Test {
    public static void main(String[] args) {
        Elvis el = Elvis.INSTANCE;
        Atom atom = Atom.getInstance();
    }
}
