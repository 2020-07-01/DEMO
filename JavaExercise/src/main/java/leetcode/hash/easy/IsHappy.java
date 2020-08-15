package hash.easy;

import com.sun.prism.PhongMaterial;

import java.util.HashSet;

/**
 * @author :qiang
 * @date :2019/10/29 下午7:25
 * @description : 判断一个数字是否为快乐数
 * @other :
 */
public class IsHappy {

    public static boolean isHappy(int n) {

        /**
         * 在set中存储sum，如果重复则说明进入死循环，即退出
         * 代码优化：可以使用取余操作来获取整型数每位上的数字
         */
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;//存储结果
        while (true) {

            /**
             * 先将整数转换为整型数组
             */
            String str = Integer.toString(n);

            int[] array = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                Character c = str.charAt(i);
                array[i] = Integer.parseInt(c.toString());
            }

            for (int i = 0; i < array.length; i++) {
                sum = sum + (int) Math.pow(array[i], 2);
            }
            if (sum == 1) {
                return true;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
                n = sum;
                sum = 0;
            }
        }


    }
}
