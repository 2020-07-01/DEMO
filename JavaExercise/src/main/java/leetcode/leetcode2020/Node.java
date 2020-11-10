package leetcode.leetcode2020;

import java.util.List;

/**
 * @ClassName : Node
 * @Author : yq
 * @Date: 2020-11-10
 * @Description :
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}