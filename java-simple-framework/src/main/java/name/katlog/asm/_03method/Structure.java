package name.katlog.asm._03method;

/**
 * Created by dell on 2018/5/5
 */
public class Structure {

    public class Bean {
        private int f;

        /**
         * ALOAD 0
         * GETFIELD pkg/Bean f I
         * IRETURN
         */
        public int getF() {
            return this.f;
        }

        /**
         * ALOAD 0
         * ILOAD 1
         * PUTFIELD pkg/Bean f I
         * RETURN
         */
        public void setF(int f) {
            this.f = f;
        }

        /**
         * 		ILOAD 1
         * 		IFLT label
         * 		ALOAD 0
         * 		ILOAD 1
         * 		PUTFIELD pkg/Bean f I
         * 		GOTO end
         * 	label:
         * 	F_SAME
         * 		NEW java/lang/IllegalArgumentException
         * 		DUP
         * 		INVOKESPECIAL java/lang/IllegalArgumentException <init> ()V
         * 		ATHROW
         * 	end:
         * 	F_SAME
         *      RETURN
         */
        public void checkAndSetF(int f) {
            if (f >= 0) {
                this.f = f;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * TRYCATCHBLOCK try catch catch java/lang/InterruptedException
     * try:
     * 		LLOAD 0
     * 		INVOKESTATIC java/lang/Thread sleep (J)V
     * 		RETURN
     * 	catch:
     * 		INVOKEVIRTUAL java/lang/InterruptedException printStackTrace ()V
     * 		RETURN
     */
    public static void sleep(long d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


