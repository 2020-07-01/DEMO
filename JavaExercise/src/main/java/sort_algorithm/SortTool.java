package sort_algorithm;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/17 上午10:28
 * @description :排序算法总结
 * @other :
 */
public class SortTool {


    /**
     * 排序的理解：无论是对象还是基本深数据类型，排序总归是基于某一东西进行排序，无论是数字还是String
     *
     * 目前存在的排序算法：
     * 1、冒泡排序
     * 2、插入排序
     * 3、
     */


    /**
     * 冒泡排序
     * 从第一个数开始，两两进行比较，将大的数放在前面
     * 稳定排序
     * 时间复杂度：n-n的平方
     *
     * @param nums
     */
    public static void bubblingSort(int[] nums) {
        /**
         * 优化：如果没有发生交换则已排好序，终止循环
         */
        boolean flag = true;
        for (int i = nums.length - 1; i > 0; i--) {
            flag = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                return;
            }

        }
    }

    /**
     * 插入排序
     * 从第二个元素开始，将后面的元素一次插入到前面排好序的元素的指定位置
     * 稳定排序
     * 时间复杂度：n-n的平方
     *
     * @param nums
     */
    public static int[] insertSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * 从第一个数开始，往后依次遍历，寻找最小的数，然后与第一个数进行交换
     * 不稳定排序
     * 时间复杂度： n的平方
     *
     * @param nums
     */
    public static void selectSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            int min = nums[i];
            int k = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    k = j;
                }
            }
            nums[k] = nums[i];
            nums[i] = min;

        }
    }


    /***
     * 归并排序
     * 先将数组进行分离，直到剩下一个元素，然后再依次进行有序合并，采用递归的思想
     * 时间复杂度nlogn
     * @param nums
     */
    public static int[] mergeSort(int[] nums) {
        //一个元素不再分离
        if (nums.length < 2) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        return merge(mergeSort(left), mergeSort(right));

    }

    /**
     * 将两段排好序的数组合并在一起
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    /**
     * 快速排序
     * 思想：
     * 每次取第一个数作为基数
     * 从后往前遍历，直到比基数小的数停止,或者left = right
     * 从前往后遍历，直到比基数大的数停止,或者left = right
     *
     * @param nums
     */
    public static void quickSort(int[] nums) {

        quickSort(nums, 0, nums.length - 1);

    }

    private static void quickSort(int[] arrays, int left, int right) {

        if (left > right) {
            return;
        }

        int base = arrays[left];
        int i = left;
        int j = right;
        //一个元素不进行排序
        while (i != j) {

            while (arrays[j] >= base && i < j) {
                j--;
            }

            while (arrays[i] <= base && i < j) {
                i++;
            }
            //将会再次进行遍历
            if (i < j) {
                int temp = arrays[i];
                arrays[i] = arrays[j];
                arrays[j] = temp;
            }
        }
        //i=j 交换基准 基准数值已经排序就位
        arrays[left] = arrays[i];
        arrays[i] = base;
        //基准左右两边再次进行基准进行排序
        quickSort(arrays, left, i - 1);
        quickSort(arrays, i + 1, right);
    }


    /**
     * 计数排序
     * 思想：n个0到n的正整数
     * 使用bucket记录每个数组出现的次数
     * 从后往前遍历依次输出
     * 时间复杂度为n+k
     *
     * @param nums
     */
    public static void countingSort(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        System.arraycopy(countingSort(nums, max), 0, nums, 0, nums.length);
    }

    private static int[] countingSort(int[] arrays, int max) {

        int[] bucket = new int[max + 1];

        for (int i = 0; i < arrays.length; i++) {
            bucket[arrays[i]] = bucket[arrays[i]] + 1;
        }

        int index = arrays.length - 1;
        int[] result = new int[arrays.length];
        //从后往前进行遍历确保稳定性
        for (int i = bucket.length - 1; i > 0; i--) {
            int count = bucket[i];
            while (count != 0) {
                result[index--] = i;
                count--;
            }
        }

        return result;
    }


    /**
     * 桶排序
     * 稳定排序
     *
     * @param nums
     * @return
     */
    public void bucketSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int bucketSize = 10;//桶中元素的个数默认为10,如果大于10可进行扩容
        int max = 0;
        int min = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                max = nums[i];
            }
        }

        //计算桶的个数
        int bucketCount = (int) Math.floor((max - min) / bucketSize + 1);
        //创建桶
        int[][] buckets = new int[bucketCount][0];

        for (int i = 0; i < nums.length; i++) {
            int x = (int) Math.floor((nums[i] - min) / bucketSize);
            buckets[x] = append(buckets[x], nums[i]);
        }
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            int[] bucket = buckets[i];
            //对桶中的元素进行排序
            bucket = insertSort(bucket);
            for (int j = 0; j < bucket.length; j++) {
                nums[index++] = bucket[j];
            }
        }
    }

    /**
     * 桶中元素的扩容
     * 每次自动扩容一位，然后保存数据
     */
    private int[] append(int[] arrays, int value) {

        arrays = Arrays.copyOf(arrays, arrays.length + 1);
        arrays[arrays.length - 1] = value;
        return arrays;
    }


    /**
     * 基数排序
     * @param nums
     */
    public static void radixSort(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return;
        }
        //最大值
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        //位数
        int maxDigit = 1;
        while (Math.pow(10, maxDigit) < max) {
            maxDigit++;
        }

        for (int i = 1; i <= maxDigit; i++) {
            int[][] buckets = new int[10][0];
            for (int j = 0; j < nums.length; j++) {
                int bucket = nums[j] % (int) Math.pow(10, i) / (int)Math.pow(10, i-1);
                buckets[bucket] = bucketAppend(buckets[bucket], nums[j]);
            }

            int index = 0;
            for (int k = 0; k < buckets.length; k++) {
                for (int h = 0; h < buckets[k].length; h++) {
                    nums[index++] = buckets[k][h];
                }
            }
        }
    }

    public static int[] bucketAppend(int[] arrays, int value) {
        arrays = Arrays.copyOf(arrays, arrays.length + 1);
        arrays[arrays.length - 1] = value;
        return arrays;
    }

    public static void main(String[] args) {

        int[] array1 = {89, 4, 20, 34, 655, 768, 6, 789, 0, 980, 2342, 2, 21, 1};
        int[] array = {29, 25, 3, 49, 42, 9, 37, 21, 43};
        SortTool sortTool = new SortTool();
        mergeSort(array1);
        System.out.println(Arrays.toString(array));
    }


}
