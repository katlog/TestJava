package name.katlog.alg;

import org.junit.Test;


/**
 * @moudle: _2_List 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月10日 上午11:30:32
 *
 */
public class _2_List {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	/**
	 * 用时2个小时
	 * 出现错误20多次：测试发现15次，ide发现5次
	 */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode temp = new ListNode(0);
        ListNode re = new ListNode(0);
        ListNode tt = new ListNode(0);

        int flag = 0;
        int times = 0;
        while( null!=n1||null!=n2){
			if (n1 != null) {
				if (n2==null) {
					temp = new ListNode((n1.val + flag)%10);
					flag = (n1.val + flag)/10;
				}
			}
			if (n2 != null) {
				if (n1 != null) {
					temp = new ListNode((n2.val + n1.val + flag) % 10);
					flag = (n2.val + n1.val+flag) / 10;
				} else {
					temp = new ListNode((n2.val + flag)%10);
					flag = (n2.val + flag)/10;
				}
			}
			n1 = (n1==null?null:(n1.next == null ? null : n1.next));
			n2 = (n2==null?null:(n2.next == null ? null : n2.next));
			if (times == 0)
				re = temp;
			tt.next = temp;
			tt = tt.next;
			
			times++;
        }
        if(flag ==1){
            temp.next = new ListNode(1);
        }
        return re;
    }
    
	/**
	 * 
	 */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }
    
    @Test public void addTwoNumbers(){
    	ListNode l1 = new ListNode(2);l1.next = new ListNode(4); l1.next.next = new ListNode(3);
    	ListNode l2 = new ListNode(5);l2.next = new ListNode(6); l2.next.next = new ListNode(4);
    	ListNode node = addTwoNumbers1(l1, l2);
    	System.out.println(node);
    	ListNode l3 = new ListNode(1);
    	ListNode l4 = new ListNode(9);l4.next = new ListNode(9); 
    	ListNode node1 = addTwoNumbers1(l3, l4);
    	System.out.println(node1);
    	ListNode l5 = new ListNode(3);l5.next = new ListNode(7);
    	ListNode l6 = new ListNode(9);l6.next = new ListNode(2); 
    	ListNode node2 = addTwoNumbers1(l5, l6);
    	System.out.println(node2);
    }
}	
