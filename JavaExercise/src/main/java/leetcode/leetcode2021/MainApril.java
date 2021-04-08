package leetcode.leetcode2021;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName : MainApril
 * @Author : yq
 * @Date: 2021-04-01
 * @Description :
 */
public class MainApril {

    /**
     * 1006. 笨阶乘
     *
     * @param N
     * @return
     */
    public int clumsy(int N) {
        if (N == 1) {
            return 1;
        }
        if (N <= 2) {
            return N * (N - 1);
        }
        String str = "*";
        LinkedList<Integer> linkedList = new LinkedList<>();
        boolean p = false;
        if (N % 2 != 0) {
            p = true;
        }
        int res = N;
        N--;
        while (N > 0) {
            switch (str) {
                case "*":
                    res = res * N;
                    str = "/";
                    break;
                case "/":
                    res = res / N;
                    str = "+";
                    break;
                case "+":
                    str = "*";
                    linkedList.addLast(res);
                    linkedList.addLast(N);
                    res = --N;
                    break;
            }
            if (N == 1) {
                linkedList.addLast(res);
            }
            N--;
        }
        int sum = linkedList.removeFirst();
        p = true;
        for (int i = 0; i < linkedList.size(); i++) {
            if (p) {
                sum = sum + linkedList.get(i);
                p = false;
            } else {
                sum = sum - linkedList.get(i);
                p = true;
            }
        }
        return sum;
    }

    /**
     * 371. 两整数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {

        return Math.addExact(a, b);
    }

    /**
     * 371. 两整数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum1(int a, int b) {
        /**
         * 二进制各位相加相当于做异或操作
         * 计算进位值相当于与操作左移一位
         */
        while (b != 0) {
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }

    /**
     * 328. 奇偶链表
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {

        ListNode node1 = new ListNode(0);
        ListNode n1 = node1.next;

        ListNode node2 = new ListNode(0);
        ListNode n2 = node2.next;

        boolean p = true;
        head = head.next;
        while (head != null) {
            ListNode temp = new ListNode(head.val);
            if (p) {
                n1.next = temp;
                n1 = temp;
                p = false;
            } else {
                n2.next = temp;
                n2 = temp;
                p = true;
            }
            head = head.next;
        }

        n1.next = node2.next.next;

        return node1.next.next;
    }

    /**
     * 334. 递增的三元子序列
     * 不应该是连续的吗
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {

        /*for (int i = 0; i < nums.length - 2) {
            if (nums[i] < nums[i + 1] && nums[i] + 1 < nums[i + 2]) {
                return true;
            }
        }
        return false;*/

        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= max) {
                max = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 326. 3的幂
     * 超时
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1 || n == 3) {
            return true;
        }
        int temp = 3;
        do {
            temp = temp * 3;
        } while (temp < n);

        if (temp == n) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 326. 3的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree1(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 324. 摆动排序 II
     * 逆序 从后往前插入
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {

        Arrays.sort(nums);
        int[] res = new int[nums.length];

        int n = nums.length;
        for (int i = 1; i < nums.length; i += 2) {
            res[i] = nums[--n];
        }
        for (int i = 0; i < nums.length; i += 2) {
            res[i] = nums[--n];
        }

        System.arraycopy(res, 0, nums, 0, nums.length);
    }

    /**
     * 81. 搜索旋转排序数组 II
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public boolean search11(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] == target || nums[end] == target) {
                return true;
            }
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[start] < target && nums[mid] > target) {
                end = mid - 1;
            } else if (nums[end] > target && nums[mid] < target) {
                start = mid + 1;
            } else {
                start++;
            }
        }
        return false;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }



    public static void main(String[] args) {
        MainApril main = new MainApril();
        int[] nums = new int[]{3, 4, 5, 1, 2};
        System.out.println(main.findMin(nums));
    }
}
