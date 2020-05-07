package person.katlog.alg.tree;

import person.katlog.alg.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**  中序便利二叉树 */
public class _094BinaryTreeInorderTraversal {

    /**
     * 递归的方式
     */
    static class Solution {
        private List<Integer> result = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            tranval(root);
            return result;
        }

        private void tranval(TreeNode root) {
            if (root != null) {
                inorderTraversal(root.left);
                result.add(root.val);
                inorderTraversal(root.right);
            }
        }



        public static void main(String[] args) {
            List<Integer> integers = new Solution().inorderTraversal(TreeNode.getInstance());
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

                    stack.push(cur);
                    cur = cur.left;
                }


                TreeNode peek = stack.pop();
                result.add(peek.val);

                cur = peek.right;

            }


            return result;
        }

        public static void main(String[] args) {
            List<Integer> integers = new Solution1().inorderTraversal(TreeNode.getInstance());
            System.out.println("integers = " + integers);
        }

    }
}
