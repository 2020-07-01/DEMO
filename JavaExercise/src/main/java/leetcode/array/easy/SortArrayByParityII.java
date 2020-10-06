package array.easy;

import java.util.Stack;

/**
 * @author :qiang
 * @date :2019/10/13 下午1:46
 * @description :
 * @other :
 */
public class SortArrayByParityII {

    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * 4 2 5 7
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {

        //创建两个栈一个存储偶数一个存储奇数
        Stack<Integer> stack1 = new Stack<Integer>();//存储偶数
        Stack<Integer> stack2 = new Stack<Integer>();//存储奇数

        for (int i = 0; i < A.length; i++) {

            if (A[i] % 2 == 0) {
                stack1.push(A[i]);
            } else stack2.push(A[i]);
        }


        int j = 0;
        while (j < A.length) {

            if (j % 2 == 0) {
                A[j] = stack1.pop();
            } else A[j] = stack2.pop();

            j++;

        }
        return A;


    }
}
