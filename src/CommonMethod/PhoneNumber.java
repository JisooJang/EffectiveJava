package CommonMethod;

public class PhoneNumber {
    private final int areaCode;
    private final int prefix;
    private final int lineNum;
    private int hashcode = 0;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
        // 인스턴스ㄵㅂ가 생성자를 통해 만들어질 때 해시코드를 계산해둔다.
        this.hashcode = hashCode();
    }

    @Override
    public int hashCode() {
        //해시 코드값이 캐싱해 둔 결과가 있다면 캐싱값을 가져온다.
        int result = hashcode;
        if(result == 0) {
            result = Integer.hashCode(areaCode);
            result = 31 * result + Integer.hashCode(prefix);
            result = 31 * result + Integer.hashCode(lineNum);
        }
        return result;
    }

    @Override
    public boolean equals(Object phoneNumber) {
        if(phoneNumber instanceof PhoneNumber) {
            return this.hashCode() == phoneNumber.hashCode();
        }
        return true;
    }
}
