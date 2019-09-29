package dataStructure.myLinkedList;


import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author :qiang
 * @date :2019/9/29 下午8:36
 * @description :手写双向链表
 * @other :
 */
public class MyLinkedList<E> implements Link<E> {

    //属性
    private int theSize;//结点数
    private Node<E> headNode;//头结点
    private Node<E> endNode;//尾结点

    //创建一个空的链表
    public MyLinkedList() {
    }

    /**
     * 创建静态内部结点类
     */
    private static class Node<E> {

        public E value;
        public Node<E> previous;
        public Node<E> next;

        //创建一个结点
        public Node(E value, Node previous, Node next) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    /**
     * add方法
     * 默认将元素添加在链表的末尾
     */
    public boolean add(E value) {

        //如果头结点为null,则将新的结点设置为头结点
        if (headNode == null) {
            headNode = new Node(value, null, null);
            endNode = headNode;//此时头结点和尾结点为同一个结点
            theSize++;
            return true;

        } else {//如果头结点不为null,则将结点插在最后一个结点后面
            Node newNode = new Node(value, endNode, null);
            endNode.next = newNode;
            endNode = newNode;
            theSize++;
            return true;
        }
    }

    /**
     * 从头部添加元素
     *
     * @param value
     * @return
     */
    public boolean addFirst(E value) {
        //如果头结点为null则添加在头结点处
        if (headNode == null) {
            headNode = new Node<>(value, null, null);
            endNode = headNode;
            theSize++;
            return true;
        } else {//如果头结点不为null则向前添加
            Node newNode = new Node(value, null, headNode);
            headNode.previous = newNode;
            headNode = newNode;
            theSize++;
            return true;
        }
    }

    /**
     * 将元素添加在指定的位置处
     *
     * @param index
     * @param value
     */
    public boolean add(int index, E value) {

        //LinkedList的下标从0开始
        if (index == size()) {
            add(value);
        }


        return true;

    }


    /**
     * 获取指定位置处的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        //从前往后进行遍历
        if (index <= size() / 2) {

            Node node = headNode;
            int j = 0;
            while (node != null) {
                if (j == index) {
                    return (E) node.value;
                }
                j++;
                node = node.next;
            }

        } else {
            Node node = endNode;
            int j = size() - 1;
            while (node != null) {
                if (j == index) {
                    return (E) node.value;
                }
                j--;
                node = node.previous;
            }

        }

        return null;
    }

    /**
     * 获取元素数量
     *
     * @return
     */
    public int size() {
        return theSize;
    }

    /**
     * 删除指定元素
     *
     * @param value
     * @return
     */
    public boolean remove(E value) {
        //如果链表为空
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node node = headNode;
        while (node != null) {
            if (node.next.value == value) {
                node.next = node.next.next;
                node.next.previous = node;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 遍历集合
     */
    public void iterator() {
        while (headNode != null) {
            System.out.println(headNode.value);
            headNode = headNode.next;

        }
    }

    public static void main(String[] args) {

        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        myLinkedList.addFirst(1);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(3);
        myLinkedList.addFirst(4);


        System.out.println(myLinkedList.get(3));


    }
}
