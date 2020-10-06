package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/14 下午9:46
 * @description :
 * @other :
 */
public class Transpose {

    /**
     * 转置矩阵
     *
     * @param A
     * @return
     */
    public static int[][] transpose(int[][] A) {

        int[][] temp = new int[A[0].length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                temp[j][i] = A[i][j];
            }
        }

        return temp;
    }

    public static void main(String[] args) {
        int[][] B = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println(Arrays.deepToString(transpose(B)));

    }

}
