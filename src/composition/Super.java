package composition;

public class Super {
    public Super() {
        System.out.println("Super 생성자 호출");
        overrideMe(); // 부모 클래스 생성자가 재정의 가능 메서드를 호출함 -> 잘못된 예.
        // 상위 클래스의 생성자가 하위 클래스의 생성자보다 먼저 실행되므로
        // 하위클래스에서 재정의한 메서드가 하위클래스 생성자보다 먼저호출됨.
    }
    public void overrideMe() {
        System.out.println("Super.overrideMe 메서드 호출");
    }
}
