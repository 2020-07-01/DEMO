package binary_search_tree;

import java.util.Stack;

/**
 * @program: BinarySortTree
 * @description: 二叉树的实现
 * @create: 2019-12-28 09:55
 **/
public class BinarySortTree {

    //创建根节点
    private Node rootNode;

    //查找二叉排序树中是否存在key值
    public boolean containsKey(int key) {
        Node current = rootNode;
        while (current != null) {
            if (current.getValue() == key) {
                return true;
            } else if (key < current.getValue()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }

    //向二叉排序树中插入节点
    public void insert(int key) {
        Node p = rootNode;
        Node prev = null;
        while (p != null) {
            prev = p;
            if (key < p.getValue()) {
                p = p.getLeft();
            } else if (key > p.getValue()) {
                p = p.getRight();
            } else
                return;//当遇到的相同的值时结束循环
        }
        if (rootNode == null) {
            rootNode = new Node(key);
        } else if (key < prev.getValue()) {
            prev.setLeft(new Node(key));
        } else prev.setRight(new Node(key));
    }

    //根据key获取节点
    private Node getNode(int key) {
        Node nodeKey = rootNode;
        while (nodeKey != null) {
            if (key < nodeKey.getValue()) {
                nodeKey = nodeKey.getLeft();
            } else if (key > nodeKey.getValue()) {
                nodeKey = nodeKey.getRight();
            } else {
                return new Node(key);
            }
        }
        return null;
    }

    /**
     * 删除节点
     * 1.节点的右子树为空的情况-》用节点的左子树替换此节点
     * 2.节点的左子树为空的情况-》用节点的右子树替换此节点
     * 3.节点的左右子树均不为空-》找出删除节点的后继节点进行替换，然后删除
     *
     * @param key
     * @return
     */
    public void delete(int key) {
        delete(rootNode, key);
    }

    private boolean delete(Node node, int key) {
        //如果根节点为空则返回false
        if (node == null) {
            return false;
        } else {
            if (key == node.getValue()) {
                return delete(node);
            } else if (key < node.getValue()) {
                return delete(node.getLeft(), key);
            } else {
                return delete(node.getRight(), key);
            }
        }
    }

    private boolean delete(Node node) {
        Node temp = null;
        if (node.getRight() == null) {//如果右子树为空
            node = node.getLeft();
        } else if (node.getLeft() == null) {//如果左子树为空
            node = node.getRight();
        } else {//左右子树均不为空,转向左子树，然后向右走向尽头
            temp = node;
            Node s = node;
            s = s.getLeft();
            while (s.getRight() != null) {
                temp = s;//父节点
                s = s.getRight();
            }
            node.setValue(s.getValue());
            if (temp == node) {
                temp.setLeft(s.getLeft());
            } else {
                temp.setRight((s.getLeft()));
            }
        }
        return true;
    }

    //中序非递归遍历二叉树
    public void nrInOrderTraverse() {
        Stack<Node> stack = new Stack<>();
        Node node = rootNode;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.pop();
            System.out.print(node.getValue() + "  ");
            node = node.getRight();
        }
        System.out.println();
    }

    //先序递归遍历二叉树
    public void preOrderResursive() {
        preOrderResursive(rootNode);
        System.out.println();
    }

    private void preOrderResursive(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        preOrderResursive(node.getLeft());
        preOrderResursive(node.getRight());
    }

    //中序递归遍历二叉树
    public void InorderResursive() {
        InorderResursive(rootNode);
        System.out.println();
    }

    private void InorderResursive(Node node) {
        if (node == null) {
            return;
        }
        InorderResursive(node.getLeft());
        System.out.print(node.getValue() + " ");
        InorderResursive(node.getRight());
    }

    //后序递归遍历二叉树
    public void postOrderResursive() {
        postOrderResursive(rootNode);
        System.out.println();
    }

    private void postOrderResursive(Node node) {
        if (node == null) {
            return;
        }
        postOrderResursive(node.getLeft());
        postOrderResursive(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    public static void main(String[] args) {
        int[] num = {45, 12, 37, 24, 3, 53, 100, 61, 55, 90, 78};
        BinarySortTree bst = new BinarySortTree();
        for (int i = 0; i < num.length; i++) {
            bst.insert(num[i]);
        }
        bst.nrInOrderTraverse();

        System.out.println("先序递归遍历：");
        bst.preOrderResursive();
        System.out.println("中序递归遍历：");
        bst.InorderResursive();
        System.out.println("后序递归遍历：");
        bst.postOrderResursive();
    }
}
