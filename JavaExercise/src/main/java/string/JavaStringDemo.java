package string;

/**
 * @author :qiang
 * @date :2019/10/7 下午12:55
 * @description :
 */
public class JavaStringDemo {

    /**
     * 字符串中子元素替换方式
     */
    public void replaceStringItem(){
        String string = "fffffafsaassfsgsfdgadsfSFAADGSDFSd";
        /**
         * case1:
         */
        String string1 = string.replace("s","*");
        System.out.println(string1);

        /**
         * case2:
         */
        String string2 = string.replace("ff","&");
        System.out.println(string2);

        /**
         * case3:
         */
        String string3 = string.replace('f','^');
        System.out.println(string3);


    }


    public static void main(String[] args) {

        JavaStringDemo javaStringDemo = new JavaStringDemo();
        javaStringDemo.replaceStringItem();


    }
}
