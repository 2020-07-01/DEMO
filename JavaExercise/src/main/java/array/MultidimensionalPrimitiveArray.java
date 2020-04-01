package array;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/11 下午3:40
 * @description :学习多维数组
 * @other :
 */
public class MultidimensionalPrimitiveArray {

    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(Arrays.deepToString(a));
    }
}
