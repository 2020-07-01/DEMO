package string;

import java.util.ArrayList;

/**
 * @author :qiang
 * @date :2019/10/1 下午3:16
 * @description :判断是否为回文串
 * @other :
 */
public class IsPalindrome {

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {

        /**
         * 先遍历处符合体条件的字符,然后遍历比较
         */
        char c[] = s.toLowerCase().toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= '0' && c[i] <= '9' || c[i] >= 'a' && c[i] <= 'z') {
                list.add(c[i]);

            }
        }

        int start = 0;
        int end = list.size() - 1;
        while (start < end) {

            if (list.get(start) != list.get(end)) {

                return false;
            }

            start++;
            end--;

        }
        return true;
    }

    public static void main(String[] args) {
        String string = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(string));
    }
}
