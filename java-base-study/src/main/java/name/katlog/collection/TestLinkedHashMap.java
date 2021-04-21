package name.katlog.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 用链表维护了插入顺序
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
            Assert.assertEquals(s, i++ + "");
        }
    }
}
