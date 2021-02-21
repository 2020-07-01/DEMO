package leetcode.leetcode2021;

import basicDataType.Main;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

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


    public static void main(String[] args) {


        MainFebruary main = new MainFebruary();

        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        int[] nums1 = new int[3];

        System.arraycopy(nums, 1, nums1, 0, 1);
        System.out.println(Arrays.toString(nums1));


        System.out.println(main.longestSubarray(nums, 10));

    }


}
