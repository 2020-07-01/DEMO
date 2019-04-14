package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: Main0413
 * @Description:TODO
 * @author: caiji
 * @date: 2019年4月12日 上午10:20:16
 */
public class Main0413 {

	/*
	 * @Description：输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的 
	 * @param array
	 * @param sum
	 * @return
	 */
	public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {

		/*
		 *	思路：排序数组的距离越远，乘积越小
		 */

		ArrayList<Integer> list = new ArrayList<Integer>();

		int i = 0;
		int j = array.length - 1;
		while (i < j) {
			if (array[i] + array[j] == sum) {
				list.add(array[i]);
				list.add(array[j]);
				return list;
			} else if (array[i] + array[j] > sum) {
				{
					j--;
				}
			} else
				i++;
		}

		return list;
	}

	/*
	 * 
	 * @Description: 1+2+3+... +n
	 * 思路：使用递归
	 * @param n
	 * @return
	 */
	public int Sum_Solution(int n) {
		int a = n;
		if (a >= 1) {
			a = a + Sum_Solution(n - 1);
		}
		return a;
	}

	/*
	 * @Description: 连续子数组中最大的和
	 * @param array
	 * @return
	 */
	public int FindGreatestSumOfSubArray(int[] array) {
		/*
		 * 遍历数组将所有可能小出现的情况计算结果，保存到list中，然后进行排序
		 */
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < array.length; i++) {
			int sum = 0;
			for (int j = i; j < array.length; j++) {
				sum = sum + array[j];
				list.add(sum);
			}
		}
		Collections.sort(list);

		return list.get(list.size() - 1);
	}

	/*
	 * @Description:全排列的实现
	 */
	public static void fullSort(int[] arr, int start, int end) {
		//设置递归终止条件
		if (start == end) {
			for (int i : arr) {
			 
				 System.out.print(i);
			}
			 System.out.println();
			 
		}

		for (int i = start; i <= end; i++) {
			swap(arr, i, start);
			fullSort(arr, start + 1, end); //固定好当前一位，继续排列后面的
			swap(arr, i, start);
		}
	}

	/**
	 *交换位置
	 */
	private static void swap(int[] str, int i, int first) {
		int tmp;
		tmp = str[first];
		str[first] = str[i];
		str[i] = tmp;
	}
	
	/*
	 * @Description:三个数组成的最小值 
	 * @param numbers
	 * @return
	 */
	 public String PrintMinNumber(int [] numbers) {
		 
		 /*
		  * 先进行全排列，然后选出最小值
		  */
		 //进行全排列
		 fullSort(numbers, 0, numbers.length-1);
		 return null;

	    }
	 
	 
	 public String printMinNuber(int[] numbers) {
		 
		 /*
		  *  	将整型数组转换为字符串数组
		  */
		 int len = numbers.length;
	        String[] string = new String[len];
	        StringBuilder sb = new StringBuilder();
		 
		 for(int i = 0;i<numbers.length;i++)
		 {
			 string[i] = String.valueOf(numbers[i]);//返回字符串的表现形式
		 }
		 //进行排列
		 Arrays.sort(string,new Comparator<String>(){
	            @Override
	            public int compare(String s1, String s2) {
	                String c1 = s1 + s2;
	                String c2 = s2 + s1;
	                
	                return c1.compareTo(c2);
	            }
	        });
		 
		 for(int i = 0; i < len; i++){
	            sb.append(string[i]);
	        }
		 
	        return sb.toString();
		 
	 }
	 
	 
	 public static  void testCompare() {
	        List<String> list = new ArrayList<>();
	        list.add("java");
	        list.add("php");
	        list.add("c++");
	        System.out.println("排序前-->" + list);

	        Comparator<String> comparator = new Comparator<String>() {
	            @Override
	            public int compare(String s1, String s2) {
	                return s1.compareTo(s2);
	            }
	        };

	        Collections.sort(list, comparator);

	        System.out.println("排序后-->" + list);
	        Collections.reverse(list);
	        
	        System.out.println("排序后逆序-->" + list);
	    }
 

	public static void main(String[] args) {
		int[] is = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 23 };

		ArrayList<Integer> list = FindNumbersWithSum(is, 7);

		System.out.println(list);

		int[] arr = { 1, 2, 3,3, 4 };
		fullSort(arr, 0, arr.length - 1);
		
		testCompare();

	}
}
