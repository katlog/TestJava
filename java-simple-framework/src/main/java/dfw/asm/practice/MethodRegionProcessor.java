package dfw.asm.practice;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

public class MethodRegionProcessor {

    public static void main(String[] args) throws IOException {

        ClassReader cr = new ClassReader("java.lang.String");


    }

    static class RegionProcessor extends ClassVisitor {

        public RegionProcessor() {
            super(ASM4);
        }



    }

}
