package string;

import java.util.Stack;

/**
 * @author :qiang
 * @date :2019/9/30 上午11:06
 * @description :反转字符串
 * @other :
 */
public class ReverseString {

    /**
     * 将第一个字符与最后一个字符进行交换位置
     *
     * @param s
     */
    public static void reverseString(char[] s) {

        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - (i + 1)];
            s[s.length - (i + 1)] = temp;
        }
    }


    /**
     * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
     * 如果剩余少于 k 个字符，则将剩余的所有全部反转。
     * 如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
     **/
    public static String reverseStr(String s, int k) {
        String[] str = new String[10001];
        int i = 0;
        int p = 0;
        //1.将字符串s以每2k个字符分割
        for (i = 0; i < s.length() - 2 * k; i += 2 * k) {
            str[p++] = s.substring(i, i + 2 * k);
        }
        str[p++] = s.substring(i);

        //2.将每2k个字符的前k个字符反转
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < p - 1; j++) {
            StringBuilder sb = new StringBuilder();
            sb = sb.append(str[j].substring(0, k)).reverse();
            res.append(sb.append(str[j].substring(k, 2 * k)));
        }

        //3.处理特殊情况
        StringBuilder sb = new StringBuilder();
        if (str[p - 1].length() < k) {
            res.append(sb.append(str[p - 1]).reverse());
        } else {
            res.append(sb.append(str[p - 1].substring(0, k)).reverse());
            res.append(str[p - 1].substring(k));
        }

        //4.转换成字符串返回
        return res.toString();
    }


    /**
     * 反转所有的字母
     * 思路：将s中的所有字母存储到栈中然后遍历s,如果是字母则出栈
     *
     * @param S
     * @return
     */
    public static String reverseOnlyLetters(String S) {

        char[] s = S.toCharArray();
        Stack<Character> stack = new Stack();
        //将所有字母存入到栈中
        for (char item : s) {
            if ((item >= 97 && item <= 122) || (item >= 65 && item <= 90)) {
                stack.push(item);
            }
        }

        int i = 0;
        //遍历s出栈
        for (char item : s) {
            if ((item >= 97 && item <= 122) || (item >= 65 && item <= 90)) {
                s[i++] = stack.pop();
            } else {
                s[i++] = item;
            }
        }

        String newString = String.valueOf(s);

        return newString;
    }

    public static void main(String[] args) {

        String string = "q-er-ero";
        String temp = reverseOnlyLetters(string);
        System.out.println(temp);
    }

}
