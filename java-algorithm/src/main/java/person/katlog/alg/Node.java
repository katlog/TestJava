package person.katlog.alg;

import com.google.common.collect.Lists;

import java.util.List;

public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }


    public static Node getInstance(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.children = Lists.newArrayList(n3, n2, n4);
        n3.children = Lists.newArrayList(n5, n6);

        return n1;
    }


    /**  [1,null,3,2,4,null,5,6]  */
    public static Node getInstance1(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.children = Lists.newArrayList(n3, n2, n4);
        n3.children = Lists.newArrayList(n5, n6);

        return n1;
    }
}
