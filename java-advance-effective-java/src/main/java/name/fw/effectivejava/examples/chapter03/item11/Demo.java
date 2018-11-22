package name.fw.effectivejava.examples.chapter03.item11;

public class Demo {

}

class HashTable implements Cloneable{
    
    private Entry[] buket = new Entry[0];
    private static class Entry{
        final Object key;
        Object value;
        Entry next;
        Entry(Object key,Object value,Entry next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
}