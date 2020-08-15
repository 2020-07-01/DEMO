package string;

/**
 * @author :qiang
 * @date :2019/10/4 上午10:32
 * @description :二进制求和
 * @other :
 */
public class AddBinary {

    /**
     * 两个二进制相加
     *
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {

        /**
         * 从后往前遍历两个数组,短数组补0
         * 对应两个数相加,如果结果为2则进行进位ca,进位之后赋值给sum
         * 接着sum和两个值相加
         * 最后将结果ans进行反转输出
         */
        StringBuilder ans = new StringBuilder();//存储最终结果
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            //获取字符串a对应的某一位的值，当i<0时sum+=0(向前补0)，否则取i的char类型和0的char类型相差刚好为1
            sum += (i >= 0 ? a.charAt(i) - '0' : 0);
            sum += (j >= 0 ? b.charAt(j) - '0' : 0);
            ans.append(sum % 2);
            ca = sum / 2;//进入到下一位接着相加
        }

        ans.append(ca == 1 ? ca : "");
        //将ans进行反转
        return ans.reverse().toString();
    }
}
