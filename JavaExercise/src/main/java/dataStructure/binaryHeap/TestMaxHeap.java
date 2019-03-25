package dataStructure.binaryHeap;

/**
 * 测试最大堆
 * @author qiang
 *
 */
public class TestMaxHeap {
	public static void main(String[] args) {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();

		maxHeap.insert(41);
		maxHeap.insert(44);
		maxHeap.insert(490);
		maxHeap.insert(4329);
		maxHeap.insert(4234);
		maxHeap.insert(4332);
		maxHeap.insert(444);

		System.out.println(maxHeap.getSize());

		while (!maxHeap.isEmpty()) {
			System.out.print(maxHeap.remove() + " "); // 从大到小输出
		}

		System.out.println();

		// 创建数组
		Integer[] array = { 43, 54, 656, 534, 53, 756, 8, 234, 123, 6547, 0 };

		MaxHeap<Integer> maxHeap1 = new MaxHeap<>(array);
		System.out.println(maxHeap1.getSize());

		while (!maxHeap1.isEmpty()) {
			System.out.print(maxHeap1.remove() + " "); // 从大到小输出
		}

	}

}
