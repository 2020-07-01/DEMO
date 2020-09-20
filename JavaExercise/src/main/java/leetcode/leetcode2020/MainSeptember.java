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
        if (headA == null || headB == null) {
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
        int min = Math.min(aLength, bLength);
        while (min-- > 0) {
            if (A == B) {
                return A;
            }
            A = A.next;
            B = B.next;
        }
        return null;
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 每个节点的左子树与右子树相互换掉
     * 时间复杂度为n
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(root.val);
        treeNode = root;
        turn(treeNode);
        return root;
    }

    private void turn(TreeNode treeNode) {
        if (treeNode != null) {
            if (treeNode.left != null && treeNode.right != null) {
                TreeNode temp = treeNode.left;
                treeNode.left = treeNode.right;
                treeNode.right = temp;
            } else if (treeNode.left != null && treeNode.right == null) {
                treeNode.right = treeNode.left;
                treeNode.left = null;
            } else if (treeNode.left == null && treeNode.right != null) {
                treeNode.left = treeNode.right;
                treeNode.right = null;
            }

            turn(treeNode.left);
            turn(treeNode.right);
        }
    }


    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 每个节点的左子树与右子树相互换掉
     * 代码优化
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree1(TreeNode root) {

        if (root == null) {
            return null;
        }
        TreeNode right = mirrorTree1(root.left);
        TreeNode left = mirrorTree1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 迭代法
     * 广度优先遍历法解决
     * 依次弹出节点，将其子节点互换之后再入队列
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree2(TreeNode root) {

        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> queue = new LinkedList();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if (left != null) {
                queue.addFirst(left);
            }
            if (right != null) {
                queue.addFirst(right);
            }
        }
        return root;
    }

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 最近公共祖先
     * 待优化
     * 利用HashMap和Set
     *
     * @param treeNode
     * @param hashMap
     * @param p
     * @param q
     * @param result
     */
    private void commonAncestor(TreeNode treeNode, HashMap<Integer, Set<Integer>> hashMap, TreeNode p, TreeNode q, TreeNode result) {
        if (treeNode != null) {
            commonAncestor(treeNode.left, hashMap, p, q, result);
            commonAncestor(treeNode.right, hashMap, p, q, result);

            //添加
            if (treeNode.left == null && treeNode.right == null) {
                Set<Integer> set = new HashSet<>();
                set.add(treeNode.val);
                hashMap.put(treeNode.val, set);
            } else {
                Set<Integer> leftSet = hashMap.get(treeNode.left.val);
                Set<Integer> rightSet = hashMap.get(treeNode.right.val);
                Set<Integer> set = new HashSet<>();
                if (leftSet != null) {
                    set.addAll(leftSet);
                }
                if (rightSet != null) {
                    set.addAll(rightSet);
                }
                System.out.println(set);
                hashMap.put(treeNode.val, set);
                //判断
                if (set.contains(p.val) && set.contains(q.val)) {
                    result.val = treeNode.val;
                    return;

                }
            }
        }
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * 链表的深拷贝
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            map.put(cur, node);//老节点:新节点
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * 原地修改法
     *
     * @param head
     * @return
     */
    public Node copyRandomList1(Node head) {

        if (head == null) {
            return null;
        }

        Node cur = head;
        //复制链表
        while (cur != null) {
            Node copy = new Node(cur.val);
            Node next = cur.next;
            cur.next = copy;
            copy.next = next;
            cur = cur.next;
        }
        //随机节点复制
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                Node random = new Node(cur.random.val);
                cur.next.random = random;
            }
            cur = cur.next.next;
        }
        //去除原来的节点
        cur = head;
        while (cur != null) {
            Node newNode = cur.next;
            newNode.next = newNode.next.next;
        }
        return head.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    /**
     * 剑指 Offer 38. 字符串的排列
     * 排列组合
     * 回溯法：其中需要进行交换元素，完成回溯
     * 从第一位元素开始，固定元素，其他元素进行交换，递归
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {

        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        ArrayList<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        findPermutation(res, chars, 0);
        return res.toArray(new String[res.size()]);
    }

    private void findPermutation(ArrayList<String> res, char[] chars, int i) {
        //到头 添加
        if (i == chars.length - 1) {
            //此时的chars 已交换过
            res.add(String.valueOf(chars));
            return;
        }

        HashSet<Character> set = new HashSet<>();

        //依次固定每一层，让后面的元素依次进行交换
        for (int x = i; x < chars.length; x++) {
            if (set.contains(chars[x])) {
                continue;
            }
            set.add(chars[x]);
            //进行元素的交换
            swap(chars, x, i);
            /**
             * i = 0时
             * 1，2，3 / 2，1，3 / 3，2，1
             * 到下一层
             */
            findPermutation(res, chars, i + 1);
            /**
             * 此时到第二层 i = 2 固定 2 每次交换后面的元素
             * 1，2，3  / 2，1，3
             * 1，2，3，4   / 2，1，4，3 （如果为4个元素）
             */
            //交换回来，继续下一个元素
            swap(chars, x, i);
        }


    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


    List<String> list = new ArrayList<>();
    //为了让递归函数添加结果方便，定义到函数之外，这样无需带到递归函数的参数列表中
    char[] c;

    //同；但是其赋值依赖c，定义声明分开
    public String[] permutation1(String s) {
        c = s.toCharArray();
        //从第一层开始递归
        dfs(0);
        return list.toArray(new String[list.size()]);
        //将字符串数组ArrayList转化为String类型数组
    }

    private void dfs(int x) {
        //当递归函数到达第三层，就返回，因为此时第二第三个位置已经发生了交换
        if (x == c.length - 1) {
            list.add(String.valueOf(c));//将字符数组转换为字符串
            return;
        }
        //为了防止同一层递归出现重复元素
        HashSet<Character> set = new HashSet<>();
        //这里就很巧妙了,第一层可以是a,b,c那么就有三种情况，这里i = x,正巧dfs(0)，正好i = 0开始
        // 当第二层只有两种情况，dfs(1）i = 1开始
        for (int i = x; i < c.length; i++) {
            //发生剪枝，当包含这个元素的时候，直接跳过
            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            //交换元素，这里很是巧妙，当在第二层dfs(1),x = 1,那么i = 1或者 2， 要不是交换1和1，要不交换1和2
            swap(i, x);
            //进入下一层递归
            dfs(x + 1);
            //返回时交换回来，这样保证到达第1层的时候，一直都是abc。这里捋顺一下，开始一直都是abc，那么第一位置总共就3个位置
            //分别是a与a交换，这个就相当于 x = 0, i = 0;
            //     a与b交换            x = 0, i = 1;
            //     a与c交换            x = 0, i = 2;
            //就相当于上图中开始的三条路径
            //第一个元素固定后，每个引出两条路径,
            //     b与b交换            x = 1, i = 1;
            //     b与c交换            x = 1, i = 2;
            //所以，结合上图，在每条路径上标注上i的值，就会非常容易好理解了
            swap(i, x);
        }
    }

    private void swap(int i, int x) {
        char temp = c[i];
        c[i] = c[x];
        c[x] = temp;
    }


    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 双指针法
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            while (nums[left] % 2 != 0 && left < right) {
                left++;
            }

            while (nums[right] % 2 == 0 && left < right) {
                right--;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            left++;
            right--;
        }
        return nums;
    }


    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 借用外部存储空间
     * @param nums
     * @return
     */

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * 双指针法/滑动窗口
     * <p>
     * 待优化
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {

        //优化
        List<int[]> res = new ArrayList<>();


        List<List<Integer>> lists = new ArrayList<>();
        int left = 1;
        int right = 1;

        while (left <= right && right < target) {
            int[] nums = new int[right - left + 1];
            int temp = 0;
            int index = 0;
            for (int i = left; i <= right; i++) {
                temp = temp + i;
                nums[index++] = i;
            }
            if (temp < target) {
                right++;
            }
            if (temp > target) {
                left++;
            }
            if (temp == target) {
                //List<Integer> list = Arrays.asList(nums);
                res.add(nums);
                //lists.add(list);
                left++;
                right++;
            }
        }

        /*int[][] result = new int[lists.size()][0];
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);
            int[] temp = new int[list.size()];
            for (int j = 0; j< list.size(); j++) {
                temp[j] = list.get(j);
            }
            result[i] = temp;
        }*/
        return res.toArray(new int[0][]);
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * 待优化
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        //存储元素
        List<Integer> list = new ArrayList<>();
        //存储下标
        HashMap<Integer, List<Integer>> hashMap = new HashMap();
        List<Integer> index = new ArrayList<>();
        hashMap.put(0, index);
        right(list, hashMap, matrix, 0, 0);

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    //向右
    public void right(List<Integer> list, HashMap<Integer, List<Integer>> hashMap, int[][] matrix, int index1, int index2) {
        if (index1 < 0 || index1 >= matrix.length) {
            return;
        }
        if (index2 < 0 || index2 >= matrix[index1].length) {
            return;
        }
        if (hashMap.get(index1) != null && hashMap.get(index1).contains(index2)) {
            return;
        }
        int length = matrix[index1].length;
        while (index2 < length) {
            if (hashMap.get(index1) != null) {
                List<Integer> indexs = hashMap.get(index1);
                if (indexs.contains(index2)) {
                    break;
                } else {
                    list.add(matrix[index1][index2]);
                    indexs.add(index2);
                    hashMap.put(index1, indexs);
                }
            } else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(index2);
                hashMap.put(index1, indexs);
                list.add(matrix[index1][index2]);
            }
            index2++;
        }
        down(list, hashMap, matrix, ++index1, --index2);

    }

    //向下
    public void down(List<Integer> list, HashMap<Integer, List<Integer>> hashMap, int[][] matrix, int index1, int index2) {
        if (index1 < 0 || index1 >= matrix.length) {
            return;
        }
        if (index2 < 0 || index2 >= matrix[index1].length) {
            return;
        }
        if (hashMap.get(index1) != null && hashMap.get(index1).contains(index2)) {
            return;
        }
        int length = matrix.length;
        while (index1 < length) {
            if (hashMap.get(index1) != null) {
                List<Integer> indexs = hashMap.get(index1);
                if (indexs.contains(index2)) {
                    break;
                } else {
                    list.add(matrix[index1][index2]);
                    indexs.add(index2);
                    hashMap.put(index1, indexs);
                }
            } else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(index2);
                hashMap.put(index1, indexs);
                list.add(matrix[index1][index2]);
            }
            index1++;
        }
        left(list, hashMap, matrix, --index1, --index2);
    }

    //向左
    public void left(List<Integer> list, HashMap<Integer, List<Integer>> hashMap, int[][] matrix, int index1, int index2) {
        if (index1 < 0 || index1 >= matrix.length) {
            return;
        }
        if (index2 < 0 || index2 >= matrix[index1].length) {
            return;
        }
        if (hashMap.get(index1) != null && hashMap.get(index1).contains(index2)) {
            return;
        }
        while (index2 >= 0) {
            if (hashMap.get(index1) != null) {
                List<Integer> indexs = hashMap.get(index1);
                if (indexs.contains(index2)) {
                    break;
                } else {
                    list.add(matrix[index1][index2]);
                    indexs.add(index2);
                    hashMap.put(index1, indexs);
                }
            } else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(index2);
                hashMap.put(index1, indexs);
                list.add(matrix[index1][index2]);
            }
            index2--;
        }
        up(list, hashMap, matrix, --index1, ++index2);
    }

    //向上
    public void up(List<Integer> list, HashMap<Integer, List<Integer>> hashMap, int[][] matrix, int index1, int index2) {
        if (index1 < 0 || index1 >= matrix.length) {
            return;
        }
        if (index2 < 0 || index2 >= matrix[index1].length) {
            return;
        }
        if (hashMap.get(index1) != null && hashMap.get(index1).contains(index2)) {
            return;
        }
        while (index1 >= 0 && !hashMap.get(index1).contains(index2)) {
            if (hashMap.get(index1) != null) {
                List<Integer> indexs = hashMap.get(index1);
                if (indexs.contains(index2)) {
                    break;
                } else {
                    list.add(matrix[index1][index2]);
                    indexs.add(index2);
                    hashMap.put(index1, indexs);
                }
            } else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(index2);
                hashMap.put(index1, indexs);
                list.add(matrix[index1][index2]);
            }
            index1--;
        }

        right(list, hashMap, matrix, ++index1, ++index2);
    }


    /**
     * 剑指 Offer 49. 丑数
     * 待优化 没理解
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if(n<=0) {
            return 0;//题目中没有规定n一定大于0，当n的输入值不符合要求时，返回0
        }
        int[] ugly = new int[n];
        ugly[0]=1;
        int index2=0,index3=0,index5=0,i =1;
        while(i<n){
            ugly[i] = Math.min(ugly[index2]*2,Math.min(ugly[index3]*3,ugly[index5]*5));
            //丑数之间的间隔不大，每次找到的index2，index3，index5的值都小于i;
            while(ugly[index2]*2<=ugly[i]){index2++;}//注意包括等号
            while(ugly[index3]*3<=ugly[i]){index3++;}
            while(ugly[index5]*5<=ugly[i]){index5++;}
            i++;
        }
        return ugly[n-1];
    }

    //暴力解法，思路清晰，但时间复杂度很高会导致时间超限
    public int nthUglyNumber2(int n) {
        if(n<=0) {
            return 0;
        }
        int num = 0;
        int count = 0;
        while(count<n){
            num++;
            if(isUgly(num)) {
                count++;
            }
        }
        return num;
    }
    public boolean isUgly(int num){
        while(num%2==0){num=num/2;}
        while(num%3==0){num=num/3;}
        while(num%5==0){num=num/5;}
        return num==1?true:false;
    }


    /**
     * 215. 数组中的第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        if(nums == null || nums.length == 0){
            return  0;
        }

        if(nums.length < k){
            return 0;
        }

        Arrays.sort(nums);

        return nums[nums.length-k];


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

        int[][] arrays = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};


        int[][] arrays1 = new int[][]{{4}, {4}, {5}};


        MainSeptember main = new MainSeptember();
        //System.out.println(Arrays.toString(main.spiralOrder(arrays)));
        System.out.println(main.nthUglyNumber(10));
    }
}
