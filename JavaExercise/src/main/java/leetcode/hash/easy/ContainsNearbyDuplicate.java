package hash.easy;


/**
 * @author :qiang
 * @date :2019/11/4 下午4:41
 * @description :判断数组中是否存在重复的元素
 * @other :
 */
public class ContainsNearbyDuplicate {

    /**
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
     * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。（根据测试用例，此处应该是绝对值不超过k）
     * <p>
     * 思路：
     * 利用减法进行判断
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {


        for (int i = 0; i < nums.length; ++i) {
            for (int j = Math.max(i - k, 0); j < i; ++j) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 1};
        System.out.println(containsNearbyDuplicate(array, 3));
    }
}
