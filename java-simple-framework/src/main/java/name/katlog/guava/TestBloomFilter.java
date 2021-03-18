package name.katlog.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * <a href="https://blog.csdn.net/CrankZ/article/details/84928562">布隆过滤器介绍及guava中的实现</a>
 * Created by fw on 2019/7/24
 */
public class TestBloomFilter {


    public static void main(String[] args) {
        // 1.创建符合条件的布隆过滤器
        // 预期数据量10000，错误率0.0001
        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.0001);

        // 2.将一部分数据添加进去
        for (int i = 0; i < 5000; i++) {
            bloomFilter.put("" + i);
        }
        System.out.println("数据写入完毕");
        // 3.测试结果
        for (int i = 0; i < 10000; i++) {
            if (bloomFilter.mightContain("" + i)) {
                System.out.println(i + "存在");
            } else {
                System.out.println(i + "不存在");
            }
        }
    }
}
