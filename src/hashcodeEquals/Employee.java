package hashcodeEquals;

public class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // hashcode() 메서드 재정의 없이 equals 메서드만 정의했을때 발생하는 문제점 테스트
    @Override
    public boolean equals(Object obj) {
        if(this == obj) { // 객체 주소값이 같으면 자기자신임
            return true;
        } else if(!(obj instanceof Employee)) {
            return false;
        }
        Employee emp = (Employee) obj;
        return this.name.equals(emp.getName()) && this.age == emp.getAge();
    }
}
