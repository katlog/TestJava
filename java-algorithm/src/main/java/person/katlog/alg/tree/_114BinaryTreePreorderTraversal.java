package person.katlog.alg.tree;

import person.katlog.alg.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**  前序遍历二叉树:  根  左  右 */
public class _114BinaryTreePreorderTraversal {

    /**
     * 递归的方式
     */
    static class Solution {
        private List<Integer> result = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            tranval(root);
            return result;
        }

        private void tranval(TreeNode root) {
            if (root != null) {
                result.add(root.val);
                preorderTraversal(root.left);
                preorderTraversal(root.right);
            }
        }

        public static void main(String[] args) {
            List<Integer> integers = new Solution().preorderTraversal(TreeNode.getInstance());
            System.out.println("integers = " + integers);
        }
    }

    /**
     * 迭代的方式
     */
    static class Solution1 {
        private List<Integer> result = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {

            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;


            while (cur != null || !stack.empty()) {

                while (cur != null) {
                    result.add(cur.val);
                    stack.push(cur);
                    cur = cur.left;
                }
                TreeNode pop = stack.pop();
                cur = pop.right;

            }



            return result;
        }

        public static void main(String[] args) {
            List<Integer> integers = new Solution1().inorderTraversal(TreeNode.getInstance());
            System.out.println("integers = " + integers);
        }

    }
}
