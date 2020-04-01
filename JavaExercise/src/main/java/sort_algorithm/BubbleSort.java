package sort_algorithm;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/17 上午10:28
 * @description :冒泡排序
 * @other :
 */
public class BubbleSort {

    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 34, 5, 6, 7, 78, 5, 3, 42, 5, 25, 2, 43};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
