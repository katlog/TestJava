package name.katlog.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/7/10
 */
public class TestCollection {

    @Test
    public void toArray(){
        Collection<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, list.toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, list.toArray(new Integer[5]));
        /** 注意 数据多事会补充null */
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, null, null}, list.toArray(new Integer[8]));

        Collection<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, sets.toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, sets.toArray(new Integer[5]));
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, null, null}, sets.toArray(new Integer[8]));
    }

    @Test
    public void removeIf(){
        Collection<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        list.removeIf(integer -> integer == 3);

        assertArrayEquals(new Integer[]{1, 2, 4, 5, 6}, list.toArray(new Integer[0]));
    }
}
