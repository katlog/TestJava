package name.katlog.collections.apache;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by fw on 2019/5/28
 */
public class CollectionUtilsTest {

    public static void main(String[] args) {

        List<String> a = Arrays.asList("1", "2", "3", "3", "4", "5");
        List<String> b = Arrays.asList("3", "4", "4", "5", "6", "7");

        //并集
        Collection<String> union = CollectionUtils.union(a, b);
        //交集
        Collection<String> intersection = CollectionUtils.intersection(a, b);
        //交集的补集
        Collection<String> disjunction = CollectionUtils.disjunction(a, b);
        //集合相减
        Collection<String> subtract = CollectionUtils.subtract(a, b);

        Collections.sort((List<String>) union);
        Collections.sort((List<String>) intersection);
        Collections.sort((List<String>) disjunction);
        Collections.sort((List<String>) subtract);

        System.out.println("Union(A, B): " + ArrayUtils.toString(union.toArray()));
        System.out.println("Intersection(A, B): " + ArrayUtils.toString(intersection.toArray()));
        System.out.println("Disjunction(A, B): " + ArrayUtils.toString(disjunction.toArray()));
        System.out.println("Subtract(A, B): " + ArrayUtils.toString(subtract.toArray()));
    }
}
