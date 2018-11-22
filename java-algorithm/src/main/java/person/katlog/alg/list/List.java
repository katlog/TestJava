package person.katlog.alg.list;

import org.junit.Test;

public class List {

    static class Node<V>{
        Node next;
        V data;
    }


    static Node readyNode() {

        Node linkNode1 = new Node();
        linkNode1.data = 1;
        Node linkNode2 = new Node();
        linkNode2.data = 2;
        Node linkNode3 = new Node();
        linkNode3.data = 3;
        Node linkNode4 = new Node();
        linkNode4.data = 4;
        Node linkNode5 = new Node();
        linkNode5.data = 5;
        Node linkNode6 = new Node();
        linkNode6.data = 6;

        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
//        linkNode3.next = linkNode4;
//        linkNode4.next = linkNode5;
//        linkNode5.next = linkNode6;

        return linkNode1;
    }


    public Node reverseList(Node n) {

        if (n == null || n.next == null) {
            return n;
        }

        Node current = n;

        Node next = n.next;

        Node tail;

        do {
            tail = next.next;

            next.next = current;
            current = next;
            next = tail;

        } while (tail != null);

        n.next = null;

        return current;

    }


    public Node recursiveList(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node next = recursiveList(node.next);
        next.next = node;
//        node.next.next = node;
        node.next = null;
        return node;
    }

    static void printNode(Node node) {
        Node n = node;
        while (n != null) {
            System.out.println("node = " + n.data);
            n = n.next;
        }
    }
    /**
     * 非递归方式 反转链表
     */
    @Test
    public void reverseList() {
//        printNode(reverseList(readyNode()));
        printNode(recursiveList(readyNode()));
    }
}
