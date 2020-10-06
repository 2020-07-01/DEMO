package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/19 下午10:21
 * @description :矩阵乘法
 * @other :
 */
public class MatrixMultiplication {

    /**
     * 矩阵与它的转置矩阵相乘
     *
     * @param array
     */
    public static void method(int[][] array) {
        //获取转置矩阵
        int[][] transpositionArray = new int[array[0].length][array.length];

        int k = 0;

        for (int i = 0; i < array.length; i++) {
            int h = 0;
            for (int j = 0; j < array[i].length; j++) {
                transpositionArray[h++][k] = array[i][j];
            }
            k++;
        }
        System.out.println(Arrays.deepToString(transpositionArray));


        //进行矩阵的相乘
        int[][] result = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                //result矩阵i行j列所对应的值为原矩阵i行与转置矩阵j列所对应的乘积之和
                for (int p = 0; p < array.length; p++) {
                    result[i][j] = result[i][j] + array[i][p] * transpositionArray[p][j];
                }
            }
        }

        System.out.println(Arrays.deepToString(result));
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        method(array);
    }
}
