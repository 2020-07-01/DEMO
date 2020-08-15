package sort.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/11/2 下午1:42
 * @description :寻找两个数组的交集
 * @other :
 */
public class Intersection {

    /**
     * 寻找两个数组的交集
     * 输出的每个元素必须唯一
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {

        /**
         * 将两个数组分别存储到hashMap中此时可以删除重复的项
         * 然后遍历判断是否同时存在两个hashMap中
         */
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            hashMap1.put(nums1[i], nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            hashMap2.put(nums2[i], nums2[i]);
        }

        List<Integer> list = new ArrayList<Integer>();

        for (Integer item : hashMap1.keySet()) {

            if (hashMap2.containsKey(item)) {
                list.add(item);
            }
        }

        int[] result = new int[list.size()];

        int k = 0;
        for (Integer item : list) {
            result[k++] = item;
        }
        return result;
    }

    /**
     * 求数组的交集，元素可以进行重复
     * 此种方法时间复杂度为N的平方
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> hashMap1 = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            hashMap1.put(i, nums1[i]);
        }

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < nums2.length; i++) {
            if (hashMap1.containsValue(nums2[i])) {
                list.add(nums2[i]);
                //删除元素
                for (Integer item : hashMap1.keySet()) {
                    if (hashMap1.get(item) == nums2[i]) {
                        hashMap1.remove(item);
                        break;
                    }
                }
            }
        }

        int[] result = new int[list.size()];

        int k = 0;
        for (Integer item : list) {
            result[k++] = item;
        }
        return result;

    }


    /**
     * 求数组的交集，元素可以进行重复
     * 时间复杂度为n
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect1(int[] nums1, int[] nums2) {

        /**
         * 遍历数组1，使用hashMap存储数字和出现的次数
         */
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], hashMap.getOrDefault(nums1[i], 0) + 1);
        }

        List<Integer> list = new ArrayList();
        for (int i = 0; i < nums2.length; i++) {
            if (hashMap.containsKey(nums2[i])) {
                list.add(nums2[i]);
                //次数减1
                hashMap.put(nums2[i], hashMap.get(nums2[i]) - 1);
                if (hashMap.get(nums2[i]) == 0) {
                    hashMap.remove(nums2[i]);
                }
            }
        }

        int[] result = new int[list.size()];

        int k = 0;
        for (Integer item : list) {
            result[k++] = item;
        }
        return result;
    }


    public static void main(String[] args) {

        int[] array1 = {1, 2, 2, 3, 45, 56, 65, 66};
        int[] array2 = {2, 2, 45};

        System.out.println(Arrays.toString(intersect1(array1, array2)));
    }
}
