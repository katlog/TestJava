package person.katlog.alg.dynamicprograming;

public class _070ClimbingStairs {

    static class Solution {

        public int climbStairs(int n) {
            if (n <= 1) {
                return n;
            }
            return climbStairs(n - 1) + climbStairs(n - 2);
        }

        public static void main(String[] args) {
            System.out.println(new Solution().climbStairs(1));
            System.out.println(new Solution().climbStairs(2));
            System.out.println(new Solution().climbStairs(3));
            System.out.println(new Solution().climbStairs(4));
            System.out.println(new Solution().climbStairs(5));
        }
    }


    static class Solution1 {

        public int climbStairs(int n) {
            int[] cache = new int[n+1];
            return cur(n,cache);
        }

        /**  可以更加简洁的 */
        private int cur(int n, int[] cache) {
            if (n <= 1) {
                return n;
            }
            if (cache[n] != 0) {
                return cache[n];
            }
            int cur1 = cur(n - 2, cache);
            cache[n - 2] = cur1;
            return cur(n - 1, cache) + cur1;
        }

        public static void main(String[] args) {
            System.out.println(new Solution1().climbStairs(1));
            System.out.println(new Solution1().climbStairs(2));
            System.out.println(new Solution1().climbStairs(3));
            System.out.println(new Solution1().climbStairs(4));
            System.out.println(new Solution1().climbStairs(5));
        }
    }



    static class SolutionDP {

        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }

            int[] ins = new int[n + 1];
            ins[1] = 1;
            ins[2] = 2;

            for (int i = 3; i <= n ; i++) {
                ins[i] = ins[i - 1] + ins[i - 2];
            }

            return ins[n];
        }


        public static void main(String[] args) {
            System.out.println(new SolutionDP().climbStairs(1));
            System.out.println(new SolutionDP().climbStairs(2));
            System.out.println(new SolutionDP().climbStairs(3));
            System.out.println(new SolutionDP().climbStairs(4));
            System.out.println(new SolutionDP().climbStairs(5));
        }
    }
}
