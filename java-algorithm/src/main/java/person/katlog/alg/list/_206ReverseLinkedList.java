package person.katlog.alg.list;

import lombok.ToString;
import person.katlog.alg.ListNode;

public class _206ReverseLinkedList {



  static  class Solution {
        public ListNode reverseList(ListNode head) {
            return reverseListInt(head,null);
        }

      private ListNode reverseListInt(ListNode head, ListNode newHead) {
          if (head == null)
              return newHead;
          ListNode next = head.next;
          head.next = newHead;
          ListNode result = reverseListInt(next, head);
          return result;
      }
      public static void main(String[] args) {
          ListNode node = new Solution().reverseList(getListNode());
          printNode(node);
      }
  }


    private static ListNode getListNode() {
        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        return new ListNode(1, node2);
    }

    public static void printNode(ListNode node) {
        ListNode curr =node;

        while (curr != null) {
            System.out.println("node = " + curr.val);
            curr = curr.next;
        }
    }

    /**  这种会更清楚些 */
    static class Solution1{
        public ListNode reverseList(ListNode head) {
            return reverseListInt(head);
        }

        private ListNode reverseListInt(ListNode head) {
            if (head == null || head.next ==null) {
                return head;
            }
            //这里的cur就是最后一个节点
            ListNode cur = reverseList(head.next);

            //这里请配合动画演示理解
            //如果链表是 1->2->3->4->5，那么此时的cur就是5
            //而head是4，head的下一个是5，下下一个是空
            //所以head.next.next 就是5->4
            head.next.next = head;

            //防止链表循环，需要将head.next设置为空

            head.next = null;
            //每层递归函数都返回cur，也就是最后一个节点

            return cur;
        }

        public static void main(String[] args) {
            ListNode node = new Solution1().reverseList(getListNode());
            printNode(node);
        }
    }

    static class Solution3 {
        public ListNode reverseList(ListNode head) {

            ListNode p1 = null;
            ListNode p2 = head;

            while (p2 != null) {
                ListNode temp= p2.next;

                p2.next = p1;

                p1 = p2;
                p2 = temp;
            }

            return p1;

        }

        public static void main(String[] args) {
            ListNode node = new Solution().reverseList(getListNode());
            printNode(node);
        }

    }

    static class SolutionTest {

        public ListNode reverseList(ListNode head) {
            // terminator
            if (head == null || head.next == null) {
                return head;
            }
            ListNode listNode = reverseList(head.next);

            ListNode p1 = head;
            head.next.next = p1;
            head.next = null;

            return listNode;
        }

        public static void main(String[] args) {
            ListNode node = new SolutionTest().reverseList(getListNode());
            printNode(node);
        }

    }


}

