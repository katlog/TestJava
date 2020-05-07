package person.katlog.alg.tree;

import person.katlog.alg.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**  N叉树的后序遍历 */
public class _590NAryTreePostorderTraversal {

    static class Solution {
        private List<Integer> result = new ArrayList<>();
        public List<Integer> postorder(Node root) {
            if (root != null) {
                if (root.children != null) {
                    for (Node child : root.children) {
                        postorder(child);
                    }
                }
                result.add(root.val);
            }
            return result;
        }

        public static void main(String[] args) {
            System.out.println(new Solution().postorder(Node.getInstance()));
        }

    }





    /**  迭代的方式 */
    static class Solution1 {
        private List<Integer> result = new ArrayList<>();
        public List<Integer> postorder(Node root) {

            Stack<Node> stack = new Stack<>();
            Node cur = root;
            stack.push(root);

            while ( cur!=null) {
                Node level = cur;
                if (level.children != null) {
                    for (int i = level.children.size() - 1; i >= 0; i--) {
                        Node node = level.children.get(i);
                        stack.push(node);
                        cur = node;
                    }
                } else {
                    break;
                }
            }

            while (!stack.empty()) {
                result.add(stack.pop().val);
            }

            return result;
        }

        public static void main(String[] args) {
            System.out.println(new Solution1().postorder(Node.getInstance()));
        }

    }
}
