package person.katlog.alg.dynamicprograming;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 *       [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 *
 * */
public class _120Triangle {

    static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {

            // dp
            List<List<Integer>> dp = new ArrayList<>(triangle);

            // problem( n ) =

            for (int i = triangle.size() - 1; i >= 0; i--) {
                List<Integer> list = triangle.get(i);
                for (int j = 0; j < list.size() - 1; j++) {
                    int dpE = Math.min(list.get(j), list.get(j + 1)) + triangle.get(i - 1).get(j);
                    List<Integer> integers = dp.get(i);
                    if (integers == null) {
                        dp.add(new ArrayList<>());
                    }
                    integers = dp.get(i);
                    integers.add(dpE);
                }
            }

            return dp.get(0).get(0);
        }

        public static void main(String[] args) {


            List<List<Integer>> triangle = new ArrayList<>();
            triangle.add(Lists.newArrayList(2));
            triangle.add(Lists.newArrayList(3,4));
            triangle.add(Lists.newArrayList(6, 5, 7));
            triangle.add(Lists.newArrayList(4, 1, 8, 3));



            System.out.println(new Solution().minimumTotal(triangle));

        }
    }
}
