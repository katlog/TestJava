package person.katlog.alg.interview;

import person.katlog.alg.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _141LinkedListCycle {

    public class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> cache = new HashSet<>();
            ListNode cur = head;
            while (cur != null) {
                if (cache.contains(cur.next) ) {
                    return true;
                }
                cache.add(cur);
                cur = cur.next;
            }
            return false;
        }
    }

    static class a{
        public boolean hasCycle(ListNode head) {
            if(head==null||head.next ==null)
                return false;

            ListNode i = head;
            ListNode j = head.next;

            while(i!=null&&j.next!=null){
                if(i==j)
                    return true;
                i = i.next;
                j = j.next.next;
            }
            return false;
        }
    }

    static class Solution2{
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null)
                return false;

            ListNode i = head;
            ListNode j = head.next;

            while (j != null && j.next != null) {
                if (i == j)
                    return true;

                i = i.next;
                j = j.next.next;
            }
            return false;
        }
    }

    public class Solution1 {
        public boolean hasCycle(ListNode head) {
            if(head==null || head.next==null) {
                return false;
            }
            //定义两个指针i和j，i是慢指针，j是快指针
            ListNode i = head;
            ListNode j = head.next;
            while(j!=null && j.next!=null) {
                if(i==j) {
                    return true;
                }
                //i每次走一步，j每次走两步
                i = i.next;
                j = j.next.next;
            }
            return false;
        }
    }



}
