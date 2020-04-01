package string;

/**
 * @author :qiang
 * @date :2019/10/5 下午5:52
 * @description :不可变的String
 * @other :
 */
public class FinalString {

    public static void main(String[] args) {
        /**
         * String它是一个final关键字修饰的不变类
         * 如果使用+操作符对String进行操作时
         * 他的底层使用的是StringBuilder的append()方法进行添加
         * 然后使用toString方法返回String对象
         * 最后进行输出
         *
         * String对象在进行传递的时候,传递的是 复制的引用 该引用指向的对象不会改变
         * 在进行操作之后,会创建新的对象
         */
        String q = "yuqiang";

        String qq = q + "1";

        System.out.println(q);

        System.out.println(qq);
    }
}
