package dfw.asm._06tree_api;

import org.junit.Test;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;

public class TreeApi {

    @Test
    /** 生成类 */
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

    }

    @Test
    /** 添加field  移除method*/
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
    /** 组件合成 */
    public void componentSynthesis() {

    }






}
