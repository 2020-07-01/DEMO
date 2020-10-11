package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : JavaRegexDemo
 * @Author : yq
 * @Date: 2020-09-26
 * @Description : 正则表达式
 */
public class JavaRegexDemo {


    /**
     * 验证字符串是否为数字字符串
     * 从开始到结尾是否全部由数字组成
     * ^：匹配开始位置
     * $：匹配结尾位置
     * *：匹配前面的子表达式零次或者多次
     * [0-9]:表示数字
     * \d:匹配一个数字字符
     *
     * @return
     */
    public static boolean regexTest1() {
        String string = "1122213213";
        String regex = "^[0-9]*$";
        String regex1 = "^\\d*$";
        return string.matches(regex1);
    }

    /**
     * 替换匹配到的第一个字符串
     * + : 表示匹配前面的子表达式一次或者多次
     * * : 表示匹配前面的子表达式零次或者多次，没有匹配到也属于匹配成功
     *
     * @return
     */
    public static void regexTest2() {

        String string = "fffas?fafa1213213s";
        string = string.replaceFirst("[0-9]*", "*");
        String string1 = "gds6362322n312n321n321n213213211";
        string1 = string1.replaceFirst("\\d+", "*");
        System.out.println(string1);
        System.out.println(string);
    }

    /**
     * 判断是否包含数字
     * [0-9] \d
     * *一次或者多次
     * {n} n为非负整数，匹配前面的表达式n次
     * <p>
     * <p>
     * 包含数字则数字前后为0或多个字符
     */
    public static boolean regexTest3() {
        /**
         * 匹配多次
         */
        String string = "313213";
        String regex = "^\\d*$";
        /**
         * //只匹配一次
         */
        String string1 = "1";
        String regex1 = "[0-9]";
        /**
         * 匹配前面的表达式指定次数
         * 逗号(,) 至少匹配4次
         */
        String string2 = "131";
        String regex2 = "[0-9]{4,}";
        /**
         * 匹配子表达式
         * .表示匹配除换行符之外的任何单个字符
         */
        String string3 = "34fas2rfsq343212fas3";
        String regex3 = ".*[0-9]{4,}.*";

        return string3.matches(regex3);
    }

    /**
     * 是否包含11位数字
     *
     * @return
     */
    public static boolean regexTest4() {

        String string = "rew11111312321111111werwe";
        String regex = ".*[0-9]{11}.*";
        return string.matches(regex);
    }

    /**
     * 是否包含字母
     * {}:一般表示匹配的字符的长度
     * []:一般表示匹配的字符的范围，在不指定次数时，默认只匹配单个
     *
     * @return
     */
    public static boolean regexTest5() {

        String string = "fsrawaf";
        String regex = ".*[a-z].*";

        return string.matches(regex);

    }


    /**
     * 包含第10集
     * {1,2} 匹配的字符长度1或者2
     * {}：匹配的字符串为花括号前直到特殊字符
     *
     * @return
     */
    public static boolean regexTest6() {

        String string = "重启第1集";
        String regex = ".*第[0-9]{1,2}集.*";

        /**
         * 包含汉字"重启"
         */
        String string1 = "重启第1集";
        String regex1 = ".*重启{1,2}.*";

        return string1.matches(regex1);
    }

    /**
     * 包含字母或者数字
     * | : 两个选择中的一个
     *
     * @return
     */
    public static boolean regexTest7() {
        String string = "%%";
        String regex = ".*[0-9].*|.*[a-zA-z].*";

        /**
         * 仅由字母组成，不包含空格等其他字符
         */
        String string1 = "qeqweQEqw";
        String regex2 = "[a-zA-Z]{1,}";
        String regex3 = "[a-zA-Z]+";
        return string1.matches(regex3);
    }

    /**
     * 验证手机号
     * 1.长度11位
     * 2.全为数字
     *
     * @return
     */
    public static boolean regexTest8() {
        String string = "11111111111";
        String regex = "^[0-9]{11}$";

        return string.matches(regex);
    }

    /**
     * {n,m}:最少匹配n次，最多匹配m次
     *
     * @return
     */
    public static boolean regexTest9() {
        String string = "3fa12af43ab2112";
        String regex = ".*[0-9]{3,4}.*";
        return string.matches(regex);
    }

    /**
     * 匹配问号 ？
     *
     * @return
     */
    public static boolean regexTest10() {
        String string = "af";
        String regex = "[?]{1}";
        return string.matches(regex);
    }


    /**
     * 匹配所包含字段中的任意一个
     * 匹配包含的任意一个字符
     * [yuqiang]
     *
     * @return
     */
    public static boolean regexTest11() {
        String string = "414qwert";
        String regex = "^.*[yuqiang].*$";
        return string.matches(regex);
    }

    /**
     * 不包含指定字符
     * 匹配未包含的任意字符
     * [^yuqiang]
     *
     * @return
     */
    public static boolean regexTest12() {

        String string = "yuqiang";
        String regex = ".*[^yuqiang].*";
        return string.matches(regex);
    }

    /**
     * \b: 匹配单词边界
     *
     * @return
     */
    public static void regexTest13() {
        String str = "(中文问号？123???英文)问号?我是华丽[的制表符\t]我是华丽{的空格符 我是华丽}的换行符\n";
        String rex = "\\b";

        Pattern pattern = Pattern.compile(rex);

        String[] result = pattern.split(str);

        for (String string : result) {
            System.out.println("分割的字符串:" + "[" + string + "]");
        }
    }

    /**
     * 是否包含3位数字
     */
    public static void regexTest14() {

        String string = "12414和2421";
        String regex = ".*[0-9]{9,}.*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        boolean flag = matcher.matches();
        System.out.println(flag);
    }

    /**
     * 是否包含.mp4
     * .:匹配文本点 ，前面需要添加文本转义字符\
     */
    public static void regexTest15() {

        String string = "12414和2421.1mp4";
        String regex = ".*\\.mp4.*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        boolean flag = matcher.matches();
        System.out.println("flag:" + flag);
        int groupCount = matcher.groupCount();
        System.out.println("groupCount:" + groupCount);
    }



    public static void main(String[] args) {
        JavaRegexDemo.regexTest15();
    }

}
