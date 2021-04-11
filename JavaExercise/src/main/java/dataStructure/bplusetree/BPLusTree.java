package dataStructure.bplusetree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName : BPLusTree
 * @Author : yq
 * @Date: 2021-04-11
 * @Description : B+ tree
 */
public class BPLusTree<K extends Comparable<? super K>, V> {

    /**
     * B+Tree的分支因子
     */
    private int branchingFactor;

    /**
     * 默认分支因子
     */
    private static final int DEFAULT_BRANCHING_FACTOR = 128;
    /**
     * 根节点
     * 初始化时根节点为叶子节点
     * 第一次拆分后，根节点变为索引节点
     */
    private Node root;

    public BPLusTree() {
        this(DEFAULT_BRANCHING_FACTOR);
    }

    public BPLusTree(int branchingFactor) {
        if (branchingFactor < 2) {
            throw new IllegalArgumentException("Illegal branching factor: " + branchingFactor);
        }
        this.branchingFactor = branchingFactor;
        root = new LeafNode();
    }


    /**
     * 检索
     * 从根节点开始
     *
     * @param key
     * @return
     */
    public V search(K key) {
        return root.getValue(key);
    }


    /**
     * 添加节点
     *
     * @param ket
     * @param value
     */
    public void insert(K ket, V value) {
        root.insertValue(ket, value);
    }

    private abstract class Node {

        List<K> keys;

        int keyNumber() {
            return keys.size();
        }

        abstract V getValue(K key);

        abstract void insertValue(K key, V value);

        abstract K getFirstLeafKey();

        abstract boolean isOverflow();

        abstract boolean isUnderflow();

        abstract Node split();

        public String toString() {
            return keys.toString();
        }
    }


    /**
     * 内部节点
     */
    private class InternalNode extends Node {

        /**
         * 子节点
         */
        List<Node> children;


        InternalNode() {
            this.keys = new ArrayList<K>();
            this.children = new ArrayList<Node>();
        }

        @Override
        V getValue(K key) {
            return getChild(key).getValue(key);
        }

        /**
         * 索引节点的存储
         *
         * @param key
         * @param value
         */
        @Override
        void insertValue(K key, V value) {
            Node child = getChild(key);
            child.insertValue(key, value);
            if (child.isOverflow()) {
                //索引节点拆分
                Node sibling = child.split();
                insertChild(sibling.getFirstLeafKey(), sibling);
            }
            //根节点进行拆分
            if (root.isOverflow()) {
                Node siblling = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(siblling.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(siblling);
                root = newRoot;
            }
        }

        @Override
        K getFirstLeafKey() {
            return children.get(0).getFirstLeafKey();
        }

        @Override
        boolean isOverflow() {
            return children.size() > branchingFactor;
        }

        @Override
        boolean isUnderflow() {
            return children.size() < (branchingFactor + 1) / 2;
        }

        @Override
        Node split() {
            int from = keyNumber() / 2 + 1, to = keyNumber();
            InternalNode sibling = new InternalNode();
            sibling.keys.addAll(keys.subList(from, to));
            sibling.children.addAll(children.subList(from, to + 1));

            keys.subList(from - 1, to).clear();
            children.subList(from, to + 1).clear();

            return sibling;
        }

        /**
         * 获取子节点
         *
         * @return
         */
        Node getChild(K key) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            return children.get(childIndex);
        }

        void insertChild(K key, Node child) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            if (loc >= 0) {
                children.set(childIndex, child);
            } else {
                //维护根节点
                keys.add(childIndex, key);
                children.add(childIndex + 1, child);
            }
        }
    }

    /**
     * 叶子节点
     * 叶子节点相当于一个单向列表
     */
    private class LeafNode extends Node {

        //叶子节点存储的值
        List<V> values;
        //下一个节点
        LeafNode next;

        LeafNode() {
            keys = new ArrayList<>();
            values = new ArrayList<>();
        }

        @Override
        V getValue(K key) {
            return null;
        }

        /**
         * 存储值
         * 根节点为叶子节点的头节点
         * 每次存储值从根节点开始进行存储
         *
         * @param key
         * @param value
         */
        @Override
        void insertValue(K key, V value) {
            //返回值含义特别注意 须看源码 此步骤能保证子节点和值 有序
            int loc = Collections.binarySearch(keys, key);
            int valueIndex = loc >= 0 ? loc : -loc - 1;
            if (loc >= 0) {
                //替换旧址
                values.set(valueIndex, value);
            } else {
                //存储新值
                keys.add(valueIndex, key);
                values.add(valueIndex, value);
            }

            //键数量超过指定值 拆分
            if (root.isOverflow()) {
                Node sibling = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(sibling.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(sibling);
                root = newRoot;
            }
        }

        @Override
        K getFirstLeafKey() {
            return keys.get(0);
        }

        /**
         * 每个叶子节点最多有m-1个键
         *
         * @return
         */
        @Override
        boolean isOverflow() {
            return values.size() > branchingFactor - 1;
        }

        @Override
        boolean isUnderflow() {
            return false;
        }

        @Override
        Node split() {
            //兄弟节点
            LeafNode sibling = new LeafNode();
            int from = (keyNumber() + 1) / 2, to = keyNumber();
            //后半部分
            sibling.keys.addAll(keys.subList(from, to));//
            sibling.values.addAll(values.subList(from, to));

            keys.subList(from, to).clear();
            values.subList(from, to).clear();

            //单链表结构
            sibling.next = next;
            next = sibling;
            return sibling;
        }
    }


    public static void main(String[] args) {

        BPLusTree bpLusTree = new BPLusTree(4);
        bpLusTree.insert(1, 1);
        bpLusTree.insert(2, 2);
        bpLusTree.insert(3, 3);
        bpLusTree.insert(4, 4);
        bpLusTree.insert(5, 5);
        bpLusTree.insert(6, 6);
        bpLusTree.insert(7, 7);
        bpLusTree.insert(8, 8);
        bpLusTree.insert(9, 9);
        bpLusTree.insert(10, 10);
        bpLusTree.insert(11, 11);
        bpLusTree.insert(12, 12);

        System.out.println(bpLusTree.root);

    }
}



