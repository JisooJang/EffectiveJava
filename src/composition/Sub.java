package composition;

import java.time.Instant;

public class Sub extends Super {
    private final Instant instant;

    Sub() {
        System.out.println("Sub 생성자 호출");
        instant = Instant.now();
    }

    @Override public void overrideMe() {
        System.out.println("Sub.overrideMe 메서드 호출");
        System.out.println(instant);
    }
}
