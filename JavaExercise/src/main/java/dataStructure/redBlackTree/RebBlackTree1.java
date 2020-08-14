package dataStructure.redBlackTree;

/**
 * @author yq
 * @date 2020/8/12 23:34
 * 红黑树 https://www.cnblogs.com/lycroseup/p/7324229.html
 */
public class RebBlackTree1<T extends Comparable<T>> {

    private Node<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    static class Node<T extends Comparable<T>> {
        boolean color;
        T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        Node(T key) {
            this.key = key;
            //默认节点为红色
            this.color = RED;
            //在插入的过程中会进行重新设置
            this.parent = null;
            this.left = null;
            this.right = null;
        }

    }


    /**
     * 红黑树的插入与二叉查找树的插入相同
     * 节点默认为红色，插入节点的过程默认设
     * 插入之后进行红黑树的验证
     * 然后进行左旋和右旋操作
     *
     * @param node
     */
    public void insert(Node<T> node) {
        int cmp;
        Node<T> x = this.root;
        Node<T> y = null;
        //寻找插入的父节点
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        //设置插入的父节点
        node.parent = y;
        /**
         * 判断插入到父节点的左边还是右边
         */
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            //如果父节点为空则此节点为根节点
            this.root = node;
        }
        //修正红黑树
        insertFixUp(node);
    }

    /**
     * 插入节点之后修正红黑树
     * 修正红黑树的过程为从下往上
     *
     * @param node
     */
    private void insertFixUp(Node node) {
        //父节点
        Node<T> parent;
        //爷爷节点
        Node<T> gparent;
        /**
         * 如果父节点不为空，并且父节点为红色
         */
        while (((parent = parentof(node)) != null) && isRed(parentof(node))) {
            gparent = parentof(parent);
            //如果父节点为左孩子
            if (parent == gparent.left) {
                Node<T> uncle = gparent.right;
                //case1:父节点为红色，叔叔的节点为红色
                if ((uncle != null) && isRed(uncle)) {
                    /**
                     * 设置爷爷节点为红色
                     * 设置父节点和叔叔节点为黑色
                     * 当前节点到爷爷节点
                     */
                    setRed(gparent);
                    setBlack(parent);
                    setBlack(uncle);
                    node = gparent;
                    continue;
                }
                //case2:当前节点为右孩子节点，父节点为红色，叔叔节点为黑色
                if (node == parent.right) {
                    /**
                     * 左旋操作
                     */
                    node = parent;
                    leftRotate(parent);
                }
                //case3:当前节点为左孩子节点，父节点为红色，叔叔节点为黑色，

                /**
                 * 右旋操作
                 * 父节点变为黑色，爷爷节点变为红色
                 */
                rightRotate(gparent);
                setRed(gparent);
                setBlack(parent);
            }
            //如果父节点为右孩子
            else {
                Node<T> uncle = gparent.left;
                //case1:当前节点为红色，叔叔节点为红色
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    /**
                     * 右旋
                     */
                    node = parent;
                    rightRotate(parent);
                }
                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                /**
                 * 左旋
                 */
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        /**
         * 如果父节点为空则此节点为根节点
         * 设置为黑色
         */
        setBlack(root);
    }


    /**
     * 以x节点进行左旋操作
     *
     * @param x
     */
    private void leftRotate(Node<T> x) {

        //x节点的右节点
        Node<T> y = x.right;
        //x的右节点为y节点的左节点
        x.right = y.left;
        y.left.parent = x;
        //设置y节点的父节点
        if (x.parent != null) {
            if (x == x.parent.right) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
        } else {
            this.root = y;
        }
    }


    /**
     * 以y节点进行右旋操作
     *
     * @param y
     */
    private void rightRotate(Node<T> y) {

        //x节点的左节点
        Node<T> x = y.left;
        //x节点的左节点为y节点的右节点
        y.left = x.right;
        x.right.parent = y;
        //y节点的父节点
        if (y.parent != null) {
            //如果y节点本来为右节点
            //则父节点的右节点为x
            if (y == y.parent.right) {
                y.parent.right = x;
            } else {
                y.parent.left = x;
            }
        } else {
            this.root = x;
        }
    }


    /**
     * 查找值为key的节点
     *
     * @param key
     * @return
     */
    public Node getNode(T key) {
        Node<T> current = root;
        int cmp;
        while (current != null) {
            cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }


    /**
     * 返回父节点
     *
     * @param node
     * @return
     */
    private Node<T> parentof(Node<T> node) {
        return node != null ? node.parent : null;
    }

    /**
     * 是否为红色节点
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<T> node) {
        return (node != null && node.color == RED) ? true : false;
    }

    /**
     * 是否为黑色节点
     *
     * @return
     */
    private boolean isBlack(Node<T> node) {
        return !isRed(node);
    }

    /**
     * 设置黑色
     *
     * @param node
     */
    private void setBlack(Node<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 设置红色
     *
     * @param node
     */
    private void setRed(Node<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }


    /**
     * 中序遍历红黑树
     *
     * @param node
     */
    private void inOrder(Node<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key + "-" + node.color);
            inOrder(node.right);
        }

    }

    public void inOrder() {
        inOrder(this.root);
    }


    public static void main(String[] args) {

        RebBlackTree1 rebBlackTree1 = new RebBlackTree1();
        Node node = new Node(19);
        rebBlackTree1.insert(node);

        Node node1 = new Node(5);
        rebBlackTree1.insert(node1);

        Node node2 = new Node(30);
        rebBlackTree1.insert(node2);

        Node node3 = new Node(1);
        rebBlackTree1.insert(node3);

        Node node4 = new Node(12);
        rebBlackTree1.insert(node4);

        Node node5 = new Node(35);
        rebBlackTree1.insert(node5);

        Node node6 = new Node(7);
        rebBlackTree1.insert(node6);

        Node node7 = new Node(13);
        rebBlackTree1.insert(node7);

        rebBlackTree1.inOrder();

    }



}










