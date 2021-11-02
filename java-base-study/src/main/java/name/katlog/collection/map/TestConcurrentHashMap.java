package name.katlog.collection.map;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fw on 2021/10/28
 */
public class TestConcurrentHashMap {
    @Test
    public void _construct(){
        // 空的 不初始化任何东西
        Map<String, String> map = new ConcurrentHashMap<>();

        map = new ConcurrentHashMap<>(12);

    }

    @Test
    public void put(){
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
    }
}
