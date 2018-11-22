package name.fw.effectivejava.examples.chapter08.item50;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocal1 {
    
    private final static Map<Thread, Map<Key, Object>> threadLocalMap = new HashMap<Thread, Map<Key, Object>>();
    
    public static class Key{ // (Capability)
        Key(){}
    }
    public static Key getKey(){
        return new Key();
    }
    public static void set(Key key,Object value){
        Thread currentThread = Thread.currentThread();
        Map<Key, Object> map = threadLocalMap.get(currentThread);
        if (map == null) {
            map = new HashMap<>();
            threadLocalMap.put(currentThread, map);
        }
        map.put(key, value);
    }
    
    public static Object get(Key key) {
        Map<Key, Object> map = threadLocalMap.get(Thread.currentThread());
        return map==null?null:map.get(key);
    }
}
