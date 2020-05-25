package builderpattern;

import static builderpattern.NyPizza.Size.SMALL;
import static builderpattern.Pizza.Topping.*;

/*
계층적 빌더 사용
각 하위 클래스의 빌더가 정의한 build 메서드는 해당하는 구체 하위 클래스를 반환하도록 선언한다.
NyPizza.Builder는 NyPizza를 반환하고, Calzone.Builder는 Calzone을 반환한다.
하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌 그 하위 타입을 반환 -> 공변 반환 타이핑
이 기능을 이용하면 클라이언트가 형변환에 신경 쓰지 않고도 빌더를 사용할 수 있다.
 */
public class BuilderTest {
    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();

        System.out.println(nyPizza.toString());
        System.out.println(calzone.toString());
    }
}
