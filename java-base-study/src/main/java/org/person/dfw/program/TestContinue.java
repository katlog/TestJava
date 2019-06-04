package org.person.dfw.program;

import org.junit.Assert;

/**
 * Created by dell on 2018/4/28
 */
public class TestContinue {

    @org.junit.Test
    public void test() {

        int i = 0;

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                i++;
                sb.append(i + "  ");
            }
        }
        System.out.println(sb);
        Assert.assertEquals(100,i);


        i = 0;
        sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                if (i > 5) {
                    continue;
                }
                i++;
                sb.append(i + "  ");
            }
        }
        System.out.println(sb);
        Assert.assertEquals(6,i);

        i = 0;
        sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                if (i > 5) {
                    if (i>5)
                        continue;
                    i++;
                }
                i++;
                sb.append(i + "  ");
            }
            i++;
            sb.append(i + "  ");
        }
        System.out.println(sb);
        Assert.assertEquals(16,i);







        i = 0;
        sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                if (i > 5) {
                    break;
                }
                i++;
                sb.append(i + "  ");
            }
        }
        System.out.println(sb);
        Assert.assertEquals(6,i);


        i = 0;
        sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                if (i > 5) {
                    break;
                }
                i++;
                sb.append(i + "  ");
            }
            i++;
            sb.append(i + "  ");
        }
        System.out.println(sb);
        Assert.assertEquals(16,i);

    }
}
