package basicDataType;

/**
 * @author :qiang
 * @date :2019/10/5 下午1:21
 * @description :基本数据类型
 * @other :
 */
public class Main {

    public static void main(String[] args) {

        /**
         * 将高精度的数据类型转换为低精度的数据类型需要进行显示转换
         * 并且进行截尾操作
         */
        double a = 1.999;
        int a1 = (int) a;//此时输出为1

        /**
         * 在进行扩容转换时不需要显示转换
         */
        int b = 2;
        double b2 = b;


        /**
         * 表达式出现的最大数据类型决定了表达式最终结果的类型
         * 此时结果为double类型,需要进行强制转换为int类型
         */
        int c = (int) (1.0 * 4);


        /**
         * 数据类型提升：
         * 在对集本数据类型进行运算时,只要类型比int小,则在运算之前这些值都会转换为int类型
         * 如果要将其转换为原来的数据类型,将会造成信息丢失
         */


    }
}
