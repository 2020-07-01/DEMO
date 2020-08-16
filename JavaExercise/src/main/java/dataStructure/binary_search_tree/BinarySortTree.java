package dataStructure.binary_search_tree;

/**
 * @author yq
 * @program: BinarySortTree
 * @description: 二叉搜索树的实现
 * @create: 2019-12-28 09:55
 **/
public class BinarySortTree<T extends Comparable> {

    /**
     * 性质：
     * 1、二叉搜索树、二叉排序树、二叉查找树
     * 2、若任意节点的左子树不为空，则左子树上的节点不大于父节点
     * 3、若任意节点的右子树不为空，则右子树的上的节点不小于父节点
     * 4、左子树和右子树也是一颗二叉搜索树
     *
     * 时间复杂度分析：n-logn
     */

    //根节点
    Node<T> root;

    //节点数
    int size;

    BinarySortTree(){

    }


    static class Node<T extends Comparable> {

        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
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
     * 获取元素数量
     */
    public int size(){
        return size;
    }


    /**
     * 中序遍历
     */
    public void order(){
        if(this.root == null){
            throw new NullPointerException();
        }
        Node<T> node = this.root;
        order(node);
    }

    private void order(Node<T> node){
        if(node != null){
            order(node.left);
            System.out.print(node.value + "  ");
            order(node.right);
        }
    }

    public static void main(String[] args) {

        int[] arrays = new int[]{109,2,3,4,9,12,13,41,346,54,13,21,654,7,5};

        BinarySortTree binarySortTree = new BinarySortTree();

        for(int i = 0;i<arrays.length;i++){
            binarySortTree.insert(arrays[i]);
        }

        binarySortTree.order();
    }


}
