package dataStructure.sort;
 

/**
 * 测试排序算法
 * @author qiang
 *
 */
public class Main {
	public static void main(String[] args) {
		Sort sort = new Sort();
		// 创建数组
		int[] array = { 125, 34, 45, 43, 2, 5, 14, 65, 32, 123, 90, 6606 };
		
		//sort.bubbleSort2(array);
		//sort.insertSort(array);
		//sort.mergeSort(array);
		//sort.bubbleSort1(array);
		//sort.bubbleSort2(array);
		sort.quickSort(array);
		sort.print(array);

	}
}
