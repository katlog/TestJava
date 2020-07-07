package name.katlog.jvm.item03;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by fw on 2020/7/6
 */
public class Reference {

    /**
     * 在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行第二次回收。
     * 如果这次回收还没有足够的内存，才会抛出内存溢出异常
     * */
    public static class SoftReferenceDemo {
        /**
         * 运行参数 -Xmx200m -XX:+PrintGC
         * */
        public static void main(String[] args) throws InterruptedException {
            //100M的缓存数据
            byte[] cacheData = new byte[100 * 1024 * 1024];
            //将缓存数据用软引用持有
            SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
            //将缓存数据的强引用去除
            cacheData = null;
            System.out.println("第一次GC前" + cacheData);
            System.out.println("第一次GC前" + cacheRef.get());
            //进行一次GC后查看对象的回收情况
            System.gc();
            //等待GC
            Thread.sleep(500);
            System.out.println("第一次GC后" + cacheData);
            System.out.println("第一次GC后" + cacheRef.get());

            //在分配一个120M的对象，看看缓存对象的回收情况
            byte[] newData = new byte[120 * 1024 * 1024];
            System.out.println("分配后" + cacheData);
            System.out.println("分配后" + cacheRef.get());
        }
    }


    /**
     * 被弱引用关联的对象，在垃圾回收时，如果这个对象只被弱引用关联（没有任何强引用关联他），那么这个对象就会被回收。
     * */
    public static class WeakReferenceDemo {

        public static void main(String[] args) throws InterruptedException {
            //100M的缓存数据
            byte[] cacheData = new byte[100 * 1024 * 1024];
            //将缓存数据用软引用持有
            WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
            System.out.println("第一次GC前" + cacheData);
            System.out.println("第一次GC前" + cacheRef.get());
            //进行一次GC后查看对象的回收情况
            System.gc();
            //等待GC
            Thread.sleep(500);
            System.out.println("第一次GC后" + cacheData);
            System.out.println("第一次GC后" + cacheRef.get());

            //将缓存数据的强引用去除
            cacheData = null;
            System.gc();
            //等待GC
            Thread.sleep(500);
            System.out.println("第二次GC后" + cacheData);
            System.out.println("第二次GC后" + cacheRef.get());
        }
    }



    /**
     * 弱引用回收测试
     *      当有强引用指向value内存区域时，即使进行gc，弱引用也不会被释放，对象不回被回收。
     *      当无强引用指向value内存区域是，此时进行gc，弱引用会被释放，对象将会执行回收流程。
     */
    public  static class WeakReferenceDemo2 {

        public static WeakReference<String> weakReference1;
        public static WeakReference<String> weakReference2;

        public static void main(String[] args) {

            test1();
            //可以输出hello值，此时两个弱引用扔持有对象，而且未进行gc
            System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

            //此时已无强一用执行"value"所在内存区域，gc时会回收弱引用
            System.gc();

            //此时输出都为nuill
            System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

        }

        public static void test1() {
            String hello = new String("value");

            weakReference1 = new WeakReference<>(hello);

            System.gc();
            //此时gc不会回收弱引用，因为字符串"value"仍然被hello对象强引用
            System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference1.get());

        }
    }


}
