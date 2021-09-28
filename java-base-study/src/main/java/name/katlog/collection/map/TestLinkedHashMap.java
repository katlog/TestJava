package name.katlog.collection.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * 用链表维护了插入顺序
 *      链表中元素的顺序可以分为：按插入顺序的链表，和按访问顺序(调用 get 方法)的链表。默认是按插入顺序排序
 *
 * @see LinkedHashMap
 * Created by fw on 2021/4/15
 */
public class TestLinkedHashMap {

    @Test
    public void common(){

        // 生成LinkedHashMap
        Map<String, String> map = IntStream.rangeClosed(1, 100).boxed()
                .collect(Collectors.toMap(String::valueOf, String::valueOf, (s, s2) -> s, LinkedHashMap::new));

        int i = 1;
        for (String s : map.keySet()) {
            assertEquals(s, i++ + "");
        }
    }

    /**
     *  按插入顺序的链表，和按访问顺序(调用 get 方法)的链表。默认是按插入顺序排序
     * */
    @Test
    public void _construct(){
        Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        map.get("2");

        assertEquals("{1=1, 3=3, 2=2}", map.toString());

    }
}
