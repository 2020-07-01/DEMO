package leetcode;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : Demo
 * @Author : yq
 * @Date: 2020-09-30
 * @Description : 算法总结
 */
public class Demo {

    /**
     * 动态规划：将一个问题分解成若干个子问题，先求解子问题，然后从子问题的解中得到原问题的解，但是子问题往往不是相互独立的
     * 1.连续数组最大值
     * 2.隔离一个数值获取最大值
     * 3.斐切那波数列
     * 4.两步楼梯与三步楼梯的区别
     * 5.二维数组路径问题：从左上角到右下角最值及走过的路径等等
     */

    /**
     * 贪心算法：
     * 做出当前看来最好的选择，获取局部最优解，进而获取问题的最优解
     * 将一个大的问题划分成多个相同但互不影响的子问题，通过子问题的最优解求出最终解
     * 1.换酒问题
     */


    /**
     * 数组：
     * 1.最长公共前缀算法：暴力，动态规划，滑动窗口
     * 矩阵的转置，旋转
     * 辅助数组法
     * 数组的深拷贝
     * 倒数第k大/小元素
     */

    /**
     * 双指针法： 双指针法也可以提前遍历确定指针位置
     * 前后指针进行覆盖
     * 前后指针进行删除
     * Floyd(龟兔赛跑法，判圈算法)
     *
     * 快慢指针法：一个指针每次走一步，另一个指针每次走两步，如果相遇则存在环
     * 可以判断链表是否存在环
     * 找出相同的元素
     *
     * 双指针哑节点问题
     */


    /**
     * 二叉搜索树：
     * 1.前序，中序，后序遍历 递归+迭代
     * 2.最小深度、最大深度
     * 3.二叉搜索树的搜索，迭代和递归
     */


    /**
     * 二分查找法：结束条件以及结束时取值问题
     * 二分左边界搜索法 ： 744. 寻找比目标字母大的最小字母
     */


    /**
     * 摩尔投票法：
     */


    /**
     * 哈希：
     *
     * 1.和为k的子数组
     * 2.和可整除K的子数组
     */


    /**
     * 深度优先遍历：
     */

    /**
     * 栈抵消法：
     *
     */

    /**
     * Set抵消法：
     * 1.判断回文排列
     */


    /**
     * 前缀树：
     */


    /**
     * 剪枝算法：
     */

    /**
     * 链表：
     * 链表相交
     * 环形链表
     */

    /**
     *
     * 拓扑排序：
     */


    /**
     * 单调栈
     */

    /**
     * 回溯法：总体思想为试探法
     * 通过枚举的方式不断尝试，当满足条件时继续向下执行，当不满足条件时，进行回溯
     * 深度优先遍历
     * 广度优先遍历
     */

    /**
     * 无重复数字全排列
     *
     * @param nums
     */
    public void backtrack1(int[] nums) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        dfs1(integerArrayList, nums);
    }

    private void dfs1(ArrayList<Integer> innerList, int[] nums) {

        //终止条件
        if (nums.length == innerList.size()) {
            System.out.println(innerList);
        } else {
            //从头开始遍历元素，此处待优化
            for (int i = 0; i < nums.length; i++) {
                //如果包含，则已经添加
                if (innerList.contains(nums[i])) {
                    continue;
                }
                //如果不包含，添加元素
                innerList.add(nums[i]);
                //继续向下遍历
                dfs1(innerList, nums);
                //删除此元素，回溯到上个状态，继续对下个元素进行判断
                innerList.remove(innerList.size() - 1);
            }
        }
    }

    //先填充好元素，动态维护，根据下标判断是否输出s
    public void backtrack2(int[] nums) {
        dfs2(nums, 0);
    }

    private void dfs2(int[] nums, int index) {
        //System.out.println("index:" + index + "[List]:" + Arrays.toString(nums));
        //终止条件，如果当前下标为数组长度
        if (index == nums.length) {
            System.out.println("index:" + index + "[List]:" + Arrays.toString(nums));
        } else {
            for (int i = index; i < nums.length; i++) {

                /**
                 * 1,2,3 index = 0
                 * 1,2,3 index = 1 交换 2,1,3 下一个  。。。 回溯 1，2，3
                 * 1,2,3 index = 3 交换 3,2,1
                 */
                //交换元素
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;

                //判断下一个元素
                dfs2(nums, index + 1);

                temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
    }

    /**
     * 无重复元素全排列
     * 广度优先遍历
     *
     * @param nums
     */
    public void backtrack3(int[] nums) {

        LinkedList<List<Integer>> lists = new LinkedList<>();

        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> innerList = new ArrayList<>();
            innerList.add(nums[i]);
            lists.add(innerList);
        }

        bfs(nums, lists, 1);
    }

    //广度优先遍历
    private void bfs(int[] nums, LinkedList<List<Integer>> lists, int curCount) {
        System.out.println(lists);
        if (curCount == nums.length) {
            return;
        } else {
            int index = 0;
            int n = lists.size();
            while (index < n) {
                List<Integer> innerList = lists.removeFirst();
                for (int i = 0; i < nums.length; i++) {
                    if (!innerList.contains(nums[i])) {
                        List<Integer> temp = new LinkedList<>();
                        temp.addAll(innerList);
                        temp.add(nums[i]);
                        lists.addLast(temp);
                    }
                }
                index++;
            }

            bfs(nums, lists, curCount + 1);
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        int[] nums = new int[]{1, 2, 3, 4};
        demo.backtrack3(nums);

    }


}
