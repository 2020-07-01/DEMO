package string;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/7 下午1:15
 * @description :正则表达式
 * @other :
 */
public class RegularExpression {

    public static String knights = "Then, when you have found the shrubbery ,you must " +
            "cut down the mightiest tree int forest...";

    public static void split(String regex) {
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {

        //此字符串是否满足正则表达式
        System.out.println("1234".matches("-?\\d+"));

        split(" ");
        split(",");



    }
}


