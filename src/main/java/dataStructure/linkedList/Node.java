package dataStructure.linkedList;

public class Node<E> {
	E element;//存储的数据元素
	Node<E> next;//next指的是下一个节点
	public Node(E e) {
		this.element =  e;
	}
}
