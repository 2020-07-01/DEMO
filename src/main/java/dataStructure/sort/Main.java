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
		sort.insertSort(array);
		for(int i = 0;i<array.length;i++)
			
		{
			System.out.println(array[i]);
		}
	}
}
