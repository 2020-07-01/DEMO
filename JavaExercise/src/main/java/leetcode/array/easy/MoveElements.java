package array.easy;

/**
 * @author :qiang
 * @date :2019/10/2 下午4:01
 * @description :移动元素
 * @other :
 */
public class MoveElements {

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
     * 必须在原数组上操作，不能拷贝额外的数组
     * 尽量减少操作次数
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {

        /*
        将非0的数字前移，后面进行补0
         */
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                p++;
            } else {
                nums[i - p] = nums[i];
            }
        }

        //补0
        for (int i = nums.length - p; i < nums.length; i++) {
            nums[i] = 0;
        }


    }

    public static void main(String[] args) {
        int[] a = {0, 6, 0, 0, 7, 0, 9};

        moveZeroes(a);

        for (int item : a) {
            System.out.println(item);
        }
    }
}
