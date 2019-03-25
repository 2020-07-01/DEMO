package dataStructure.sort;

public class Sort {
	/*
	 * 输出排序序后的算法
	 */
	public void print(int[] list) {
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}

	/*
	 * 插入排序算法：每次将新的元素插入到排好序的线性表当中，直到线性表排好序 ，排序的结果为：升序
	 */
	public void insertSort(int[] list) {
		for (int i = 1; i < list.length; i++) {
			int currentElement = list[i];
			int k;
			for (k = i - 1; k >= 0 && list[k] > currentElement; k--)
				list[k + 1] = list[k];

			list[k + 1] = currentElement;
		}
	}

	/*
	 * 冒泡排序：将最大值往前移动
	 *  
	 */
	public void bubbleSort1(int[] list) {
		int temp; // 记录临时中间值
		int size = list.length; // 数组大小
		for (int i = 0; i < size - 1; i++)//遍历次数
		{
			for (int j = i+1; j < size ; j++) {//每次循环以后将最大的元素移到前面
				if (list[i] < list[j]) {// 如果后面的元素大于前面的元素，则互换
					temp = list[j];
					list[j] = list[i];
					list[i] = temp;
				}
			}
		}
	}
	/*
	 * 冒泡排序：将最小值往前移动
	 */
	 public void bubbleSort2(int[] list) {
		 int temp;//记录林临时的中间值
		 int size = list.length;//数组大小
		 for(int i = 0;i < size-1;i++)//
		 {
			 for(int j = i+1;j < size;j++)
			 {
				if (list[j] < list[i]) 
				{
					temp = list[j];
					list[j] = list[i];
					list[i] = temp;
				}
			}
		 }
	 }
	 
	 /*
	  * 归并排序
	  */
	  public void mergeSort(int[] list) {
		  //调用MergeSort类中的静态方法
		 MergeSort.merge_Sort(list); 
	  }
	  
	  /*
	   *快速排序 
	   */
	  public void quickSort(int[] list) {
		  //一般去第一个元素为基准
		  QuickSort.sort(list, 0, list.length-1);
	  }
}
