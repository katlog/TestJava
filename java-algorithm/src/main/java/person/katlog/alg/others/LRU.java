package person.katlog.alg.others;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class LRU{

    private HashMap<String,Object> map = new HashMap<>();

    private LinkedList<Object> list = new LinkedList<>();

    private int size;

    public LRU(int size) {
        this.size = size;
        map = new HashMap<>(size);
        list = new LinkedList<>();
    }

    public void put(String key, Object val) {
        if (map.size() == size) {
            list.peekLast();
        }
        list.addFirst(key);
        map.put(key, val);

        Consumer consumer = Object::notify;

    }


}
