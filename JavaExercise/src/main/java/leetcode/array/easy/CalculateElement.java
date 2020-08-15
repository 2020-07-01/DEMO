package array.easy;

import java.util.HashMap;

/**
 * @author :qiang
 * @date :2019/10/14 下午9:58
 * @description :计算元素
 * @other :
 */
public class CalculateElement {

    /**
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。
     * 计算出现次数最多的元素
     *
     * @param nums
     * @return
     */

    public int majorityElement(int[] nums) {

        //创建hashMap存储元素的次数
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //每次将key的value值加1
            //getOrDefault()方法是在第一次遇到时使用默认值
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        int max = 0;
        int result = 0;
        for (Integer key : count.keySet()) {
            if (count.get(key) > max) {
                max = count.get(key);
                result = key;
            }
        }
        return result;
    }

}

