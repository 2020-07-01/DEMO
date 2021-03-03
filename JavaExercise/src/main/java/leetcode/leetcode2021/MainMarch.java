package leetcode.leetcode2021;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : MainMarch
 * @Author : yq
 * @Date: 2021-03-01
 * @Description :
 */
public class MainMarch {

    /**
     * 61. 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (k <= 0 || head == null) {
            return head;
        }

        LinkedList<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int startIndex = 0;
        int index = 0;
        if (list.size() >= k) {
            index = list.size() - k;
            startIndex = index;
        } else if (list.size() == 1) {
            index = 0;
            startIndex = 0;
        } else {
            int temp = k / list.size();
            k = k - list.size() * temp;
            index = k == 0 ? k : (list.size() - k);
            startIndex = index;
        }

        head = new ListNode();
        ListNode node = new ListNode(list.get(index));
        head.next = node;
        index++;
        while (index < list.size()) {
            ListNode temp = new ListNode(list.get(index));
            node.next = temp;
            node = temp;
            index++;
        }
        index = 0;
        while (index < startIndex) {
            ListNode temp = new ListNode(list.get(index));
            node.next = temp;
            node = temp;
            index++;
        }
        node.next = null;
        return head.next;
    }

    /**
     * 面试题 01.06. 字符串压缩
     *
     * @param S
     * @return
     */
    public String compressString(String S) {

        if (S == null || S.length() == 0) {
            return S;
        }
        StringBuilder stringBuilder = new StringBuilder();

        int index = 1;
        int count = 1;
        while (index < S.length()) {
            if (S.charAt(index - 1) != S.charAt(index)) {
                stringBuilder.append(S.charAt(index - 1)).append(count);
                count = 1;
            } else {
                count++;
            }
            index++;
        }
        stringBuilder.append(S.charAt(index - 1)).append(count);
        if (stringBuilder.length() >= S.length()) {
            return S;
        } else {
            return stringBuilder.toString();
        }
    }

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     */
    class NumMatrix {
        int[][] matrix;

        /*public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }
*/
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    sum = sum + matrix[i][j];
                }
            }
            return sum;
        }

        /**
         * 前缀和优化法
         **/
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 1; j < n; j++) {
                        sums[i][j + 1] = sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion1(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum = sum + (sums[i][col2 + 1] - sums[i][col1]);
            }
            return sum;
        }
    }

    /**
     * 338. 比特位计数
     * bitCount()
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {

        int[] res = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            res[i] = Integer.valueOf(i).bitCount(i);
        }

        return res;
    }

    /**
     * 338. 比特位计数
     * 2的整数次幂二进制最高位数为1
     *
     * @param num
     * @return
     */
    public int[] countBits1(int num) {

        //标记上一个整数次幂
        int highBit = 0;
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            //2的整数次幂
            if ((i & (i - 1)) == 0) {
                res[i] = 1;
                highBit = i;
            } else {
                res[i] = res[i - highBit] + 1;
            }
        }

        return res;
    }
}
