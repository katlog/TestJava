package org.person.dfw.util.collections;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by fw on 2019/7/10
 */
public class CollectionUtil extends CollectionUtils {

    public static <E> Collection<E> filter(Collection<E> c, Predicate<E> p) {
        c.removeIf(o -> !p.test(o));
        return c;
    }

    public static <E> List<List<E>> split(Collection<E> source, int batchNum) {
        if (isEmpty(source)) {
            return Collections.emptyList();
        }

        List<List<E>> resultList = new ArrayList<>();

        int totalSize = source.size();
        int n = totalSize / batchNum;
        int remainder = totalSize % batchNum;

        Iterator<E> iterator = source.iterator();
        for (int i = 0; i < n; i++) {
            List<E> batch = new ArrayList<>();
            for (int j = 0; j < batchNum; j++) {
                batch.add(iterator.next());
            }
            resultList.add(batch);
        }

        if (remainder > 0) {
            List<E> batch = new ArrayList<>();
            for (int i = 0; i < remainder; i++) {
                batch.add(iterator.next());
            }
            resultList.add(batch);
        }
        return resultList;
    }



}
