package name.katlog.util.collections;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static <E,R> List<List<R>> split(Collection<E> source, int batchNum, Function<E,R> elementFunc) {
        List<List<E>> split = split(source, batchNum);
        if (isNotEmpty(split)) {
            return  split.stream()
                    .map(es -> es.stream().map(elementFunc).collect(Collectors.toList()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static <E,R> List<List<R>> splitBatchFun(Collection<E> source, int batchNum, Function<List<E>,List<R>> batchFunc) {
        List<List<E>> split = split(source, batchNum);
        if (isNotEmpty(split)) {
            return  split.stream()
                    .map(batchFunc)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


}
