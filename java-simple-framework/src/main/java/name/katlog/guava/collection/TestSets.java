package name.katlog.guava.collection;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by fw on 2021/8/31
 */
public class TestSets {

    @Test
    public void intersection(){

        Sets.SetView<Integer> sets = Sets.intersection(Sets.newHashSet(1, 2, 3, 5), Sets.newHashSet(1, 4, 5));

        Assert.assertArrayEquals(new Integer[]{1, 5}, sets.toArray(new Integer[0]));
    }
}
