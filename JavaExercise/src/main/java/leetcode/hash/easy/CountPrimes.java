package hash.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :qiang
 * @date :2019/10/29 下午7:57
 * @description :计算质数的数量
 * @other :
 */
public class CountPrimes {

    /**
     * 计算小于n的整数中质数的数量
     * 此种方法性能很差，leetCode时间超时
     * 时间复杂度为n的平方
     *
     * @param n
     * @return
     */
    public static int countPrimes1(int n) {

        int count = 0;
        for (int i = 2; i < n; i++) {
            //设置标志
            boolean p = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0)
                    p = false;
            }

            if (p) {
                System.out.print(i + " ");
                count++;
            }

        }
        System.out.println();
        return count;
    }

    /**
     * 在上面循环中的第二个循环可以进行优化
     * 只需要判断前sqart(n)个数字即可
     * 如果前面不存在整除的数后面也不会存在
     * 时间复杂度为n的开方
     *
     * @param n
     * @return
     */
    public static int countPrimes2(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            //设置标志
            boolean p = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0)
                    p = false;
            }
            if (p) {
                System.out.print(i + " ");
                count++;
            }

        }
        System.out.println();
        return count;
    }

    /**
     * 来自leetCode解法
     * 标记法：将非质数的元素进行标记，剩下的元素为质数，然后进行计算
     *
     * @param
     * @return
     */


    public static void main(String[] args) {
        System.out.println(countPrimes1(10000));
    }
}
