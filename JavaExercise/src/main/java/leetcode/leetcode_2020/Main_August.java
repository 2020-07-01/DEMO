package leetcode_2020;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author yq
 * @date 2020/8/10 19:51
 */
public class Main_August {


    /**
     * 数组中重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                return nums[i];
            } else {
                hashMap.put(nums[i], nums[i]);
            }

        }
        return -1;
    }

    /**
     * 数组中重复的数字
     * 下标为i的数字应该为i
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber1(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                } else {
                    int temp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return -1;
    }

    /**
     * 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0) {
            return false;
        }

        if (matrix.length == 1 && matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[0][0] > target || target > matrix[row - 1][col - 1]) {
            return false;
        }

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] <= target && matrix[i][col - 1] >= target) {
                for (int k = 0; k < col; k++) {
                    if (matrix[i][k] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null) {
            return null;
        }
        if (!s.contains(" ")) {
            return s;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                stringBuffer = stringBuffer.append("%20");
            } else {
                stringBuffer = stringBuffer.append(s.charAt(i));
            }
            i++;
        }

        return stringBuffer.toString();
    }


    /**
     * 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace1(String s) {
        s.replace(" ", "%20");
        return s;
    }

    /**
     * 回文数
     * 不能直接将数字进行反转，否则有可能会出现数值越界
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {

        String string = Integer.toString(x);
        int i = 0;
        int size = string.length();
        while (i < string.length() / 2) {
            if (string.charAt(i) != string.charAt(size - i - 1)) {
                return false;
            }
            i++;
        }
        return true;
    }


    /**
     * 回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {

        String oldString = new StringBuffer(Integer.toString(x)).toString();
        String newString = new StringBuffer(oldString).reverse().toString();
        if (oldString.equals(newString)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 回文数
     * 数学方法
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        int p = x;
        int cur = 0;
        while (p != 0) {
            cur = cur * 10 + p % 10;
            p = p / 10;
        }

        return cur == x;
    }

    /**
     * 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {

        String string = s + s;
        return string.substring(n, s.length() + n);
    }


    /**
     * 合并两个排序的链表合并两个排序的链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode root = l1.val > l2.val ? new ListNode(l1.val) : new ListNode(l2.val);
        ListNode p = root;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = l2;
                l2 = l2.next;
            }
        }
        p.next = l1 != null ? l1 : l2;

        return root;
    }

    /**
     * 和为s的两个数字
     *
     * @param nums   递增排序的数组
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int k = i + 1; k < nums.length; k++) {
                if (nums[i] + nums[k] == target) {
                    result[0] = nums[i];
                    result[1] = nums[k];
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 和为s的两个数字
     *
     * @param nums   递增排序的数组
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        if(nums == null || nums.length == 0){
            return result;
        }
        HashMap<Integer,Integer> hashMap = new HashMap();
        for(int i = 0;i<nums.length;i++){
            if(hashMap.get(target - nums[i]) != null){
                 result[0] = nums[i];
                 result[1] = target-nums[i];
                 return result;
            }else {
                hashMap.put(target-nums[i],target-nums[i]);
            }
        }
        return result;
    }

    /**
     * 和为s的两个数字
     * 双指针法
     * @param nums   递增排序的数组
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            if(nums[left] + nums[right] == target){
                return new int[]{nums[left],nums[right]};
            }else if(nums[left] + nums[right] > target){
                right--;
            }else {
                left++;
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        Main_August main_august = new Main_August();
        //int[][] mums = new int[][]{{1,   4,  7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24},{18, 21, 23, 26, 30}};
        int[][] nums = new int[0][0];


        System.out.println(main_august.reverseLeftWords("lrloseumgh", 6));

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
