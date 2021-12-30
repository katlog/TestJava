package name.katlog.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Created by fw on 2020/1/16
 */
public class TestSpliterator {

    /**
     * Spliterator是将一个stream进行对半平分的操作类
     * Arrays.parallelSetAll 和 IntStream.range可以生成一个指定长度Int的Stream
     */
    @Test
    public void _common(){

        int[] array = new int[10];
        Arrays.parallelSetAll(array, i -> i);

        // 这个方法也可以构建一个Stream，然后再构建Spliterator
        Spliterator.OfInt test = IntStream.range(0, 10).spliterator();
        test.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });
        System.out.println("+++++++++++");

        Spliterator.OfInt sp = Arrays.spliterator(array);
        // 分割完后sp还剩5个元素，sp1也是5个元素
        Spliterator.OfInt sp1 = sp.trySplit();
        // 分割完后sp1为3个，sp2为2个
        Spliterator.OfInt sp2 = sp1.trySplit();

        sp1.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });

        System.out.println("------------");

        sp2.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });

        System.out.println("------------");

        sp.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });
    }

    static final class ArrayListSpliterator<E> implements Spliterator<E> {

        //用于存放实体变量的list
        private final ArrayList<E> list;
        //遍历的当前位置
        private int index;
        //结束位置(不包括) 意思是当前可用的元素是[index, fence) = [index, fence-1]
        private int fence; // -1 until used; then one past last index

        // 构造方法
        ArrayListSpliterator(ArrayList<E> list, int origin, int fence) {
            this.list = list;
            this.index = origin;
            this.fence = fence;
        }

        //第一次使用的时候初始化fence 返回结束位置
        private int getFence() { // initialize fence to size on first use
            int hi; // (a specialized variant appears in method forEach)
            ArrayList<E> lst;
            if ((hi = fence) < 0) {
                if ((lst = list) == null)
                    hi = fence = 0;
                else {
                    hi = fence = lst.size();
                }
            }
            return hi;
        }

        /**
         * 根据当前的Spliterator拆分出一个新的Spliterator
         * 相当于二分,
         * Note:共享同一个list,改变的只是下标
         */
        @Override
        public ArrayListSpliterator<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide range in half unless too small
                    new ArrayListSpliterator<E>(list, lo, index = mid);
        }

        //单次遍历  下标index只加1
        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null)
                throw new NullPointerException();
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                @SuppressWarnings("unchecked") E e = (E)list.get(i);
                action.accept(e);
                return true;
            }
            return false;
        }

        //整体遍历
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            ArrayList<E> lst; Object[] a;
            if (action == null)
                throw new NullPointerException();
            if ((lst = list) != null && (a = lst.toArray()) != null) {
                if ((hi = fence) < 0) {
                    hi = lst.size();
                }
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        @SuppressWarnings("unchecked") E e = (E) a[i];
                        action.accept(e);
                    }
                }
            }
        }

        //剩下还有多少元素
        @Override
        public long estimateSize() {
            return (long) (getFence() - index);
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        public String toString() {
            return "[" + this.index + "," + getFence() + "]";
        }
    }

    @Test
    public void test_trySplit() {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i <= 10; i++) al.add(i);
        ArrayListSpliterator als_1 = new ArrayListSpliterator(al, 0, -1);
        System.out.println("als_1:" + als_1);    // [0,11]

        System.out.println("---------split-----------");
        ArrayListSpliterator als_2 = als_1.trySplit();
        System.out.println("als_1:" + als_1);    // [5,11]
        System.out.println("als_2:" + als_2);    // [0,5]

        // [0,11](als_1) ---> [0,5](als_2) + [5,11](als_1)

        System.out.println("---------split-----------");
        ArrayListSpliterator als_3 = als_1.trySplit();
        ArrayListSpliterator als_4 = als_2.trySplit();
        System.out.println("als_1:" + als_1);
        System.out.println("als_2:" + als_2);
        System.out.println("als_3:" + als_3);
        System.out.println("als_4:" + als_4);

        /**
         * [0,5](als_2)  --> [0,2](als_4)  + [2,5](als_2)
         * [5,11](als_1) --> [8,11](als_1) + [5,8](als_3)
         */

        System.out.println("---------test the address---------");
        System.out.println("(als_1.list == als_2.list) = " + (als_1.list == als_2.list));
        System.out.println("(als_2.list == als_3.list) = " + (als_2.list == als_3.list));
        System.out.println("(als_3.list == als_4.list) = " + (als_3.list == als_4.list));
    }
}
