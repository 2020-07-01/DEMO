package string;

/**
 * @author :qiang
 * @date :2019/10/19 下午7:11
 * @description : 测试String的方法
 * @other :
 */
public class TestStringMethod {

    public static void main(String[] args) {

        //将多个字符串放在一起，并使用分割符
        System.out.println(String.join("|", "123", "12f3", "1fa23"));

        //字符串放在字符串常量池中
        String string1 = "123qwe";
        String string2 = "123qwe";
        System.out.println(string1 == string2);

        //此时在堆上分配存储空间，在栈中存储引用地址
        String string3 = new String("123qwe");
        System.out.println(string1 == string3);


        System.out.println(string1.hashCode());
        System.out.println(string2.hashCode());
        System.out.println(string3.hashCode());

        /**
         * 不区分大小写判断字符串是否相等
         */
        String string4 = "qwe";
        String string5 = "QWE";

        System.out.println(string4.equalsIgnoreCase(string5));

        /**
         * 创建格式化的字符串
         * String format：指定输出的格式
         * Object... args：指定等待被格式化的对象
         */
        String string6 = String.format("%s,next year ,you will %d ", "小明", 25);
        System.out.println(string6);

        /**
         * subString(int startIndex,int beginIndex)
         * 返回这个字符串的一个字串，包含开始字符但是不包含结束字符
         */
        System.out.println("123456789".substring(1, 4));

        /**
         * \:在java中是转义字符，比如\n转义为换行,\r转义为回车
         *  比如在字符串中需要输入\,因此要输入两个\\，表示后面的\为一个单纯的字符而已
         */
        System.out.println(("12345678\\9").length());
    }

}
