package hash.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :qiang
 * @date :2019/11/4 下午4:07
 * @description : 只出现一次的元素
 * @other :
 */
public class SingleNumber {


    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现两次。找出那个只出现了一次的元素
     * <p>
     * 思路：
     * 遍历数组使用将其存入到hashMap中
     * 如果存在则进行删除，如果不存在则存储
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap();

        for (Integer item : nums) {
            //如果存在则进行删除
            if (hashMap.containsKey(item)) {
                hashMap.remove(item);
            }
            //否则进行添加
            else {
                hashMap.put(item, hashMap.getOrDefault("item", 0) + 1);
            }
        }
        Integer result = 0;
        for (Integer item : hashMap.keySet()) {
            if (hashMap.get(item) == 1) {
                result = item;
                return item;
            }
        }

        return result;
    }


    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * <p>
     * 思路：
     * 如果存在则将value置为3
     *
     * @param nums
     * @return
     */
    public static int singleNumber1(int[] nums) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (Integer item : nums) {
            if (hashMap.containsKey(item)) {
                hashMap.put(item, 3);
            } else {
                hashMap.put(item, hashMap.getOrDefault(item, 0) + 1);
            }
        }

        Integer result = -1;

        for (Integer item : hashMap.keySet()) {
            if (hashMap.get(item) == 1) {
                result = item;
                return result;
            }
        }
        return result;
    }


    /**
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，
     * 其余所有元素均出现两次。 找出只出现一次的那两个元素。
     * 思路：
     *
     * @param nums
     * @return
     */
    public static int[] singleNumber2(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap();

        for (Integer item : nums) {
            //如果存在则进行删除
            if (hashMap.containsKey(item)) {
                hashMap.remove(item);
            }
            //否则进行添加
            else {
                hashMap.put(item, hashMap.getOrDefault("item", 0) + 1);
            }
        }
        int[] result = new int[2];
        int i = 0;
        for (Integer item : hashMap.keySet()) {
            if (hashMap.get(item) == 1) {
                result[i++] = item;
                if (i == 2) {
                    return result;
                }
            }
        }
        return result;

    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 3, 2, 4, 5};

        System.out.println(Arrays.toString(singleNumber2(array)));
    }
}
