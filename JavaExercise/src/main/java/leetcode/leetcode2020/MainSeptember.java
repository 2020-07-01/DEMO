package leetcode.leetcode2020;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName : MainSeptember
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class MainSeptember {

    /**
     * 剑指 Offer 18. 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            head = head.next;
            return head;
        }

        ListNode node = new ListNode(head.val);
        node = head;

        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
                return head;
            }
            node = node.next;
        }
        return head;

    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * 递归求解
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode1(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }

        head.next = deleteNode1(head.next, val);
        return head;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * 双指针法求解
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode2(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = new ListNode(head.val);
        ListNode cur = new ListNode(head.val);
        pre = head;
        cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return head;
    }

    int max = 0;
    int cur = 0;


    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * max 为左子树深度和右子树深度的最大值+1
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    public void DFS(TreeNode treeNode) {
        if (treeNode != null) {
            cur++;
            DFS(treeNode.left);
            DFS(treeNode.right);
        } else {
            max = max > cur ? max : cur;
            cur = 0;
        }
    }


    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * 空间复杂度 O(1)
     * 时间复杂度 O(n)
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        //获取位数不相同的数
        int A = 0;
        int B = 0;
        int C = num & (-num);
        for (int i = 0; i < nums.length; i++) {
            if ((C & nums[i]) == 1) {
                A = A ^ nums[i];
            } else {
                B = B ^ nums[i];
            }
        }
        return new int[]{A, B};
    }


    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 空间复杂度为O(n)
     * 时间复杂度为O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int index = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
                if (hashMap.get(nums[i]) > count) {
                    count = hashMap.get(nums[i]);
                    index = i;
                }
            } else {
                hashMap.put(nums[i], 1);
            }
        }

        return nums[index];
    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 时间复杂度为O(n)
     * 空间复杂度为O(1)
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        /**
         * 摩尔投票法
         * 在众多的候选人中，选出票数最多的那个
         * 算法过程：
         * 1、先确定第一个数
         * 2、遍历数组，进行抵消，如果相同则count+1,否则减1
         * 3、如果count减为0，则当前数字为major，初始化count=1
         */

        int count = 1;
        int major = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (major == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    major = nums[i];
                    count = 1;
                }
            }
        }
        return major;
    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 排序法 取中间值
     * 快速排序
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        quickSort(nums, left, right);
        System.out.println(Arrays.toString(nums));
        return nums[nums.length / 2];

    }

    /**
     * 快速排序
     *
     * @param arrays
     * @param left
     * @param right
     */
    private void quickSort(int[] arrays, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arrays[left];
        int i = left;
        int j = right;
        while (i != j) {
            //先从右往左遍历
            while (arrays[j] >= base && i < j) {
                j--;
            }
            //从左往右遍历
            while (arrays[i] <= base && i < j) {
                i++;
            }
            //交换后再次进行遍历
            if (i < j) {
                int temp = arrays[i];
                arrays[i] = arrays[j];
                arrays[j] = temp;
            }
        }

        base = arrays[i];
        arrays[left] = base;
        quickSort(arrays, i + 1, right);
        quickSort(arrays, left, i - 1);
    }


    /**
     * 面试题 02.03. 删除中间节点
     * 无法理解题意
     *
     * @param node
     */
    public void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * 121. 买卖股票的最佳时机
     * minPrice 记录历史最低点
     * max 记录历史最大收益
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        //当前之前的最低点
        int minPrice = Integer.MAX_VALUE;
        //当前卖出的最大收益
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;

        //暴力解法
        /*//最大收益
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;*/
    }


    /**
     * 121. 买卖股票的最佳时机
     * 暴力解法
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {

        //最大收益
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }


    /**
     * 258. 各位相加
     * 递归法解决
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {

        int result = add(num);

        return result;
    }

    private int add(int num) {
        if (num < 10) {
            return num;
        }
        String string = Integer.toString(num);
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            result = result + ((int) string.charAt(i) - (int) '0');
        }
        return add(result);
    }


    /**
     * 258. 各位相加
     * 循环法解决
     *
     * @param num
     * @return
     */
    public int addDigits1(int num) {
        int temp = num;
        for (; ; ) {
            String string = Integer.toString(temp);
            int result = 0;
            for (int i = 0; i < string.length(); i++) {
                result = result + ((int) string.charAt(i) - (int) '0');
            }
            temp = result;
            if (result < 10) {
                return result;
            }
        }
    }

    /**
     * 258. 各位相加
     * 时间复杂度 O(1)
     *
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        if (num > 9) {
            num = num % 9;
            if (num == 0) {
                return 9;
            }
        }
        return num;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {

        int[] arrays = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        MainSeptember main = new MainSeptember();
        int i = 10;
        int m = i + '2';

        System.out.println(main.addDigits1(3865));
    }
}
