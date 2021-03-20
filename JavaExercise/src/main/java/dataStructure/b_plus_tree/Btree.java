package dataStructure.b_plus_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: Btree
 * @description: B+树的实现
 * @create: 2019-12-29 14:25
 **/
public class Btree {

    private static final String NODE = "NODE";
    static final String INT = "INT";
    private static final String PRENODE = "PRENODE";
    private static final String NEXTNODE = "NEXTNODE";

    //B+树的阶数，一个节点最多拥有的子节点树
    private int rank;
    //根节点
    private Node root;
    //叶子节点的链表头,维护了一个双向链表
    private Node head;

    Btree(int rank) {
        this.rank = rank;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * 插入一条记录(键值对)
     * 任何非叶子节点最多有M个子节点，且M>2，M为阶数
     *
     * @param entry
     */
    public void insert(KeyAndValue entry) {
        List<KeyAndValue> keyAndValues1 = new ArrayList<>();
        /**
         * 头节点为空
         */
        if (head == null) {
            keyAndValues1.add(entry);
            head = new Node(null, keyAndValues1, null, null, null);
            root = new Node(null, keyAndValues1, null, null, null);
        } else {//如果存在节点
            Node node = head;
            //遍历链表找到插入键值对对应的节点s
            while (node != null) {
                List<KeyAndValue> keyAndValues = node.getKeyAndValues();//获取节点的关键字列表
                int exitFlag = 0;
                //如果要插入的建的值与当前节点的键值对集合中某个键的值相等，则直接进行替换,然后退出循环
                for (KeyAndValue kv : keyAndValues) {
                    if (kv.getValue() == entry.getValue()) {
                        kv.setValue(entry.getValue());
                        exitFlag = 1;
                        break;
                    }
                }

                //如果插入的键已经有值了则直接退出循环
                if (exitFlag == 1) {
                    break;
                }
                //如果当前节点是最后一个节点或者要插入的键值对的键的值小于下一个节点的键的值，则直接进行插入
                if (node.getNextNode() == null || node.getNextNode().getKeyAndValues().get(0).getKey() >= entry.getKey()) {
                    splidNode(node, entry);
                    break;
                }
                //移动指针
                node = node.getNextNode();
            }
        }
    }

    //判断是否需要进行拆分节点
    private void splidNode(Node node, KeyAndValue addKeyAndValue) {
        List<KeyAndValue> keyAndValues = node.getKeyAndValues();

        /*
         * 除根节点之外的每个节点存放至少M/2个关键字，至多存放M个关键字
         * 节点中关键字是按递增顺序进行排序
         *
         * 在添加一个数据后要进行分裂
         */
        if (keyAndValues.size() == rank - 1) {
            //先插入待添加的节点
            keyAndValues.add(addKeyAndValue);
            Collections.sort(keyAndValues);//进行从小到大进行排序
            //取出key-value集合中中间位置的下标
            int mid = keyAndValues.size() / 2;
            //取出原来的key-value集合中中间位置的键
            int midKey = keyAndValues.get(mid).getKey();
            //构造一个新的键值对，不是叶子节点的节点不存储数据
            KeyAndValue midKeyAndValue = new KeyAndValue(midKey, "");
            //将中间位置左边的键值对信息封装为一个新的集合对象
            List<KeyAndValue> leftKeyAndValue = new LinkedList<>();
            for (int i = 0; i < mid; i++) {
                leftKeyAndValue.add(keyAndValues.get(i));
            }
            //将中间位置右边的键值对信息封装为一个新的集合对象
            List<KeyAndValue> rightKeyAndValue = new LinkedList<>();
            /**
             * 如果是叶子节点，则在原节点中保留上移的key-value,否则删除
             */
            int k = 0;
            if (node.isLeaf()) {
                k = mid;
            } else {
                k = mid + 1;
            }
            for (int j = mid; j < keyAndValues.size(); j++) {
                rightKeyAndValue.add(keyAndValues.get(j));
            }
            //对左右两边的元素进行重排序
            Collections.sort(leftKeyAndValue);
            Collections.sort(rightKeyAndValue);
            //以mid为界限将当前节点分成两个节点维护前后指针
            Node rightNode;
            Node leftNode;
            //if (node.isLeaf()) {
            rightNode = new Node(null, rightKeyAndValue, node.getNextNode(), null, node.getParentNode());
            leftNode = new Node(null, leftKeyAndValue, rightNode, node.getPreviousNode(), node.getParentNode());
            rightNode.setPreviousNode(leftNode);
            // }

            //如果当前分裂的节点有孩子节点，设置分裂后的节点与孩子节点的关系
            if (node.getChildren() != null) {
                //获取所有的孩子节点
                List<Node> nodes = node.getChildren();
                List<Node> leftNodes = new ArrayList<>();
                List<Node> rightNodes = new ArrayList<>();

                for (Node childNode : nodes) {
                    int max = childNode.getKeyAndValues().get(childNode.getKeyAndValues().size() - 1).getKey();
                    //小于midKeyAndValue节点的key的节点均属于新产生的左节点的孩子节点
                    if (max < midKeyAndValue.getKey()) {
                        leftNodes.add(childNode);
                        childNode.setParentNode(leftNode);
                    } else {
                        rightNodes.add(childNode);
                        childNode.setParentNode(rightNode);
                    }
                }
                leftNode.setChildren(leftNodes);
                rightNode.setChildren(rightNodes);
            }
            //获取当前节点的前节点
            Node preNode = node.getPreviousNode();
            //分裂节点的后将分裂节点的前节点的后节点设置为左节点
            if (preNode != null) {
                preNode.setNextNode(leftNode);
            }
            //获取当前节点的后节点
            Node nextNode = node.getNextNode();
            //分解节点的后节点的前节点设置为右节点
            if (nextNode != null) {
                nextNode.setPreviousNode(rightNode);
            }
            //如果是头节点分裂，则分裂后左边的节点为头节点
            if (node == head) {
                head = leftNode;
            }

            //添加父节点的子节点
            List<Node> childNodes = new ArrayList<>();
            childNodes.add(rightNode);
            childNodes.add(leftNode);

            //如果当前节点无父节点
            if (node.getParentNode() == null) {
                List<KeyAndValue> parentKeyAndValues = new ArrayList<>();
                parentKeyAndValues.add(midKeyAndValue);
                //为新生的左右节点构造父节点
                Node parentNode = new Node(childNodes, parentKeyAndValues, null, null, null);
                //将子节点与父节点进行关联
                rightNode.setParentNode(parentNode);
                leftNode.setParentNode(parentNode);
                //设置根节点
                root = parentNode;
            } else {
                Node parentNode = node.getParentNode();
                //将原来孩子节点与新的孩子节点合并，然后与父节点进行关联
                childNodes.addAll(parentNode.getChildren());
                //移除分裂的节点
                childNodes.remove(node);
                //将子节点与父节点进行关联
                parentNode.setChildren(childNodes);
                rightNode.setParentNode(parentNode);
                leftNode.setParentNode(parentNode);
                if (parentNode.getParentNode() == null) {
                    root = parentNode;
                }
                //递归调用进行拆分方法添加父节点,依次递增
                splidNode(parentNode, midKeyAndValue);
            }
        } else {
            //当节点的关键字的个数小于rank-1时
            keyAndValues.add(addKeyAndValue);
            //排序
            Collections.sort(keyAndValues);
        }
    }

    //根据索引进行搜索
    public Object search(int key, Node node, String mode) {
        //如果是叶子节点则直接获取值
        if (node.isLeaf()) {
            List<KeyAndValue> keyAndValues = node.getKeyAndValues();
            for (KeyAndValue keyAndValue : keyAndValues) {
                if (keyAndValue.getKey() == key) {
                    switch (mode) {
                        case NODE:
                            return node;
                        case INT:
                            return keyAndValue.getValue();
                    }
                }
            }
            return null;
        }

        //如果是非叶子节点

        //没写完
        return null;
    }


}
