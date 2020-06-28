
/**  
 * @Title: MinorGC.java
 * @Package: org.dfw.jvm.garbagecollect
 * @author: katlog
 * @date: 2017年4月28日 上午9:53:03
 * @version: V1.0  
 */ 
package name.katlog.jvm.item03;

/**
 * @moudle: MinorGC 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年4月28日 上午9:53:03
 *
 */
public class MinorGC {
    private static final int _1MB = 1024 * 1024;

    static class MinorGC1 {

        /**	清单3-5　新生代Minor GC
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
         */
        public static void testAllocation() {
            byte[] allocation1, allocation2, allocation3, allocation4;
            allocation1 = new byte[2 * _1MB];
            allocation2 = new byte[2 * _1MB];
            allocation3 = new byte[2 * _1MB];
            allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
        }

        public static void main(String[] args) {
            testAllocation();
        }
    }

    static class MinorGC2 {

        /**清单3-6　大对象直接进入老年代
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
         */
        public static void testPretenureSizeThreshold() {
            byte[] allocation;
            allocation = new byte[4 * _1MB];  //直接分配在老年代中
        }

        public static void main(String[] args) {
            testPretenureSizeThreshold();
        }
    }

    static class MinorGC3 {

        /**
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
         */
        @SuppressWarnings("unused")
        public static void testTenuringThreshold() {
            byte[] allocation1, allocation2, allocation3;
            allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
            allocation2 = new byte[4 * _1MB];
            allocation3 = new byte[4 * _1MB];
            allocation3 = null;
            allocation3 = new byte[4 * _1MB];
        }

        public static void main(String[] args) {
            testTenuringThreshold();
        }

    }
    static class MinorGC4 {
        /**
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
         */
        @SuppressWarnings("unused")
        public static void testTenuringThreshold2() {
            byte[] allocation1, allocation2, allocation3, allocation4;
            allocation1 = new byte[_1MB / 4];   // allocation1+allocation2大于survivo空间一半
            allocation2 = new byte[_1MB / 4];
            allocation3 = new byte[4 * _1MB];
            allocation4 = new byte[4 * _1MB];
            allocation4 = null;
            allocation4 = new byte[4 * _1MB];
        }

        public static void main(String[] args) {
            testTenuringThreshold2();
        }
    }

    static class MinorGC5{

        /**
         * VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
         */
        @SuppressWarnings("unused")
        public static void testHandlePromotion() {
            byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
            allocation1 = new byte[2 * _1MB];
            allocation2 = new byte[2 * _1MB];
            allocation3 = new byte[2 * _1MB];
            allocation1 = null;
            allocation4 = new byte[2 * _1MB];
            allocation5 = new byte[2 * _1MB];
            allocation6 = new byte[2 * _1MB];
            allocation4 = null;
            allocation5 = null;
            allocation6 = null;
            allocation7 = new byte[2 * _1MB];
        }

        public static void main(String[] args) {
            testHandlePromotion();
        }
    }


}
