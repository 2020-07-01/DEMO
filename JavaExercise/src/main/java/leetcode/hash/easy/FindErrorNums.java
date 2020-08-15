package hash.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :qiang
 * @date :2019/11/4 下午10:47
 * @description :寻找错误的元素
 * @other :
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，
 * 导致集合丢失了一个整数并且有一个元素重复。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，
 * 再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的
 */
public class FindErrorNums {

    /**
     * 遍历数组将其存储在hashMap中，如果存在重复的元素则停止遍历
     *
     * @param nums
     * @return
     */
    public static int[] findErrorNums(int[] nums) {
        //创建数组存储结果
        int[] out = new int[2];


        int count[] = new int[10002];
        //如果数字重复则存储的值为2
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]] = count[nums[i]] + 1;
        }

        for (int i = 0; i <= nums.length; i++) {
            if (count[i] == 2)//如果值为2则元素重复
                out[0] = i;
            if (count[i] == 0 && i != 0 && i <= nums.length)
                out[1] = i;
        }

        return out;

    }

    public static void main(String[] args) {

        int[] array = {1,2,4,4};

        System.out.println(Arrays.toString(findErrorNums(array)));
    }

}
