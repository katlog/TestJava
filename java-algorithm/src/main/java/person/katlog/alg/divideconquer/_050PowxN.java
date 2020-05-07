package person.katlog.alg.divideconquer;

/**  注意这个限制 - 2147483648  */
public class _050PowxN {

    /**  原始  */
    static  class Solution {
        // public double myPow(double x, int n) {
        //     if (x == 0.0) {
        //         return 0.0;
        //     }
        //     long m = n;
        //
        //     double num = 1.0;
        //     // 整数
        //     if (m == 0 || x==1.0) {
        //         return 1;
        //     } else if (m < 0) {
        //         /**  【采用除法的方式计算时间太长了】 */
        //         for (int i = 0; i < -m; i++) {
        //             num /= x;
        //         }
        //     } else {
        //         for (int i = 0; i < m; i++) {
        //             num *= x;
        //         }
        //     }
        //
        //     return num;
        // }

        public double myPow(double x, int n) {
            if (x == 0.0 || x == 1) {
                return x;
            }

            double num = 1.0;
            if (n < 0) {
                x = 1 / x;
            }

            for (int i = 0; i < Math.abs(n); i++) {
                num *= x;
            }

            return num;
        }


        public static void main(String[] args) {

            System.out.println(new Solution().myPow(2.0, 10));
            System.out.println(new Solution().myPow(2.10000, 3));
            System.out.println(new Solution().myPow(2.00000, -2));
            // System.out.println(new Solution().myPow(2.00000, - 2147483648));
        }
    }


    static class Solution1{

        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double ans = 1;
            for (long i = 0; i < N; i++)
                ans = ans * x;
            return ans;
        }


        public static void main(String[] args) {

            // System.out.println(new Solution().myPow(2.0, 10));
            // System.out.println(new Solution().myPow(2.10000, 3));
            // System.out.println(new Solution().myPow(2.00000, -2));
            System.out.println(new Solution1().myPow(2.00000, - 2147483648));
        }

    }

    /**  分治 */
    static class  Solution2{
        public double myPow(double x, int n) {
            if (x == 0.0 || x == 1.0) {
                return x;
            }
            // 如何拆分子任务
            return recur(n < 0, x, n);
        }
        
        private double recur(boolean ne, double x, int n){
            if (n == 1 ) {
                return x;
            }
            if (n == -1) {
                return 1 / x;
            }
            // 如何拆分子任务
            boolean even = n % 2 == 0;
            return even ? recur(ne, x, n / 2) * recur(ne, x, n / 2)

                    : recur(ne, x, ne ? n + 1 : n - 1) * recur(ne, x, ne ? -1 : 1);
        }
        
        public static void main(String[] args) {
            System.out.println(new Solution2().myPow(2.0, 10));
            System.out.println(new Solution2().myPow(2.10000, 3));
            System.out.println(new Solution2().myPow(2.00000, -2));
            System.out.println(new Solution2().myPow(2.00000, - 2147483648));
        }
    }


    static class SolutionDP{

        public double myPow(double x, int n) {
            if (x == 0.0 || x == 1.0) {
                return x;
            }
            if (n < 0) {
                x = 1 / x;
            }
            boolean even = n % 2 == 0;
            if (even) {
                double pow = myPow(x * x, n);
                return pow * pow;
            } else {
                return myPow(x, n -1) *x;
            }
        }

        public static void main(String[] args) {
            System.out.println(new SolutionDP().myPow(2.0, 10));
            System.out.println(new SolutionDP().myPow(2.10000, 3));
            System.out.println(new SolutionDP().myPow(2.00000, -2));
            // System.out.println(new Solution2().myPow(2.00000, - 2147483648));
        }

    }


}
