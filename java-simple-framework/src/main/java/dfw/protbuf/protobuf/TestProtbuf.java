package dfw.protbuf.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fw on 2019/6/14
 */
public class TestProtbuf {
    public static void main(String[] args) {

        PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();

        builder.setEmail("xiaoxiangzi@email.com");
        builder.setId(1);
        builder.setName("筱灬湘子");
        builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("1001").setType(PersonProbuf.Person.PhoneType.MOBILE));
        builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("1002").setType(PersonProbuf.Person.PhoneType.HOME));

        PersonProbuf.Person person = builder.build();
        byte[] buf = person.toByteArray();

        try {
            // 反序列化
            PersonProbuf.Person person2 = PersonProbuf.Person.parseFrom(buf);

            System.out.println(person2.getName() + ", " + person2.getEmail());
            List<PersonProbuf.Person.PhoneNumber> lstPhones = person2.getPhoneList();
            for (PersonProbuf.Person.PhoneNumber phoneNumber : lstPhones) {
                System.out.println(phoneNumber.getNumber());
            }

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("buf.length = " + buf.length);
        System.out.println(Arrays.toString(buf));

        System.out.println("\n -------格式化-------------");
        try {
            String print = JsonFormat.printer().print(builder);
            System.out.println("print = " + print);
            System.out.println("buf.length = " + print.getBytes().length);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
