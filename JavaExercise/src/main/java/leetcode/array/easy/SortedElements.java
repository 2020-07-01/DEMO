package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/2 下午4:28
 * @description : 对数组进行排序
 * @other :
 */
public class SortedElements {


    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     *
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {

        /*
        采用插入法排序
         */
        int[] B = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            int square = A[i] * A[i];
            B[i] = square;
        }

        Arrays.sort(B);
        return B;
    }

    /**
     * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素
     * 按奇偶数进行排序
     *
     * @param A
     * @return
     */
    public static int[] sortArrayByParity(int[] A) {

        int i = 0;
        int j = A.length - 1;

        while (i < j) {
            if (A[i] % 2 == 1 && A[j] % 2 == 0) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
                j--;
            }
            if (A[i] % 2 == 0 && A[j] % 2 == 1) {
                i++;
                j--;
            }
            if (A[i] % 2 == 0 && A[j] % 2 == 0) {
                i++;
            }
            if (A[i] % 2 == 1 && A[j] % 2 == 1) {
                j--;
            }
        }

        return A;
    }

    public static void main(String[] args) {
        int[] A = {3, 1, 2, 4};
        int[] B = sortArrayByParity(A);

        for (int item : B) {
            System.out.println(item);
        }
    }

}
