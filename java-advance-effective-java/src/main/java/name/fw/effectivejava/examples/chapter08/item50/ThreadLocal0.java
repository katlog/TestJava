package name.fw.effectivejava.examples.chapter08.item50;

import java.util.HashMap;
import java.util.Map;

// Broken - inappropriate use of string as capability!
public class ThreadLocal0 {
    private final static Map<Thread, Map<String, Object>> threadLocalMap = new HashMap<Thread, Map<String, Object>>();
    
    private  ThreadLocal0() {
    }
    // Sets the current thread's value for the named variable
    public static void set(String key,Object value){
        Thread currentThread = Thread.currentThread();
        Map<String, Object> map = threadLocalMap.get(currentThread);
        if (map == null) {
            map = new HashMap<>();
            threadLocalMap.put(currentThread, map);
        }
        map.put(key, value);
    }
    
    // Returns the current thread's value for the named variable
    public static Object get(String key) {
        Map<String, Object> map = threadLocalMap.get(Thread.currentThread());
        return map==null?null:map.get(key);
    }
}
