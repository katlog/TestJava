package person.katlog.alg.array;

import java.util.Arrays;

public class _283MoveZeroes {


    /**  copy的方式 */
   static  class Solution {
        public void moveZeroes(int[] nums) {
            int times = 0;
            for (int i = 0; i < nums.length - times - 1; ) {
                if (nums[i] != 0) {
                    i++;
                    continue;
                }
                System.arraycopy(nums, i + 1, nums, i, nums.length - i - 1);
                nums[nums.length-++times] = 0;
            }
        }
    }


    /**  O(N)的方式就可以解决 */
    static class Solution1 {
        public void moveZeroes(int[] nums) {
            int insertPos = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] !=0) nums[insertPos++] = nums[i];
            }
            for (int i = 0; i < nums.length-insertPos-1; i++) {
                nums[nums.length -i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        // new Solution().moveZeroes(new int[]{0, 1, 0, 3, 12});
        new Solution().moveZeroes(new int[]{0, 0, 1});
    }


}
