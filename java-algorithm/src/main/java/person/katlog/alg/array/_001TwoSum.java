package person.katlog.alg.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _001TwoSum {

    /**  暴力求解 */
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            if(nums.length<2)
                return new int[]{};

            for(int i = 0; i < nums.length -1 ; i++){
                for (int j = i+1; j<nums.length; j++)
                    if(nums[i] + nums[j] == target)
                        return new int[]{i,j};
            }
            return new int[]{};
        }
    }


    /**  一遍hash （还有两边hash的就不写了）*/
    class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            if(nums.length<2)
                return new int[]{};

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{i, map.get(target - nums[i])};
                }
                map.put(nums[i], i);
            }
            return new int[]{};

        }
    }
}
