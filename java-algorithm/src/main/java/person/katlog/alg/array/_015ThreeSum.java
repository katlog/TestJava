package person.katlog.alg.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**  三数之和 */
public class _015ThreeSum {
    class Solution1{
        public List<List<Integer>> threeSum(int[] nums) {
            if( nums == null || nums.length < 3)
                return new ArrayList<>();
            List<List<Integer>> result = new ArrayList<>();
            for(int i = 0; i< nums.length -3 ; i++){
                for(int j = i+1; j< nums.length -2 ; j++ )
                    for(int k = j+1; k< nums.length -1 ; k++ )
                        if(nums[i]+nums[j]+nums[k] == 0){
                            List<Integer> a = new ArrayList<>();
                            a.add(nums[i]);
                            a.add(nums[j]);
                            a.add(nums[k]);
                            result.add(a);
                        }
            }
            for (int i = 0; i < 3; i++) {
                for (List<Integer> integers : result) {

                }
            }
            return result;
        }
    }

 static   class Solution2{
        public List<List<Integer>> threeSum(int[] nums) {

            if( nums == null || nums.length < 3)
                return new ArrayList<>();
            Arrays.sort(nums);

            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i] > 0) {
                    break;
                }
                if (i > 0 && nums[i] == nums[i-1] ) {
                    continue;
                }

                int l = i+1,r = nums.length-1;
                while (r > l) {
                    int sum = nums[i] + nums[l] + nums[r];
                    while (l<r &&nums[l]==nums[l+1] )
                        l++;
                    while (l<r &&nums[r]==nums[r-1])
                        r--;

                    if (sum == 0) {

                        ArrayList<Integer> a = new ArrayList<>();
                        a.add(nums[i]);a.add(nums[l]);a.add(nums[r]);
                        result.add(a);
                        r--;
                        l++;
                    }else if(sum>0) {
                        r--;
                    }else {
                        l++;
                    }

                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution2().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println("lists = " + lists);
        lists = new Solution2(). threeSum(new int[]{0,0,0});
        System.out.println("lists = " + lists);

        lists = new Solution2(). threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0});
        System.out.println("lists = " + lists);
    }

}
