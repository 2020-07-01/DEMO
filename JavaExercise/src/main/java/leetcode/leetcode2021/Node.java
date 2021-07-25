package leetcode.leetcode2021;

/**
 * @ClassName : Node
 * @Author : yq
 * @Date: 2021-07-11
 * @Description :
 */
public class Node {

    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
