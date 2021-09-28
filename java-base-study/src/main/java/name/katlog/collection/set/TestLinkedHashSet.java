package name.katlog.collection.set;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by fw on 2021/9/7
 */
public class TestLinkedHashSet {

    @Test
    public void common(){
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(Arrays.asList(1, 2, 3, 4, 5));
        set.addAll(Arrays.asList(6, 7));
        set.addAll(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20));
        set.addAll(Arrays.asList(21, 22, 23, 24,25, 26));

        Iterator<Integer> iterator = set.iterator();
        Integer num = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            Assert.assertEquals(++num, next);
        }
    }
}
