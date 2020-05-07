package person.katlog.alg.list;

import person.katlog.alg.ListNode;

/**  两两交换链表中的节点 */
public class _024SwapNodesInPairs {

    /**
     * 双指针
     */
    static class Solution {

        public ListNode swapPairs(ListNode head) {

            if (head == null || head.next == null) {
                return head;
            }

            ListNode p1 = head;
            ListNode p2 = head.next;

            int times = 0;

            ListNode root = null;
            ListNode tail = null;
            while (p1 != null) {

                ListNode temp = p2.next;

                tail = p2;
                times++;
                if (times == 1) root = p2;
                p2.next = p1;
                p1.next = temp;
                p1 = temp;

                if (times > 1) {
                    tail.next = p2;
                }

                if (temp == null) {
                    break;
                }
                p2 = temp.next;
            }

            return root;
        }
    }


    /**  递归 */
    static class Solution1 {

        public ListNode swapPairs(ListNode head) {
            if (head==null || head.next==null) return head;
            return  recursive(head, head.next);
        }

        private ListNode recursive(ListNode head, ListNode next) {
            if (head == null || next == null) {
                return head;
            }

            ListNode tail = recursive(next.next, next.next == null ? null : next.next.next);


            ListNode temp = head;

            //交换
            next.next = head;
            head.next = tail;

            return next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new Solution().swapPairs(getListNode());
        print(node);

        ListNode node1 = new Solution1().swapPairs(getListNode());
        print(node1);


        ListNode node2 = new Solution3().swapPairs(getListNode());
        print(node2);
    }

    private static ListNode getListNode() {
        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        return new ListNode(1, node2);
    }

    private static void print(ListNode node) {
        ListNode cur = node;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

 static class Solution3 {
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
    }

}
