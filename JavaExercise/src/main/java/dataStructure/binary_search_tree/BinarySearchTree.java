package dataStructure.binary_search_tree;

import java.util.*;

/**
 * @author yq
 * @program: BinarySortTree
 * @description: 二叉搜索树的实现
 * @create: 2019-12-28 09:55
 **/
public class BinarySearchTree<T extends Comparable> {

    /**
     * 性质：
     * 1、二叉搜索树、二叉排序树、二叉查找树
     * 2、若任意节点的左子树不为空，则左子树上的节点不大于父节点
     * 3、若任意节点的右子树不为空，则右子树的上的节点不小于父节点
     * 4、左子树和右子树也是一颗二叉搜索树
     * <p>
     * 时间复杂度分析：logn
     */

    //根节点
    private Node<T> root;
    //节点数
    private int size;


    BinarySearchTree() {

    }


    static class Node<T extends Comparable> {

        T value;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        Node() {
            this.value = null;
            this.left = null;
            this.right = null;
            this.parent = null;
        }


    }

    /**
     * 插入节点
     *
     * @param value
     */
    public void insert(T value) {

        if (value == null) {
            throw new NullPointerException();
        }

        Node<T> x = this.root;
        Node<T> parent = null;
        int cmp;
        while (x != null) {
            parent = x;
            cmp = value.compareTo(x.value);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        Node<T> newNode = new Node<>(value);
        newNode.parent = parent;
        //不为根节点
        if (parent != null) {
            int n = value.compareTo(parent.value);
            if (n < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        } else {
            //设置为根节点
            this.root = newNode;
        }
        size++;
    }


    /**
     * 查找节点
     *
     * @param value
     */
    public Node getNode(T value) {
        if (this.root == null) {//根节点为空
            return null;
        }

        Node cur = this.root;
        int cmp = value.compareTo(cur.value);
        while (cmp != 0) {
            if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            if (cur == null) {
                return null;
            }
            cmp = value.compareTo(cur.value);
        }
        return cur;
    }

    /**
     * 二叉树的删除
     * case1:待删除的节点没有子节点，直接删除
     * case2:待删除的节点有一个子节点，直接删除，用其子节点代替它
     * case3:有两个子节点  找右子树的最左节点或者左子树的最右节点进行替换
     */
    public void remove(T value) {
        //value为空
        if (value == null) {
            throw new NullPointerException();
        }
        //节点为空
        if (root == null) {
            return;
        }

        Node removeNode = getNode(value);
        if (removeNode != null) {

            //case1:没有任何子节点
            if (removeNode.left == null && removeNode.right == null) {
                Node parent = removeNode.parent;
                if (parent != null) {
                    if (parent.left != null && parent.left == removeNode) {
                        parent.left = null;
                    }
                    if (parent.right != null && parent.right == removeNode) {
                        parent.right = null;
                    }
                } else {
                    //为根节点
                    this.root = null;
                }

            }
            //case2:只有一个子节点
            else if (removeNode.left != null && removeNode.right == null) {//左边不为空
                Node parent = removeNode.parent;
                if (parent != null) {
                    if (parent.left != null && parent.left == removeNode) {
                        parent.left = removeNode.left;
                    }
                    if (parent.right != null && parent.right == removeNode) {
                        parent.right = removeNode.left;
                    }
                } else {
                    this.root = removeNode.left;
                    this.root.parent = null;
                }

            } else if (removeNode.left == null && removeNode.right != null) {//右边不为空
                Node parent = removeNode.parent;
                if (parent != null) {
                    if (parent.left != null && parent.left == removeNode) {
                        parent.left = removeNode.left;
                    }
                    if (parent.right != null && parent.right == removeNode) {
                        parent.right = removeNode.left;
                    }
                } else {
                    //为根节点
                    this.root = removeNode.right;
                    this.root.parent = null;
                }
            }
            //case3:有两个子节点
            else {
                //找右子树的最左节点
                Node rightNode = removeNode.right;
                while (rightNode.left != null) {
                    rightNode = rightNode.left;
                }
                rightNode.parent.left = null;//删除最左节点
                if (removeNode.parent.left == removeNode) {//如果删除节点为左节点
                    removeNode.parent.left = rightNode;
                    rightNode.left = removeNode.left;
                    rightNode.right = removeNode.right;
                } else {
                    removeNode.parent.right = rightNode;
                    rightNode.left = removeNode.left;
                    rightNode.right = removeNode.right;
                }
            }

        }

        size--;
    }

    /**
     * 二叉搜索树的最大深度
     * BFS 计算出队列的次数
     *
     * @return
     */
    public int maxDepth() {
        return deptha(this.root);
    }

    private int deptha(Node root) {
        int m;
        int n;
        if (root == null) {
            return 0;
        } else {
            m = deptha(root.left);
            n = deptha(root.right);
        }

        return (m > n ? m : n) + 1;
    }

    /**
     * 二叉树的最小深度
     * @return
     */
    public int minDepth() {
        return depthi(this.root);
    }

    private int depthi(Node root) {

        int m;
        int n;
        if (root == null) {
            return 0;
        } else {
            m = depthi(root.left);
            n = depthi(root.right);
        }

        return (m > n ? n : m) + 1;
    }


    /**
     * 深度优先遍历
     * 递归实现
     * 根节点->左节点->右节点
     * 深度优先遍历是从上上往下进行遍历，直到叶子节点
     * 前序遍历的的顺序与深度优先遍历相同，但是是从叶子节点往根节点进行遍历
     */
    public void DFS(){
        List<T> list = new ArrayList<>();
        DFS(this.root,list);
        if(list.size() > 0){

            System.out.println(Arrays.toString(list.toArray()));
        }else {
            System.out.println("null");
        }
    }


    private void DFS(Node node,List<T > list){

        if(node != null){
            list.add((T) node.value);
            DFS(node.left,list);
            DFS(node.right,list);
        }

    }

    /**
     * 深度优先遍历
     * 非递归方式
     *
     */
    public void DFS1(){
        if(this.root == null){
            return;
        }else {
            Node node = new Node();
            Node head = node;
            DFS1(node);
            head = head.right;
            System.out.print("[ ");
            while (head!= null){
                System.out.print(head.value+" ");
                head = head.right;
            }
            System.out.print("]");
        }
    }

    /**
     * 广度优优先遍历
     * 从上往下、从左往右进行遍历
     * 当linkedList值最大时，为树的最大深度
     *
     */
    public void BFS(){
        if(this.root == null){
            return;
        }
        Node head = new Node();
        Node node = head;

        LinkedList<Node> linkedList = new LinkedList();
        linkedList.add(root);
        int max = 0;
        while (!linkedList.isEmpty()){
            Node cur = linkedList.removeFirst();
            Node temp = new Node(cur.value);//注意此处需要创建新的节点，否则会改变root结构
            node.right = temp;
            node = node.right;
            if(cur.left != null){
                linkedList.add(cur.left);
            }
            if(cur.right != null){
                linkedList.add(cur.right);
            }
            max++;
        }
        head = head.right;
        while (head!= null){
            System.out.print(head.value+" ");
            head = head.right;
        }
        System.out.print("]");
        System.out.println("\nmaxDepth"+max);
    }



    private void DFS1(Node head){
        Stack<Node> stack = new Stack();
        stack.push(root);
        Node cur ;

        while (!stack.isEmpty()){
            //出栈
            cur = stack.pop();
            Node temp = new Node(cur.value);
            head.right = temp;
            head = head.right;
            if(cur.right != null){
               stack.push(cur.right);
            }
            if(cur.left!= null){
                stack.push(cur.left);
            }
        }

    }


    /**
     * 获取元素数量
     */
    public int size() {
        return size;
    }


    /**
     * 中序遍历
     */
    public void order() {
        if (this.root == null) {
            throw new NullPointerException();
        }
        Node<T> node = this.root;
        order(node);
    }

    private void order(Node<T> node) {
        if (node != null) {
            order(node.left);
            System.out.print(node.value + "  ");
            order(node.right);
        }
    }

    public static void main(String[] args) {

        //int[] arrays = new int[]{109, 2, 3, 4, 9, 12, 41, 346, 54, 13, 21, 654, 7, 5};

        //int[] arrays = new int[]{50,38,60,20,40,52,53,48,54};
        int[] arrays = new int[]{0,1,2,3,4,5,6,7,8};
        BinarySearchTree binarySortTree = new BinarySearchTree();

        for (int i = 0; i < arrays.length; i++) {
            binarySortTree.insert(arrays[i]);
        }
/*

        System.out.println("DFS:");
        binarySortTree.DFS();
        binarySortTree.DFS1();
        System.out.println("\n根节点" + binarySortTree.root.value);
*/

        System.out.println("BFS");
        binarySortTree.BFS();
/*
        System.out.println("maxDepth:" + binarySortTree.maxDepth());
        System.out.println("minDepth:" + binarySortTree.minDepth());

        binarySortTree.order();
        System.out.println("\n========================");
        binarySortTree.remove(60);
        binarySortTree.order();*/


    }


}
