package name.katlog.asm._01introduction;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;


/**产生自定义类对应的class字节码*/
public class ASMGenerateSimpleClass3 {


    /**
     *package pkg;
     *public interface Comparable extends Mesurable {
     *      int LESS = -1;
     *      int EQUAL = 0;
     *      int GREATER = 1;
     *      int compareTo(Object o);
     * }
     *
     * */

    public static void main(String[] args) throws IOException {

        // 生成一个类只需要ClassWriter组件即可
        ClassWriter cw = new ClassWriter(0);

        // 通过visit方法确定类的头部信息
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/asm5/Comparable", null, "java/lang/Object", new String[]{"com/asm5/Mesurable"});

        // 定义类的属性
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();

        // 定义类的方法
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I",
                null, null).visitEnd();

        // 使cw类已经完成
        cw.visitEnd();

        // 将cw转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        File file = new File("/Users/fan/Comparable.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();

        // 动态加载生成的class字节码
        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("com.asm5.Comparable", data);
        System.out.println(c.getName());
        Field f[] = c.getFields();
        for (int i = 0; i < f.length; i++)
            System.out.println(f[i].toString());

        "".getBytes();
    }
}

