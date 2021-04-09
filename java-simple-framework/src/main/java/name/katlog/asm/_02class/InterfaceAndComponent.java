package name.katlog.asm._02class;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 * 接口和组件
 * Created by fw on 2021/4/9
 */
public class InterfaceAndComponent {

    /**
     * 示例1：分析类
     *
     * 子类用来打印class字节码内容，这里以java.lang.String 为例
     */
    public static class ClassPrinter extends ClassVisitor {
        public ClassPrinter() {
            super(Opcodes.ASM5);
        }

        public void visit(int version, int access, String name, String signature,
                          String superName, String[] interfaces) {
            System.out.println(name + " extends " + superName + " {");
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            System.out.println("filed:\t\t    " + desc + " " + name);
            return null;
        }

        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            System.out.println("method:\t\t " + name + desc);
            return null;
        }

        public void visitEnd() {
            System.out.println("}");
        }

        public static void main(String args[]) throws IOException {
            // ClassReader作为字节码生产者，ClassPrinter作为字节码消费者
            ClassPrinter cp = new ClassPrinter();
            // ClassReader产生的事件由ClassPrinter 使用
            ClassReader cr = new ClassReader("java.lang.String");
            cr.accept(cp, 0);
        }
    }

    /**
     * 示例2：生成类。如下
     *
     * package pkg;
     * public interface Comparable extends Mesurable {
     *      int LESS = -1;
     *      int EQUAL = 0;
     *      int GREATER = 1;
     *      int compareTo(Object o);
     * }
     */
    public static class GenerateClass {

        public static void main(String[] args) {

            ClassWriter cw = new ClassWriter(0);
            cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
            cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
            cw.visitEnd();
            byte[] b = cw.toByteArray();

            MyClassLoader myClassLoader = new MyClassLoader();
            Class c = myClassLoader.findClass("pkg.Comparable", b);
            System.out.println("c.getSimpleName() = " + c.getSimpleName());
        }

        static class MyClassLoader extends ClassLoader {
            public Class findClass(String name, byte[] b) {
                return defineClass(name, b, 0, b.length);
            }
        }

        /**
         * 另一种加载已生成类的方法可能更清晰一些，那就是定义一个 ClassLoader 子类，
         *  它的findClass 方法被重写，以在运行过程中生成所请求的类
         */
        class StubClassLoader extends ClassLoader {
            @Override
            protected Class findClass(String name)throws ClassNotFoundException {
                if (name.endsWith("_Stub")) {
                    ClassWriter cw = new ClassWriter(0);
                    // ... 生成class的过程
                    byte[] b = cw.toByteArray();
                    return defineClass(name, b, 0, b.length);
                }
                return super.findClass(name);
            }
        }

    }


    /**
     * 示例3：类转换
     *
     *      第一步是将 ClassReader 产生的事件转给 ClassWriter。
     *      第二步是在类读取器和类写入器之间引入 ClassVisitor
     *
     *      <code>
     *          	byte[] b1 = new byte[]{};
     * 				ClassReader cr = new ClassReader(b1);
     * 				// cv 将所有事件转发给 cw
     * 				ClassWriter cw = new ClassWriter(0);
     * 				ClassVisitor cv = new ChangeXxxAdapter(cw);
     * 				cr.accept(cv, 0);
     * 				byte[] b2 = cw.toByteArray();
     *      </code>
     */
    public static class ClassTransform{

        /**
         * 更改版本号
         */
        public static class ChangeVersionAdapter extends ClassVisitor {
            public ChangeVersionAdapter(ClassVisitor cv) {
                super(ASM4, cv);
            }
            @Override
            public void visit(int version, int access, String name,String signature, String superName, String[] interfaces) {
                cv.visit(V1_5, access, name, signature, superName, interfaces);
            }
        }

        public static void main(String[] args) throws IOException {
            ClassReader cr = new ClassReader("java.lang.String");
            // cv 将所有事件转发给 cw
            ClassWriter cw = new ClassWriter(0);
            ClassVisitor cv = new ChangeVersionAdapter(cw);
            cr.accept(cv, 0);
            byte[] b2 = cw.toByteArray();

        }
    }

    /**
     * 示例4：移除类成员
     *
     *  原理：在转发的方法调用中使用经过修改的参数外，还可选择根本不转发该调用，效果就是相应的类元素被移除。
     */
    public static class RemoveClassMember{


        /**
         * 移除debug所用的类信息
         *   移除了有关外部类及内部类的信息，还删除了一个源文件的名字，
         *   也就是由其编译这个类的源文件（所得到的类仍然具有全部功能，
         *   因为删除的这些元素仅用于调试目的）。这一移除操作是通过在适当的访问方法中
         *   不转发任何内容而实现的。
         *
         *   使用这种策略对字段和方法无效
         */
        public class RemoveDebugAdapter extends ClassVisitor {
            public RemoveDebugAdapter(ClassVisitor cv) {
                super(ASM4, cv);
            }
            @Override
            public void visitSource(String source, String debug) {
            }
            @Override
            public void visitOuterClass(String owner, String name, String desc) {
            }
            @Override
            public void visitInnerClass(String name, String outerName, String innerName, int access) {
            }
        }

        /**
         * 移除方法
         */
        public class RemoveMethodAdapter extends ClassVisitor {
            private String mName;
            private String mDesc;

            public RemoveMethodAdapter(ClassVisitor cv, String mName, String mDesc) {
                super(ASM4, cv);
                this.mName = mName;
                this.mDesc = mDesc;
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals(mName) && desc.equals(mDesc)) {
                    // 不要委托至下一个访问器 -> 这样将移除该方法
                    return null;
                }
                return cv.visitMethod(access, name, desc, signature, exceptions);
            }
        }

    }

    /**
     * 示例5：增加类成员
     *
     *      原理：转发时，发出的调用数多于收到的调用
     */
    public static class AddClassMember{

        /**
         * 向类中添加一个字段，除非这个字段已经存在
         */
        public class AddFieldAdapter extends ClassVisitor {
            private int fAcc;
            private String fName;
            private String fDesc;
            private boolean isFieldPresent;
            public AddFieldAdapter(ClassVisitor cv, int fAcc, String fName,String fDesc) {
                super(ASM4, cv);
                this.fAcc = fAcc;
                this.fName = fName;
                this.fDesc = fDesc;
            }
            @Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                if (name.equals(fName)) {
                    isFieldPresent = true;
                }
                return cv.visitField(access, name, desc, signature, value);
            }
            @Override
            public void visitEnd() {
                if (!isFieldPresent) {
                    FieldVisitor fv = cv.visitField(fAcc, fName, fDesc, null, null);
                    // 注意 NPE 检测：因为一个类访问器可以在 visitField 中返回 null
                    if (fv != null) {
                        fv.visitEnd();
                    }
                }
                cv.visitEnd();
            }
        }
    }


}
