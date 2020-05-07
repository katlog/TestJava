package person.katlog.alg.array;

import java.util.Arrays;

// public int[] plusOne(int[] digits) {
// }
public class _66PlusOne {

    /**  注意特殊情况，注意边界 像 9 时   而且也容易越界  */
    @Deprecated
 static class Solution {
        public int[] plusOne(int[] digits) {

            long num = 0;
            int[] result = new int[digits.length];

            for(int i = digits.length -1 ; i>=0; i--){
                num += digits[i] * (Math.pow(10, digits.length - 1 - i));
            }
            num++;

            int size  = 0;
            long n = num;
            while (n > 0) {
                n /= 10;
                size++;
            }

            System.out.println("size = " + size);
            if (size == digits.length) {
                for (int i = digits.length - 1; i >= 0; i--) {
                    result[i] = (int) (num % 10);
                    num = num / 10;
                }
                return result;
            } else {
                result = new int[digits.length +1];
                for (int i = digits.length ; i >= 0; i--) {
                    result[i] = (int) (num % 10);
                    num = num / 10;
                }
                return result;
            }

        }
    }


    static class Solution1{

        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] < 9) {
                    digits[i]++;
                    return digits;
                }
                digits[i] = 0;
            }
            int[] newNumber = new int [digits.length+1];
            newNumber[0] = 1;

            return newNumber;


        }
    }

    public static void main(String[] args) {
        System.out.println("args = " + (10));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{9,8,7,6,5,4,3,2,1,0})));

        System.out.println(Arrays.toString(new Solution1().plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(new Solution1().plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(new Solution1().plusOne(new int[]{1, 2, 9})));
        System.out.println(Arrays.toString(new Solution1().plusOne(new int[]{9,8,7,6,5,4,3,2,1,0})));
    }
}
