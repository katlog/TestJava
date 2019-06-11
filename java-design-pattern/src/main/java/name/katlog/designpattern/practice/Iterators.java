package name.katlog.designpattern.practice;


import org.junit.Test;

import java.util.Iterator;

/**
 * Created by fw on 2019/6/11
 */
public class Iterators {

    public interface Collection{
		Iterator iterator();
		/*取得集合元素*/
        Object get(int i);
		/*取得集合大小*/
        int size();
    }

    class MyCollection implements Collection {
        private int[] members;
        public MyCollection(int[] members) {
            this.members = members;
        }

        @Override
        public Iterator iterator() {
            return new Iterator() {
                int i = 0;
                @Override
                public Object next() {
                    return members[i++];
                }
                @Override
                public boolean hasNext() {
                    return i<members.length;
                }
            };
        }
        @Override
        public Object get(int i) {
            return members[i];
        }

        @Override
        public int size() {
            return members.length;
        }
    }

    @Test
    public void tests(){
        Collection collection = new MyCollection(new int[]{1,3,3,5,5,6});
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
