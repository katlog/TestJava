package io.github.viscent.mtia.ch6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fw on 2020/1/14
 */
public class SyncCollectionSafeTraversal {
    final List<String> syncList = Collections.synchronizedList(new
            ArrayList<String>());

    // ...

    public void dump() {
        Iterator<String> iterator = syncList.iterator();

        synchronized (syncList) {

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
