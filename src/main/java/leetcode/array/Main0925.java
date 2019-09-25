package leetcode.array;


/**
 * @Author: qiang
 * @Description:
 * @other:
 * @Date: 2019/9/25 19:31
 */
public class Main0925 {


	/*
	 *给定一个整数数组 nums 和一个目标值 target，
	 *请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
	 */
	public static int[] twoSum(int[] nums, int target) {

		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					int[] temp = {i, j};
					return temp;
				}
			}
		}
		return new int[]{-1, -1};
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 43, 41, 21};
		int[] temp = twoSum(array, 9);
		System.out.println("[" + temp[0] + "," + temp[1] + "]");
	}
}
