package b_plus_tree;

import java.util.List;

/**
 * @program: Node
 * @description: B+树的节点类
 * @create: 2019-12-29 14:20
 **/
public class Node {

    //节点的子节点
    private List<Node> children;
    //节点的关键字列表
    private List<KeyAndValue> keyAndValues;
    //节点的后节点
    private Node nextNode;
    //节点的前节点
    private Node previousNode;
    //节点的父节点
    private Node parentNode;

    public Node(List<Node> children, List<KeyAndValue> keyAndValues, Node nextNode, Node previousNode, Node parentNode) {
        this.children = children;
        this.keyAndValues = keyAndValues;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
        this.parentNode = parentNode;
    }

    //判断是否是叶子节点
    boolean isLeaf() {
        return children == null;//叶子节点的子节点为空
    }

    //判断是否为头节点
    boolean isHead() {
        return this.previousNode == null;
    }

    //判断是否为尾节点
    boolean isTail() {
        return this.nextNode == null;//尾节点的下一个节点为空
    }

    //判断是否是根节点
    boolean isRoot() {
        return this.parentNode == null;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public List<KeyAndValue> getKeyAndValues() {
        return keyAndValues;
    }

    public void setKeyAndValues(List<KeyAndValue> keyAndValues) {
        this.keyAndValues = keyAndValues;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
}
