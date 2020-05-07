package org.person.dfw.others;

public class Test {
    public static void main(String[] args) {
        // System.out.println("recu = " + recursiveNumber(0));
        System.out.println("recu = " + recursiveNumber(1));
        System.out.println("recu = " + recursiveNumber(2));
        System.out.println("recu = " + recursiveNumber(3));
        System.out.println("recu = " + recursiveNumber(8));
    }

    //
    private static int recursiveNumber(int a){
        // if (a <=0) {
        //     return 0;
        // }
        return a == 1 ? 1 : recursiveNumber(a - 1) + a;
    }


    static class DeadLock{

        public static void main(String[] args) {
            Object a =new Object();
            Object b =new Object();

            new Thread(() -> {
                synchronized (a) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println(" 1111111111" );
                    }
                }
            }).start();

            new Thread(() -> {
                synchronized (b) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a) {
                        System.out.println(" 2222222" );
                    }
                }
            }).start();


        }


    }
}
