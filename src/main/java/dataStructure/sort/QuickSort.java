package dataStructure.sort;

public class QuickSort {
	
	//进行一次排序
	public static int partition(int[] array,int low,int high)
	{
		int key = array[low];
		while(low < high) {
			while(array[high] >= key && high > low) {//从后半部分开始扫描
				high--;
			}
			//交换元素值
			array[low] = array[high]; 
			
			while(array[low] <= key && high > low) {//从前半部分开始扫描
				low++;
			}
			
			array[high] = array[low];
		}
		
		array[high] = key;
		
		return high;
	}
	
	//递归进行排序
	public static void sort(int[] array,int low,int high) {
		if(low >= high)
			return ;
		int index = partition(array,low,high);
		//递归排序
		sort(array, low, index-1);
		sort(array, index+1, high);
	}
	
	
	public static void main(String[] args) {
		int[] array = { 125, 34, 45, 43, 2, 5, 14, 65, 32, 123, 90, 6606 };
		sort(array, 0, 11);
		for(int i = 0;i<array.length;i++)
		{
			System.out.print(array[i]+ " ");
		}
	}
}
