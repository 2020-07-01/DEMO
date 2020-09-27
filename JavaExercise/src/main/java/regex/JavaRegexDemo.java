package regex;

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


    public static void main(String[] args) {
        JavaRegexDemo.regexTest2();

    }

}
