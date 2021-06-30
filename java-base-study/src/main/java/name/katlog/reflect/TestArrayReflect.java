package name.katlog.reflect;

import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayReflect {

    @Test
    public void test() {
        Class<int[]> intArrayClass = int[].class;

        // isArray判断是否是数组
        assertTrue(intArrayClass.isArray());
        // getComponentType获取基本元素类型
        assertEquals(int.class, intArrayClass.getComponentType());
    }
}
