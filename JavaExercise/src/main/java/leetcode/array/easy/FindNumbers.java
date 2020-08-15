package array.easy;

/**
 * @author :qiang
 * @date :2019/10/3 上午9:41
 * @description :求个数
 * @other :
 */
public class FindNumbers {

    /**
     * 给定一个二进制数组， 计算其中最大连续1的个数
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes(int[] nums) {

        int result = 0;//保存最终结果值
        int temp = 0;//保存当前值

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {//如果为0则temp的值加1
                temp++;

            } else {
                temp = 0;
            }

            result = Math.max(temp, result);

        }
        return result;
    }



    public static void main(String[] args) {
        int[] a = {1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0};

        System.out.println(findMaxConsecutiveOnes(a));
    }
}
