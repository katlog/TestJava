package dfw.asm.practice;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

public class MethodRegionProcessor {

    public static void main(String[] args) throws IOException {

        // ClassReader cr = new ClassReader("java.lang.String");
        ClassReader cr = new ClassReader("java.util.List");


        cr.accept(new RegionProcessor(), ClassReader.SKIP_DEBUG);
    }

    static class RegionProcessor extends ClassVisitor {

        RegionProcessor() {
            super(ASM4);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            System.out.println("name = " + name);
            System.out.println("\tdesc = " + desc);
            System.out.println("\tsignature = " + signature);
            return new MethodVisitor(api) {

            };
        }
    }

}
