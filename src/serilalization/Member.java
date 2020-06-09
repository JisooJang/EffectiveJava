package serilalization;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private int age;

    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return String.format("Member{name='%s', email='%s', age='%s'}", name, email, age);
    }
}
