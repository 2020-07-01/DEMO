package leetcode.leetcode2021;

import basicDataType.Main;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : MainFebruary
 * @Author : yq
 * @Date: 2021-02-19
 * @Description :
 */
public class MainFebruary {

    /**
     * 292. Nim 游戏
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    /**
     * TODO
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {

        int a = 0;
        int b = 0;
        int left = 0;
        int right = piles.length - 1;
        boolean p = true;
        while (left <= right) {
            if (p) {
                if (piles[left] > piles[right]) {
                    a = a + piles[left];
                    left++;
                } else {
                    a = a + piles[right];
                    right--;
                }
                p = false;
            } else {
                if (piles[left] > piles[right]) {
                    b = b + piles[left];
                    left++;
                } else {
                    b = b + piles[right];
                    right--;
                }
                p = true;
            }
        }
        if (a > b) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * TODO
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        return calculate(1, n);
    }

    public int calculate(int low, int high) {

        if (low >= high) {
            return 0;
        }

        int minres = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate(i + 1, high), calculate(low, i - 1));
            minres = Math.min(res, minres);
        }
        return minres;
    }

    /**
     * 1486. 数组异或操作
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = start + 2 * i;
        }
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            sum = sum ^ nums[i];
        }
        return sum;
    }

    public int xorOperation1(int n, int start) {
        int sum = start + 2 * 0;
        for (int i = 1; i < n; i++) {
            sum = sum ^ (start + 2 * i);
        }
        return sum;
    }

    /**
     * 950. 按递增顺序显示卡牌
     * 倒序构造数据
     *
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        Arrays.sort(deck);
        linkedList.add(deck[deck.length - 1]);
        for (int i = deck.length - 2; i >= 0; i--) {
            int first = linkedList.removeFirst();
            linkedList.addLast(first);
            linkedList.addLast(deck[i]);
        }
        int index = 0;
        int[] nums = new int[deck.length];
        for (int i = deck.length - 1; i >= 0; i--) {
            nums[index++] = linkedList.get(i);
        }
        return nums;
    }

    /**
     * 暴力解法
     * 超出时间限制
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray1(int[] nums, int limit) {

        int left = 0;
        int right = 1;
        int maxLength = 1;
        while (left < right && right < nums.length) {

            //获取子数组
            int[] numsTemp = new int[right - left + 1];
            System.arraycopy(nums, left, numsTemp, 0, right - left + 1);
            Arrays.sort(numsTemp);

            if (numsTemp[numsTemp.length - 1] - numsTemp[0] > limit) {
                left++;
                right++;
            } else {
                right++;
                maxLength = Math.max(maxLength, numsTemp.length);
            }

        }
        return maxLength;
    }


    /**
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {

        int left = 0;
        int right = 0;
        int maxLength = 0;

        //维护当前元素为止，左侧元素中的最大值与最小值
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        while (right < nums.length && left < nums.length) {

            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);

            if (maxQueue.peek() - minQueue.peek() <= limit) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                continue;
            }
            maxQueue.remove(nums[left]);
            minQueue.remove(nums[left]);
            left++;
            right++;
        }
        return maxLength;
    }

    /**
     * 766. 托普利茨矩阵
     * 时间复杂度O(M*N)
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {

        //判断最上边
        for (int i = 0; i < matrix[0].length; i++) {

            int index1 = 0;
            int index2 = i;

            while (index1 < matrix.length && index2 < matrix[0].length) {
                if (matrix[0][i] != matrix[index1][index2]) {
                    return false;
                }
                index1++;
                index2++;
            }
        }

        //判断最左边
        for (int i = 0; i < matrix.length; i++) {

            int index1 = i;
            int index2 = 0;

            while (index1 < matrix.length && index2 < matrix[0].length) {
                if (matrix[i][0] != matrix[index1][index2]) {
                    return false;
                }
                index1++;
                index2++;
            }
        }
        return true;
    }

    /**
     * 1052. 爱生气的书店老板
     * 滑动窗口
     * 问题转换
     * 21489
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {

        int n = customers.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
                customers[i] = 0;
            }
        }
        int max = 0, cur = 0;
        for (int i = 0, j = 0; i < n; i++) {
            cur += customers[i];
            if (i - j + 1 > X) cur -= customers[j++];
            max = Math.max(max, cur);
        }
        return ans + max;
    }

    /**
     * 832. 翻转图像
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {

        int length = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length / 2; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][length - j - 1];
                A[i][length - j - 1] = temp;
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = A[i][j] == 0 ? 1 : 0;
            }
        }

        return A;
    }

    /**
     * 832. 翻转图像
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage1(int[][] A) {

        int length = A[0].length;
        if (A[0].length % 2 == 0) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length / 2; j++) {
                    int temp = A[i][j] == 0 ? 1 : 0;
                    A[i][j] = A[i][length - j - 1] == 0 ? 1 : 0;
                    A[i][length - j - 1] = temp;
                }
            }
        } else {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j <= A[i].length / 2; j++) {
                    int temp = A[i][j] == 0 ? 1 : 0;
                    A[i][j] = A[i][length - j - 1] == 0 ? 1 : 0;
                    A[i][length - j - 1] = temp;
                }
            }
        }
        return A;
    }


    /**
     * @param s
     * @return
     */
    public String makeGood(String s) {
        StringBuffer ret = new StringBuffer();
        int retIndex = -1;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ret.length() > 0 && Character.toLowerCase(ret.charAt(retIndex)) == Character.toLowerCase(ch) && ret.charAt(retIndex) != ch) {
                ret.deleteCharAt(retIndex);
                retIndex--;
            } else {
                ret.append(ch);
                retIndex++;
            }
        }
        return ret.toString();

    }


    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 滑动窗口
     * 错误解法
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {

        int maxCount = 0;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>(k);
        String string;
        int index;
        while (right < s.length()) {
            string = s.substring(left, right);
            index = 0;
            hashMap.clear();
            while (index < string.length()) {
                hashMap.put(string.charAt(index), hashMap.getOrDefault(string.charAt(index), 0) + 1);
                index++;
            }
            boolean p = true;
            for (Integer integer : hashMap.values()) {
                if (integer < k) {
                    p = false;
                }
            }
            if (p) {
                maxCount = Math.max(maxCount, string.length());
            } else {
                if (string.length() >= k) {
                    left++;
                }
            }
            right++;
        }
        return maxCount;
    }

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 暴力解法
     * 时间复杂度超时
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring1(String s, int k) {
        int maxCount = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>(k);
        String string;
        int index;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                string = s.substring(j, i);
                index = 0;
                hashMap.clear();
                while (index < string.length()) {
                    hashMap.put(string.charAt(index), hashMap.getOrDefault(string.charAt(index), 0) + 1);
                    index++;
                }
                boolean p = true;
                for (Integer integer : hashMap.values()) {
                    if (integer < k) {
                        p = false;
                    }
                }
                if (p) {
                    maxCount = Math.max(maxCount, string.length());
                }
            }
        }
        return maxCount;
    }

    /**
     * 594. 最长和谐子序列
     * 滑动窗口
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {

        Arrays.sort(nums);
        int maxCount = 0;
        int left = 0;
        int right = 1;
        while (right < nums.length && left <= right) {
            if (nums[right] - nums[left] > 1) {
                left++;
            }
            if (nums[right] - nums[left] == 1) {
                maxCount = Math.max(maxCount, right - left + 1);
            }
            right++;

        }
        return maxCount;
    }

    /**
     * HashMap法解法
     *
     * @param nums
     * @return
     */
    public int findLHS1(int[] nums) {

        int maxCount = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.getOrDefault(nums[i] + 1, 0) == 0) {
                continue;
            }
            maxCount = Math.max(maxCount, hashMap.getOrDefault(nums[i], 0) + hashMap.getOrDefault(nums[i] + 1, 0));
        }

        return maxCount;
    }

    /**
     * 896. 单调数列
     * 一次遍历
     *
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {

        boolean p1 = false;
        boolean p2 = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                p1 = true;
            }
            if (A[i] < A[i - 1]) {
                p2 = true;
            }
        }

        return p1 && p2 == true ? false : true;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> integerList = new LinkedList<>();

        dfs(lists, integerList, nums, target);

        return lists;
    }

    private void dfs(List<List<Integer>> lists, List<Integer> integerList, int[] nums, int targets) {

        if (integerList.size() == 4) {
            //判断值
            int sum = 0;
            for (int i = 0; i < integerList.size(); i++) {
                sum = sum + integerList.get(i);
            }
            if (sum != targets) {
                return;
            }

            //判断重复
            List<Integer> list = new LinkedList<>();
            list.addAll(integerList);
            Collections.sort(list);
            int index = 1;
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> tempInteger = lists.get(i);
                Collections.sort(tempInteger);
                for (int j = 0; j < 4; j++) {
                    if (!tempInteger.get(j).equals(list.get(j))) {
                        index++;
                        break;
                    }
                }
            }

            if(index == lists.size()){
                lists.add(list);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            //不存在则添加
            if (integerList.contains(nums[i])) {
                continue;
            }
            integerList.add(nums[i]);
            dfs(lists, integerList, nums, targets);
            integerList.remove(integerList.size()-1);
        }

    }


    public static void main(String[] args) {


        MainFebruary main = new MainFebruary();

        int[] nums = {1,0,-1,0,-2,2};
        main.fourSum(nums,0);


    }


}
