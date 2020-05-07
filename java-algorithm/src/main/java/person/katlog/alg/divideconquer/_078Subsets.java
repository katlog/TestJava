package person.katlog.alg.divideconquer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class _078Subsets {

    /**  分治法 */
   static  class Solution {
       List<List<Integer>> result = new ArrayList<>();
       public List<List<Integer>> subsets(int[] nums) {
           recur(nums, 0, new ArrayList<>());
           return result;
        }

        /**  应该完全参照  左右括号的思路  */
       private List<Integer> recur(int[] nums, int index, List<Integer> upper) {
           if (index > nums.length - 1) {
               return upper;
           }
           List<Integer> temp = Lists.newArrayList(upper);
           // 拆分子集
           List<Integer> recur = recur(nums, index+1, Lists.newArrayList(nums[index]));
           upper.addAll(recur);
           result.add(upper);

           List<Integer> recur1 = recur(nums, index+1, Lists.newArrayList());
           temp.addAll(recur1);
           result.add(temp);

           return upper;

       }

       public static void main(String[] args) {
            System.out.println(new Solution().subsets(new int[]{1,2,3}));
        }
    }


    static class Solution1{
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> subsets(int[] nums) {
            recur(nums, 0, new ArrayList<>());
            return result;
        }

        private void recur(int[] nums, int index, List<Integer> list) {
            if (index > nums.length - 1) {
                result.add(list);
                return;
            }

            recur(nums, index + 1,  Lists.newArrayList(list));
            list.add(nums[index]);
            recur(nums, index + 1, Lists.newArrayList(list));

            // 为啥？
            // list.remove(list.size() - 1);
        }

        public static void main(String[] args) {
            List<List<Integer>> subsets = new Solution1().subsets(new int[]{1, 2, 3});
            System.out.println("subsets.size() = " + subsets.size());
            System.out.println(subsets);
        }
    }
}
