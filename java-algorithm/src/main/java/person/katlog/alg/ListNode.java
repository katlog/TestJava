package person.katlog.alg;

import java.util.Objects;

public class ListNode {
    public int val;
    public   ListNode next;
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +"val=" + val +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    public static ListNode F = new ListNode(-1, null);
    public static ListNode getListNode(int num) {
        return recursive(num);
        // return F.next;
    }

    private static ListNode recursive(int num){
        // 边界
        if (num <= 1) {
            ListNode node = new ListNode(num, null);
            // F.setNext(node);
            return node;
        }

        // process
        ListNode upper = recursive(--num);
        // ListNode n =  new ListNode(num+1, null);
        upper.next = new ListNode(num+1, null);

        return upper;
    }

    public static void main(String[] args) {
        ListNode listNode = getListNode(5);
        System.out.println("listNode = " + listNode);
    }
}
