package person.katlog.alg.list;

import lombok.ToString;
import org.junit.Test;
import person.katlog.alg.ListNode;

public class _025ReverseNodesInKGroup {
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {

            ListNode dump = new ListNode(0, head);

            cursive(head, k);

            return dump.next;
        }

        private ListNode cursive(ListNode head, int k) {
            if (head == null || k <= 0) {
                return head;
            }

            int size = 0;

            ListNode cur = head;

            // k 个 交换
            ListNode node = swapK(cur, k);

            // 下一个k 个交换
            cursive(node, k);

            return cur;
        }

    }


    private ListNode swapK(ListNode cur, int k) {
        if (cur==null && k > 0) {
            return cur;
        }

        ListNode h = cur.next;
        cur.next = swapK(cur.next, k--);
        h.next = cur;

        return cur;
    }


    @Test
    public void testSwapK() {

    }



    public static void main(String[] args) {

    }
}
