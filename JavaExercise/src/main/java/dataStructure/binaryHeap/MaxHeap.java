package dataStructure.binaryHeap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最大堆的实现:父节点的键值总是大于或等于任何一个子节点的键值 使用数组实现MaxHeap
 * 
 * @author qiang
 *
 */
public class MaxHeap<E extends Comparable<E>> {

	private ArrayList<E> data;
	private static final int DEFAULT_CAPACITY = 10;// 默认的大小

	// 构造函数
	public MaxHeap() {
		this.data = new ArrayList<E>(DEFAULT_CAPACITY);// 创建默认大小为10的数组
	}

	public MaxHeap(int capacity) {
		this.data = new ArrayList<E>(capacity);
	}

	/*
	 * 将数组转化为堆右多种方式： 此处使用插入的方式
	 */
	public MaxHeap(E[] array) {
		this.data = new ArrayList<>();
		// 进行插入操作
		for (int i = 0; i < array.length; i++)
			insert(array[i]);

	}

	/*
	 * 添加元素,从数组的0位置开始添加 首先将元素添加到数组的最后，然后进行堆化，即上滤
	 */
	public void insert(E x) {
		data.add(x);// 添加到末尾
		int curIndex = data.size() - 1;// 插入的节点的下标
		shifup(curIndex);
	}

	// 节点上浮
	public void shifup(int curIndex) {
		// 当前节点不是根节点，并且当前节点的值比父节点的值大
		while (curIndex > 0 && (data.get(parent(curIndex)).compareTo(data.get(curIndex)) < 0)) {
			// 交换节点的值
			swop(curIndex, parent(curIndex));
			curIndex = parent(curIndex);
		}
	}

	// 判断堆是否为空
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/*
	 * 删除节点一般从根节点开始，然后将最后一个节点移动到根节点的位置，最后进行节点的下沉，保持堆的结构
	 */
	public E remove() {
		E max = findMax();
		swop(0, getSize() - 1);
		data.remove(data.size() - 1);

		shifdown(0);

		return max;
	}

	// 节点下沉
	public void shifdown(int cur) {
		// liftchild存在
		while (leftChild(cur) < getSize()) {
			int j = leftChild(cur);
			// 如果右节点存在，并且右节点大于左节点的值
			if (rightChild(j) < getSize() && data.get(j).compareTo(data.get(j + 1)) < 0) {
				j = rightChild(cur);
			}
			// 如果当前节点的值大于子节点,则退出
			if (data.get(cur).compareTo(data.get(j)) > 0) {
				break;
			}

			swop(cur, j);
			cur = j;
		}
	}

	// 获取堆元素的数量
	public int getSize() {
		return data.size();
	}

	// 左节点的下标
	public int leftChild(int index) {
		return index * 2 + 1;
	}

	// 右节点的下标
	public int rightChild(int index) {
		return index * 2 + 2;
	}

	// 父节点的下标
	public int parent(int index) {
		return (index - 1) / 2;
	}

	// 取出最大的元素
	public E findMax() {
		if (data.size() == 0)
			throw new IllegalArgumentException("Can not findMax when heap is empty");
		return data.get(0);
	}

	// 交换值
	public void swop(int a, int b) {
		E temp = data.get(a);
		data.set(a, data.get(b));
		data.set(b, temp);
	}
}
