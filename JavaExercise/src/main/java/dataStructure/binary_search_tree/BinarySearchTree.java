package dataStructure.binary_search_tree;

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
     *
     * 时间复杂度分析：logn
     */

    //根节点
    private Node<T> root;
    //节点数
    private int size;


    BinarySearchTree(){

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
     * @param value
     */
    public Node getNode(T value){
        if(this.root == null){//根节点为空
            return null;
        }

        Node cur = this.root;
        int cmp = value.compareTo(cur.value);
        while (cmp != 0){
            if(cmp < 0){
                cur = cur.left;
            }else {
                cur = cur.right;
            }
            if(cur == null){
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
    public void remove(T value){
        //value为空
        if(value == null){
            throw new NullPointerException();
        }
        //节点为空
        if(root == null){
            return;
        }

        Node removeNode = getNode(value);
        if(removeNode !=null){

            //case1:没有任何子节点
            if(removeNode.left == null && removeNode.right == null){
                Node parent = removeNode.parent;
                if(parent != null){
                    if(parent.left != null && parent.left == removeNode){
                        parent.left = null;
                    }
                    if(parent.right != null && parent.right == removeNode){
                        parent.right = null;
                    }
                }else {
                    //为根节点
                    this.root = null;
                }

            }
            //case2:只有一个子节点
            else if(removeNode.left != null && removeNode.right == null){//左边不为空
                Node parent = removeNode.parent;
                if(parent != null){
                    if(parent.left != null && parent.left == removeNode){
                        parent.left = removeNode.left;
                    }
                    if(parent.right != null && parent.right == removeNode){
                        parent.right = removeNode.left;
                    }
                }else {
                    this.root = removeNode.left;
                    this.root.parent = null;
                }

            }
            else if(removeNode.left == null && removeNode.right != null){//右边不为空
                Node parent = removeNode.parent;
                if(parent != null){
                    if(parent.left != null && parent.left == removeNode){
                        parent.left = removeNode.left;
                    }
                    if(parent.right != null && parent.right == removeNode){
                        parent.right = removeNode.left;
                    }
                }else {
                    //为根节点
                    this.root = removeNode.right;
                    this.root.parent = null;
                }
            }
            //case3:有两个子节点
            else {
                //找右子树的最左节点
                Node rightNode = removeNode.right;
                while (rightNode.left != null){
                    rightNode = rightNode.left;
                }
                rightNode.parent.left = null;//删除最左节点
                if(removeNode.parent.left == removeNode){//如果删除节点为左节点
                    removeNode.parent.left = rightNode;
                    rightNode.left = removeNode.left;
                    rightNode.right = removeNode.right;
                }else {
                    removeNode.parent.right = rightNode;
                    rightNode.left = removeNode.left;
                    rightNode.right = removeNode.right;
                }
            }

        }

        size--;
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

        //int[] arrays = new int[]{109,2,3,4,9,12,41,346,54,13,21,654,7,5};

        int[] arrays = new int[]{50,38,60,20,40,52,53,48,54};
        BinarySearchTree binarySortTree = new BinarySearchTree();

        for(int i = 0;i<arrays.length;i++){
            binarySortTree.insert(arrays[i]);
        }

        System.out.println("根节点"+binarySortTree.root.value);
        binarySortTree.order();
        System.out.println("\n========================");
        binarySortTree.remove(60);
        binarySortTree.order();
    }


}
