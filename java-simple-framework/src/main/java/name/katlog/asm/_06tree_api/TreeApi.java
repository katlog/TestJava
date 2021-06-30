package name.katlog.asm._06tree_api;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;

public class TreeApi {

    /** 6.1.2 生成类 */
    @Test
    public void classGenerate() {
        ClassNode cn = new ClassNode();
        cn.version = V1_5;
        cn.access = ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE;
        cn.name = "pkg/Comparable";
        cn.superName = "java/lang/Object";
        cn.interfaces.add("pkg/Mesurable");
        cn.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,"LESS", "I", null, new Integer(-1)));
        cn.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,"EQUAL", "I", null, new Integer(0)));
        cn.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,"GREATER", "I", null, new Integer(1)));
        cn.methods.add(new MethodNode(ACC_PUBLIC + ACC_ABSTRACT,"compareTo", "(Ljava/lang/Object;)I", null, null));

        ClassWriter cw = new ClassWriter(Opcodes.ASM5);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        cn.accept(tcv);

        byte[] bytes = cw.toByteArray();
    }

    /** 6.1.3 添加field  移除method*/
    @Test
    public void addField() {

        class ClassTransformer {
            protected ClassTransformer ct;
            public ClassTransformer(ClassTransformer ct) {
                this.ct = ct;
            }
            public void transform(ClassNode cn) {
                if (ct != null) {
                    ct.transform(cn);
                }
            }
        }


        class RemoveMethodTransformer extends ClassTransformer {
            private String methodName;
            private String methodDesc;
            public RemoveMethodTransformer(ClassTransformer ct,String methodName, String methodDesc) {
                super(ct);
                this.methodName = methodName;
                this.methodDesc = methodDesc;
            }
            @Override
            public void transform(ClassNode cn) {
                Iterator<MethodNode> i = cn.methods.iterator();
                while (i.hasNext()) {
                    MethodNode mn = i.next();
                    if (methodName.equals(mn.name) && methodDesc.equals(mn.desc)) {
                        i.remove();
                    }
                }
                super.transform(cn);
            }
        }

        class AddFieldTransformer extends ClassTransformer {
            private int fieldAccess;
            private String fieldName;
            private String fieldDesc;
            public AddFieldTransformer(ClassTransformer ct, int fieldAccess,String fieldName, String fieldDesc) {
                super(ct);
                this.fieldAccess = fieldAccess;
                this.fieldName = fieldName;
                this.fieldDesc = fieldDesc;
            }
            @Override
            public void transform(ClassNode cn) {
                boolean isPresent = false;
                List<FieldNode> fieldNodes = cn.fields;
                for (FieldNode fn : fieldNodes) {
                    if (fieldName.equals(fn.name)) {
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    cn.fields.add(new FieldNode(fieldAccess, fieldName, fieldDesc,null, null));
                }
                super.transform(cn);
            }
        }
    }

    @Test
    /** 6.2.1 组件合成：由一个类的字节数组表示来构造一个 ClassNode，或由 ClassNode 构造这个字节数组 */
    public void componentSynthesis() throws IOException {

        // ClassNode 扩展了ClassVisitor

        // 要由字节数组构建 ClassNode，可将它与 ClassReader 合在一起，使 ClassReader生成的事件可供 ClassNode 组件使用
        ClassNode cn = new ClassNode();
        ClassReader cr = new ClassReader(String.class.getCanonicalName());
        cr.accept(cn, 0);

        for (FieldNode field : (List<FieldNode>)cn.fields) {
            System.out.println("field = " + field.name);
        }
    }






}
