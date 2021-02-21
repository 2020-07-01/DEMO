package leetcode.leetcode2021;

import basicDataType.Main;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.LinkedList;

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

    public static void main(String[] args) {


        MainFebruary main = new MainFebruary();

        int[] nums = new int[]{3, 2, 10, 4};
        main.stoneGame(nums);

    }


}
