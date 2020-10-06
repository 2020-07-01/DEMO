package array.easy;

/**
 * @author :qiang
 * @date :2019/10/13 下午2:06
 * @description :
 * @other :
 */
public class SearchInsert {

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 二分查找法
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;

        if (nums[end] < target) {
            return end + 1;
        }

        while (start <= end) {
            int mid = (end + start) / 2;
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;

    }


}
