package name.fw.effectivejava.examples.chapter04.item22;

import java.util.AbstractSet;
import java.util.Iterator;
// Typical use of a nonstatic member class
public class MySet<E> extends AbstractSet<E> {

    private class MyIterator<E> implements Iterator<E>{
        @Override
        public boolean hasNext() {
            return false;
        }
        @Override
        public E next() {
            return null;
        }
        
    }
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>();
    }

    @Override
    public int size() {
        return 0;
    }
    



}
