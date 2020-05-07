package person.katlog.alg.recursive;

import java.util.ArrayList;
import java.util.List;

public class _022GenerateParentheses {


    /**  没做出来 */
    static class Solution {
        public List<String> generateParenthesis(int n) {

            return recursive(n, 0, 0, "", new ArrayList<>());

        }

        private List<String> recursive(int n, int left,int right, String s,List<String> result) {

            // terminate
            if (left ==n && right ==n) {
                result.add(s);
                return result;
            }

            // process

            // left 随时可以加 left<n
            String s1 = s + "(";
            if (left + 1 <= n) {
                recursive(n, left + 1, right, s1, result);
            }

            // right 数量小于左括号 才可以加

            String s2 = s + ")";
            if (right < left) {
                recursive(n, left, right + 1, s2, result);
            }
            // pruning

            // drill down

            return result;
        }
    }

    public static void main(String[] args) {

        List<String> strings = new Solution().generateParenthesis(3);
        System.out.println("strings.size() = " + strings.size());
        strings.forEach(System.out::println);

        strings = new Solution1().generateParenthesis(3);
        System.out.println("strings.size() = " + strings.size());
        strings.forEach(System.out::println);

    }



    static class Solution1 {

        private ArrayList<String> result;

        public List<String> generateParenthesis(int n) {

            result = new ArrayList<>();
            recursive(0, 0, n, "");
            return result;

        }

        private void recursive(int left, int right, int n, String s) {

            // terminate
            if (left == n && right == n) {
                result.add(s);
            }

            // process
            recursive(++left, right, n, s + "(");
            recursive(left, ++right, n, s + ")");

            // pruning

            // drill down
        }
    }
}
