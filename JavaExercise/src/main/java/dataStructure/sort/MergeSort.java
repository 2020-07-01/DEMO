package dataStructure.sort;

/**
 * 实现归并排序：
 * 
 * @author qiang
 *
 */
public class MergeSort {

	public static void merge_Sort(int[] list) {
		if (list.length > 1) {
			int[] firsthalf = new int[list.length / 2];
			System.arraycopy(list, 0, firsthalf, 0, list.length / 2);
			merge_Sort(firsthalf);// 对第一部分数组使用递归分离

			int secondhalflength = list.length - list.length / 2;// 后半部分数组的长度
			int[] secondhalf = new int[secondhalflength];// 创建后半部分数组
			System.arraycopy(list, list.length / 2, secondhalf, 0, secondhalflength);
			merge_Sort(secondhalf);// 对第二部分数组使用递归分离

			// 合并两个数组
			merge(firsthalf, secondhalf, list);

		}
	}

	public static void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0;// 数组1的当前下标
		int current2 = 0;// 数组2的当前下标
		int current3 = 0;// 新数组的下标

		while (current1 < list1.length && current2 < list2.length) {
			// 将最小的值存到temp中
			if (list1[current1] < list2[current2]) {
				temp[current3++] = list1[current1++];
			} else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length) {
			temp[current3++] = list1[current1++];
		}

		while (current2 < list2.length) {
			temp[current3++] = list2[current2++];
		}

	}

}
