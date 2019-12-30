package binary_search_tree;

/**
 * @program: Node
 * @description: 节点类
 * @create: 2019-12-27 22:20
 **/
public class Node {

    private int value;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node(int value) {
        this(null, null, value);
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
