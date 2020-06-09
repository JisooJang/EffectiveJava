package serilalization;

import java.io.*;
import java.util.Base64;
/*
https://woowabros.github.io/experience/2017/10/17/java-serialize.html
 */
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Member m = new Member("jisoo", "ppuagirls@naver.com", 28);
        byte[] serializedMember;

        // 직렬화
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(m);
                serializedMember = baos.toByteArray();
            }
        }

        // 역직렬화
        String base64Member = Base64.getEncoder().encodeToString(serializedMember);
        System.out.println(base64Member);

        byte[] serializedMember2 = Base64.getDecoder().decode(base64Member);
        try(ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember2)) {
            try(ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object o = ois.readObject();
                Member deserializedMember = (Member) o;
                System.out.println(deserializedMember);
            }

        }


    }
}
