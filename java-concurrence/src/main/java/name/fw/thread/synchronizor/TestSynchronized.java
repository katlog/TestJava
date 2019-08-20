package name.fw.thread.synchronizor;

/**
 * Created by fw on 2019/7/31
 */
public class TestSynchronized {

    static class Woker implements Runnable {
        private int index;
        public Woker(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            synchronized (Woker.class) {
                while (true) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running = " + index);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Woker(i)).start();
        }
    }

}
