package name.katlog.jvm.item08;

public class SlotInfluenceGC {

    static class Test1 {
        public static void main(String[] args) {
            byte[] placeholder = new byte[64 * 1024 * 1024];
            System.gc();
        }
    }

    static class Test2 {
        public static void main(String[] args) {
            {
                byte[] placeholder = new byte[64 * 1024 * 1024];
            }
            System.gc();
        }
    }

    static class Test3 {
        public static void main(String[] args) {
            {
                byte[] placeholder = new byte[64 * 1024 * 1024];
            }
            int a = 0;
            System.gc();
        }
    }
}

