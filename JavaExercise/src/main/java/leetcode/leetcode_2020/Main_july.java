package leetcode_2020;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.bind.v2.util.EditDistance;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Main_july {


    /**
     * 买卖股票的最佳时机
     * 贪心算法：从局部最优解达到获取整体最优解
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length <= 1) {
            return 0;
        } else {
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    result = result + (prices[i] - prices[i - 1]);
                }
            }
            return result;
        }
    }


    /* *//**
     * 买卖股票的最佳时机
     * 暴力算法
     * @param prices
     * @return
     *//*
    public int maxProfit1(int[] prices) {
        int result = 0;
        for(int i = 0;i<prices.length-1;i++){
            int max = 0;
            for(int j = i+1;j<prices.length;j++){
                if(prices[j]>prices[i]){
                    if(prices[j] -)
                }
            }
        }
    }*/

    /**
     * 旋转数组
     * 暴力旋转
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (k > 0 && nums.length > 1) {
            for (int p = 0; p < k; p++) {
                int temp = nums[nums.length - 1];
                for (int i = nums.length - 1; i > 0; i--) {
                    nums[i] = nums[i - 1];
                }
                nums[0] = temp;
            }
        }
    }

    /**
     * 存在重复元素
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.get(nums[i]) != null) {
                return true;
            } else {
                hashMap.put(nums[i], nums[i]);
            }
        }
        return false;
    }

    /**
     * 存在重复元素
     * 此种解法耗时比较长
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;

    }


    /**
     * 只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + 1;
            if (digits[i] < 10) {
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;

        return digits;
    }


    /**
     * 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }


    /**
     * 旋转图像
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //换行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }


    /**
     * 验证回文字符串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if ((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 48 && s.charAt(i) <= 57)) {
                stringBuilder.append(s.charAt(i));
            }
        }
        char[] chars = stringBuilder.toString().toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 字符串转整数
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        //去掉空格
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        //第一个非空字符串不为正负号和数字
        if ((str.charAt(0) < 48 || str.charAt(0) > 57) && str.charAt(0) != ' ' && str.charAt(0) != 43 && str.charAt(0) != 45) {
            return 0;
        }
        //处理正负号
        if (str.length() == 1 && (str.charAt(0) == 43 || str.charAt(0) == 45)) {
            return 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (str.charAt(0) >= 49 && str.charAt(0) <= 57) {
            stringBuilder.append(str.charAt(0));
        }

        if (str.charAt(0) == 43 || str.charAt(0) == 45) {
            stringBuilder.append(str.charAt(0));
        }


        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) < 48 || str.charAt(i) > 57) {
                if (stringBuilder.length() == 1 && stringBuilder.toString().equals("+") || stringBuilder.toString().equals("-")) {
                    stringBuilder = new StringBuilder().append(0);
                }
                break;
            }

            if (str.charAt(i) == '0') {
                if (stringBuilder.length() == 1 && stringBuilder.toString().equals("+") || stringBuilder.toString().equals("-")) {
                    continue;
                } else if (stringBuilder.length() == 0) {
                    continue;
                } else {
                    stringBuilder.append(str.charAt(i));
                }
            }

            if (str.charAt(i) >= 49 && str.charAt(i) <= 57) {

                stringBuilder.append(str.charAt(i));
            }

        }
        long parseLong;
        if (stringBuilder.length() > 11) {
            parseLong = Long.parseLong(stringBuilder.toString().substring(0, 12));
        } else if (stringBuilder.length() == 0) {
            parseLong = 0;
        } else {
            parseLong = Long.parseLong(stringBuilder.toString());
        }

        if (parseLong > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (parseLong < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) parseLong;
        }
    }

    /**
     * 一维数组的动态和
     *
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum = sum + nums[j];
            }
            result[i] = sum;
        }
        return result;
    }

    /**
     * 一维数组的动态和
     *
     * @param nums
     * @return
     */
    public int[] runningSum1(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }

    /**
     * 拥有最多糖果的孩子
     *
     * @param candies
     * @param extraCandies
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        ArrayList<Boolean> result = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > max) {
                max = candies[i];
            }
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    /**
     * 重新排列数组
     *
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle(int[] nums, int n) {

        int[] result = new int[nums.length];
        int k = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            result[k++] = nums[i];
            result[k++] = nums[nums.length / 2 + i];
        }
        return result;
    }


    /**
     * 判断能否形成等差数列
     *
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int p = arr[1] - arr[0];
        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i + 1] - arr[i] != p) {
                return false;
            }
        }

        return true;
    }


    /**
     * 计算右侧小于当前元素的个数
     * 暴力算法
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int count = 0;
            for (int k = i + 1; k < nums.length; k++) {
                if (nums[k] < nums[i]) {
                    count++;
                }
            }
            result.add(count);
        }
        if (nums.length > 0) {
            result.add(0);
        }
        return result;
    }

    /**
     * 找到所有数组中消失的数字
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        HashMap<Integer, Boolean> hashMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], true);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!hashMap.containsKey(i)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 找到所有数组中消失的数字
     * 原地修改
     * 时间复杂度为O(2n)
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int newIndex = Math.abs(nums[i]) - 1;
            nums[newIndex] = Math.abs(nums[newIndex]) * -1;
        }

        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }

        return result;
    }


    /**
     * 最大子序和
     * 暴力算法
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (result < sum) {
                result = sum;
            }
            for (int k = i + 1; k < nums.length; k++) {
                sum = sum + nums[k];
                if (result < sum) {
                    result = sum;
                }
            }
        }
        return result;
    }

   /* *//**
     * 斐波那契数列
     * @param
     * @return
     *//*
    public int fib(int n) {
        if(n == 0) {
            return 0;
        }else if(n == 1){
            return 1;
        }else {
            int result = 0;
            int temp1;
            int temp2;
            for(int i = 2;i<n;i++){
                temp1 =
                temp2 =
                result = temp1 + temp2;
            }
            return 0;
        }
    }
*/
    public static void main(String[] args) {

        Main_july main_july = new Main_july();
        int[] nums = {126, 183, 259, 31, 145, 202, 221, 278, 50, 107, 164, 12, 88, -7, 240, 69};
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {-2, 1};



    }

    /**
     * 用两个栈实现队列
     */
    class CQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack();
            stack2 = new Stack();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            int removeElement;
            if(stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            if(stack2.isEmpty()){
                removeElement = -1;
            }else {
                removeElement = stack2.pop();
            }
            return removeElement;
        }
    }


}
