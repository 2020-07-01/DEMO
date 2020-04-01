package sort_algorithm;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/17 上午9:52
 * @description :插入排序
 * @other :
 */
public class InsertSort {

    /**
     * 思想：从第二个元素开始，将合适的元素插入到前面排好序的元素中
     * 应用场景：基本有序或者数据规模比较小
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {

            int temp = array[i];
            for (int j = i - 1; j >= 0; j--) {

                if (array[j] > array[j + 1]) {

                    array[j + 1] = array[j];
                    array[j] = temp;

                }
            }


        }
    }

    public static void main(String[] args) {
        int[] array = {89, 4, 84, 28, 54, 532, 43, 342, 1, 21};

        insertSort(array);

        System.out.println(Arrays.toString(array));
    }
}
