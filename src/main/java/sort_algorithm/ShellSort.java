package sort_algorithm;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/17 上午10:39
 * @description :希尔排序
 * @other :
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        /**
         * 先进行分组，然后对每个分组进行插入排序
         * 增量设置为数组长度的一半，然后依次递减为原来的一半
         */

        int N = array.length;

        //外层循环控制增量
        for (int gap = N / 2; gap > 0; gap /= 2) {//gap为增量大小

            //内层循环对每个分组进行插入排序
            for (int i = gap; i <= N; i++) {//gap到N表示分组的数量

                int temp = array[i - gap];//temp为每个数组的第一个元素

                for (int j = i - gap; j >= 0; j -= gap) {

                    for (int k = j - 1; k >= 0; k--) {

                        if (array[k] > array[k + 1]) {

                            array[k + 1] = array[k];
                            array[k] = temp;

                        }
                    }

                }
            }
        }

        System.out.println(Arrays.toString(array));
    }
}
