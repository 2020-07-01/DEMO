package leetcode.leetcode2020;

import java.util.*;

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

    /**
     * 234. 回文链表
     * 辅助栈
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            if (!stack.empty() && stack.peek().val == head.val) {
                stack.pop();
            } else {
                stack.push(head);
            }
        }

        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 234. 回文链表
     * 双指针法
     * 1、将链表复制到数组中 时间复杂度为O(n)
     * 2、双指针法遍历链表 时间复杂度O(n/2) 即O(n)
     * <p>
     * O(2n) 即 O(n)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            if (list.get(left).val != list.get(right).val) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 234. 回文链表
     * <p>
     * 快慢指针法
     * 反转后半部分链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        ListNode front = new ListNode(head.next.val);
        ListNode back = new ListNode(head.val);
        //找到前半部分边表的尾部
        while (front.next.next != null) {
            back = back.next;
            front = front.next.next;
        }
        //判断奇偶
        if (front.next != null) {
            back = back.next;
        }
        //反转后半部分链表
        ListNode tail = new ListNode(back.val);
        back = back.next;
        while (back != null) {
            ListNode temp = new ListNode(back.val);
            temp.next = tail;
            tail = temp;
            back = back.next;
        }

        //前后遍历
        while (tail != null) {
            if (head.val != tail.val) {
                return false;
            }
        }
        return true;
    }


    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * 时间复杂度 O(n)
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int i = 0;
        while (i < s.length()) {
            hashMap.put(s.charAt(i), hashMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        i = 0;
        while (i < s.length()) {
            if (hashMap.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }


    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * <p>
     * 有序哈希表
     *
     * @param s
     * @return
     */
    public char firstUniqChar1(String s) {

        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        while (i < s.length()) {
            linkedHashMap.put(s.charAt(i), linkedHashMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        //遍历哈希表
        for (Map.Entry<Character, Integer> entry : linkedHashMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    /**
     * 14. 最长公共前缀
     * 暴力解法
     * 时间复杂度O(m*n*m)
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        String strs0 = strs[0];

        int i = 0;
        while (i < strs0.length()) {

            int j = 0;
            while (j < strs.length) {
                if (strs[j].length() <= i) {
                    return strs0.substring(0, i);
                }
                if (strs0.charAt(i) != strs[i].charAt(j)) {
                    return strs0.substring(0, i);
                }
                j++;
            }

            i++;
        }
        return strs0;
    }

    /**
     * 14. 最长公共前缀
     * 优化
     * 一次遍历数组
     * 时间复杂度O(m*n)
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String strs0 = strs[0];
        String publicString = strs0;
        int i = 1;
        while (i < strs.length) {
            String temp = strs[i];
            //与publicString求公共前缀
            int length = publicString.length() < temp.length() ? publicString.length() : temp.length();
            publicString = publicString.substring(0, length);
            int j = 0;
            while (j < length) {
                if (publicString.charAt(j) != temp.charAt(j)) {
                    publicString = publicString.substring(0, j);
                    break;
                }
                j++;
            }
            i++;
            if (publicString.length() == 0) {
                return "";
            }
        }
        return publicString;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * 排序
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {

        if (arr.length <= k) {
            return arr;
        }

        Arrays.sort(arr);

        int[] result = Arrays.copyOf(arr, k);
        return result;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * 维护最大堆
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        //创建大根堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((v1, v2) -> v2 - v1);
        for (int i = 0; i < arr.length; i++) {
            if (queue.size() < k) {
                queue.add(arr[i]);
            } else if (arr[i] < queue.peek()) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        int index = 0;
        int[] result = new int[k];
        for (Integer num : queue) {
            result[index++] = num;
        }

        return result;
    }


    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 条件：递增排序
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        if (numbers[0] > numbers[numbers.length / 2]) {
            //从前往后找
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    return numbers[i + 1];
                }
            }
            return numbers[numbers.length - 1];
        } else {
            //从后往前找
            for (int i = numbers.length - 1; i > 0; i--) {
                if (numbers[i] < numbers[i - 1]) {
                    return numbers[i];
                }
            }
            return numbers[0];
        }
    }


    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 一次遍历
     * 时间复杂度O(n)
     *
     * @param numbers
     * @return
     */
    public int minArray1(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        int mix = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            mix = mix < numbers[i] ? mix : numbers[i];
        }
        return mix;
    }


    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     *
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {


        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode A = new ListNode(headA.val);
        A = headA;
        ListNode B = new ListNode(headB.val);
        B = headB;

        int aLength = 0;
        int bLength = 0;

        while (A != null) {
            aLength++;
            A = A.next;
        }

        while (B != null) {
            bLength++;
            B = B.next;
        }

        int AB = aLength > bLength ? (aLength - bLength) : (bLength - aLength);
        A = headA;
        B = headB;
        if (aLength > bLength) {
            while (AB != 0) {
                A = A.next;
                AB--;
            }
        } else {
            while (AB != 0) {
                B = B.next;
                AB--;
            }
        }
        //一一比较
        int min = Math.min(aLength,bLength);
        while (min-- > 0){
            if(A == B){
                return A;
            }
            A = A.next;
            B = B.next;
        }
        return null;
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

        int[] arrays = new int[]{3, 4, 3, 5, 1, 2};
        int[] array = new int[10];

        System.out.println(Arrays.toString(array));


        MainSeptember main = new MainSeptember();
        int i = 10;
        int m = i + '2';

        Integer integer = 11;
        Integer integer1 = 11;
int a = 10;
int b = 7;
int c = (a > b) ? (a-b) : (b-a);
        System.out.println(c);
    }
}
