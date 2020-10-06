package dataStructure.find;


import java.util.Scanner;


public class TestFind {
	/*
	 * 椤哄簭鏌ユ壘锛氭椂闂村鏉傚害涓簅(n)
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
	 * 浜屽垎娉曟煡鎵撅細瀹氫綅绱㈠紩鐨勪綅缃� 椤哄簭琛ㄥ繀椤绘槸鏈夊簭鐨� 浜屽垎娉曟煡鎵句篃鍙仛鎶樺崐鏌ユ壘 鏃堕棿澶嶆潅搴(log2n)
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
			a[i] = Integer.parseInt(Arrays[i]); // 灏哠tring鍨嬭浆鍖栨垚int鍨�
		}

		/*
		 * System.out.println("娴嬭瘯椤哄簭鏌ユ壘锛�"); int p = orderFind(a,1);
		 * System.out.println("鍏抽敭瀛椾笅鏍囦负锛�"+p);
		 */
		System.out.println("娴嬭瘯浜屽垎娉曟煡鎵撅細");
		int p = binarysearch(a, 8);
		System.out.println("鍏抽敭瀛椾笅鏍囦负锛�" + p);
		
	 
		
		

	}
}
