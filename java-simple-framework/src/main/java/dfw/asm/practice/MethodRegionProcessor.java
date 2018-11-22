package dfw.asm.practice;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

public class MethodRegionProcessor {

    public static void main(String[] args) {

    }

    static class RegionProcessor extends ClassVisitor {

        public RegionProcessor() {
            super(ASM4);
        }



    }

}
