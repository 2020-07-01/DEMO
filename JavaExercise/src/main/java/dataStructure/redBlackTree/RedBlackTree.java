package dataStructure.redBlackTree;

/**
 * @author yq
 * @date 2020/8/12 22:22
 * 红黑树
 */
public class RedBlackTree<k extends Comparable,v> {

    private final static boolean RED = false;
    private final static boolean BLACK = true;
    Node<k,v> root;

    private static class Node<k extends Comparable,v>{
        k key;
        v value;
        boolean color;
        Node<k,v> left;
        Node<k,v> right;
        Node<k,v> parent;

        Node(k key,v value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = RED;
        }

        Node(k key,v value,Node<k,v> parent){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = parent;
            this.color = RED;
        }
    }

    /**
     * 插入元素
     * treeMap中key不能为null
     * @param key
     * @param value
     */
    public void put(k key,v value){
        Node<k,v> x = this.root;
        Node<k,v> parent = null;
        int cmp ;
        while (x != null){
            parent = x;
            cmp = key.compareTo(x.key);
            if(cmp < 0){
                x = x.left;
            }else {
                x = x.right;
            }
        }

        //创建新的元素
        Node<k,v> newNode = new Node<>(key,value,parent);

        if(parent != null){
            //比较大小确认插入左边还是右边
            cmp = key.compareTo(parent.key);
            if(cmp < 0){
                parent.left = newNode;
            }else {
                parent.right = newNode;
            }
        }else {
            //如果parent为空则设置为根元素
            this.root = newNode;
        }
        //插入元素后进行修正
        fixAfterInsertion(newNode);
    }


    /**
     * 插入元素之后进行修正
     * @param node
     */
    private void fixAfterInsertion(Node<k,v> node){

        Node<k,v> parent;
        Node<k,v> gParent;

        //如果当前节点不为null，父节点不为null，并且父节点为红色
        while (node != null && (parent = parentOf(node)) != null &&  parent.color == false){
            //判断当前父节点为左节点
            if(parent == parentOf(parentOf(node)).left){
                Node<k,v> uncle = parentOf(parentOf(node)).right;
                /**
                 * case1：父节点为红色，叔叔节点为红色
                 * 设置父节点和叔叔节点为黑色，设置爷爷节点为红色，当前当前节点移动到爷爷节点
                 */
                if(uncle.color == false){
                    setColor(parent,BLACK);
                    setColor(uncle,BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                    continue;
                }else {
                    //case2：左右情况，先左旋再进行右旋
                    if (node == parentOf(node).right){
                        rotateLeft(parentOf(node));
                    }
                    //case:左左情，以爷爷节点进行右旋
                    rotateRight(parentOf(parentOf(node)));
                    setColor(parentOf(node),BLACK);
                    setColor(parentOf(parentOf(node)),RED);
                }
            }
            //父节点为右节点
            else {
                Node<k,v> uncle = parentOf(parentOf(node)).left;
                if(uncle.color == RED){
                    //case3：变颜色
                    setColor(parentOf(node),BLACK);
                    setColor(parentOf(node),BLACK);
                    setColor(parentOf(parentOf(node)),RED);
                    node = parentOf(parentOf(node));
                }
                else {
                    //case4: 右左情况，先右旋，再左旋
                    if(node == parentOf(node).left){
                        rotateRight(node);
                    }

                    rotateLeft(parentOf(parentOf(node)));
                    setColor(parentOf(node),BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                }
            }
        }        setColor(this.root,BLACK);
    }

    //左旋操作
    private void rotateLeft(Node<k,v> node){

        Node<k,v> x = node.right;
        x.left = node;
        x.parent = node.parent;
        node.right = x.left;

        if(node.parent != null) {
            int cmp = x.key.compareTo(node.parent.key);
            if (cmp < 0) {
                node.parent.left = x;
            } else {
                node.parent.right = x;
            }
        }
        x.parent = null;
        this.root = x;
    }

    //右旋操作
    private void rotateRight(Node<k,v> node){

        Node<k,v> x = node.left;
        x.right = node.right;
        x.parent = node.parent;
        node.left = x.right;

        if(node.parent != null){
            int cmp = x.key.compareTo(node.parent.key);
            if(cmp < 0){
                node.parent.left = x;
            }else {
                node.parent.right = x;
            }
        }

        this.root = x;
        x.parent = null;
    }


    private <k extends Comparable,v> Node<k,v> parentOf(Node<k,v> node){
        return node != null ? node.parent : null;
    }


    private void setColor(Node<k,v> node,boolean color){
        if(node != null){
            node.color = color;
        }
    }

    /**
     * 中序遍历
     */
    private void inOrder(Node<k,v> node){
        if(node != null){
            inOrder(node.left);
            System.out.println(node.key+ " " + node.value+ " " + node.color);
            inOrder(node.right);
        }
    }

    public void inOrder(){
        inOrder(this.root);
    }


    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();


        redBlackTree.put(19,19);
        redBlackTree.put(5,5);
        redBlackTree.put(30,30);
        redBlackTree.put(1,1);
        redBlackTree.put(12,12);
        redBlackTree.put(35,35);
        redBlackTree.put(7,7);
        redBlackTree.put(13,13);


        redBlackTree.inOrder();

    }

}
