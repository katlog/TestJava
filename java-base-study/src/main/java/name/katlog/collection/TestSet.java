package name.katlog.collection;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/6/21
 */
public class TestSet {

    Set<String> set1 ;
    Set<String> set2 ;
    @Before
    public void tear(){
        set1 = Sets.newHashSet("a", "b", "c", "d", "e");
        set2 = Sets.newHashSet("c", "e", "f", "j", "k");
    }

    /** 添加 */
    @Test
    public void addALL(){
        set1.addAll(set2);
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f", "j", "k"}, set1.toArray());
    }

    /** 保留 */
    @Test
    public void retainAll(){
        set1.retainAll(set2);
        assertArrayEquals(new String[]{"c",  "e"}, set1.toArray());
    }

    /** 移除 */
    @Test
    public void removeAll(){
        set1.removeAll(set2);
        assertArrayEquals(new String[]{"a", "b", "d"}, set1.toArray());
    }
}
