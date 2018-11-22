/**
 * @Title: TestRandom.java
 * @Package: org.person.dfw.common.math
 * @author: 丰伟
 * @date: 2017年4月19日 上午11:21:58
 * @version: V1.0
 */
package org.person.dfw.common.math;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class TestRandom {
    @Test
    public void demo() {
        // 先建一个对象
        // 可以提供一个随机数种子，默认是系统时间
        Random rand = new Random();

        // 布尔型随机数
        System.out.println("rand.nextBoolean" + rand.nextBoolean());

        // 为一个byte数组赋随机数
        byte[] buffer = new byte[16];
        rand.nextBytes(buffer);
        System.out.println(Arrays.toString(buffer));

        // 生成数值介于[0,1.0)之间的伪随机数 
        System.out.println("rand.nextDouble：" + rand.nextDouble());

        // 生成[0.0,1.0)之间的伪随机数
        System.out.println("rand.nextFloat:" + rand.nextFloat());

        // 生成一个整数取值范围内的伪随机数  -231到231-1之间
        System.out.println("rand.nextInt:" + rand.nextInt());
        System.out.println("rand.nextInt3:" + rand.nextInt(3));

        // 生成一个0~26之间的伪随机数  该值介于[0,n)的区间
        System.out.println("rand.nextInt(26)：" + rand.nextInt(26));

        // 生成一个long整数取值范围的伪随机数
        System.out.println("rand.nextLong()：" + rand.nextLong());
    }
}
