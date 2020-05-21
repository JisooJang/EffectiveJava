package Singleton;

public class Atom {
    private static final Atom INSTANCE = new Atom();
    private Atom() {

    }
    public static Atom getInstance() {
        return INSTANCE;
    }
}
