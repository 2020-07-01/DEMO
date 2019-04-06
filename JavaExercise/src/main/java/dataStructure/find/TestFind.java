package dataStructure.find;

import javax.sql.rowset.CachedRowSet;

mport java.util.Scanner;


public class TestFind {
	/*
	 * 顺序查找：时间复杂度为o(n)
	 */

	public static int orderFind(int[] array, int m) {
		int p = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == m) {
				p = i;
				break;
			}
			if (i == array.length - 1) {
				return -1;
			}
		}
		return p;

	}

	/*
	 * 二分法查找：定位索引的位置 顺序表必须是有序的 二分法查找也叫做折半查找 时间复杂度O(log2n)
	 */
	public static int binarysearch(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;

		while (left < right) {
			int mid = (left + right) / 2;
			if (array[mid] < key) {
				left = mid + 1;
			} else if (array[mid] > key) {
				right = mid - 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();

		String[] Arrays = str.split(",");

		int[] a = new int[Arrays.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(Arrays[i]); // 将String型转化成int型
		}

		/*
		 * System.out.println("测试顺序查找："); int p = orderFind(a,1);
		 * System.out.println("关键字下标为："+p);
		 */
		System.out.println("测试二分法查找：");
		int p = binarysearch(a, 8);
		System.out.println("关键字下标为：" + p);
		
	 
		
		

	}
}
