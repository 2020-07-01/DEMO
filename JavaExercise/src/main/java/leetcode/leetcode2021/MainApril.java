package leetcode.leetcode2021;

import callback_function.example2.Main;

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

    public static void main(String[] args) {
        MainApril main = new MainApril();
        System.out.println(main.clumsy(4));
    }
}
