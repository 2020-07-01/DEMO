package array.easy;

/**
 * @author :qiang
 * @date :2019/10/2 下午3:19
 * @description :删除元素
 * @other :
 */
public class RemoveElements {

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素
     * 使得每个元素只出现一次，返回移除后数组的新长度
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     */

    public static int removeDuplicates(int[] nums) {

        /*
        从第二个元素开始,如果后一个元素不等与第一个元素，则将后一个元素存储到数组中
         */
        int p = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[p] = nums[i];
                p++;
            }
        }

        return p;

    }

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素
     * 返回移除后数组的新长度
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {

        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }




    public static void main(String[] args) {
        int[] a = {1, 2, 0, 4, 5, 0, 0, 6, 7};

        System.out.println(removeElement(a, 2));
    }
}
