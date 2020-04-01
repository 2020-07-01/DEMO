package generics.wildcardCharacter;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author :qiang
 * @date :2019/10/11 下午2:51
 * @description : 通配符学习
 * @other :
 */
public class CovariantArrays {

    public static void main(String[] args) {

        /**
         * 创建apple数组，只能放置apple类型及其他的子类型
         * 存储两种不同类型的对象，在运行的对象为apple
         */
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();//存储apple类型的对象
        fruit[1] = new Jonathan();//存储jonathan类型的对象

        /**
         * 将fruit类型的对象放置在数组中在编译期间是允许的
         * 但是在运行时数组处理机制认为处理的是Apple类型，因此会将数组中放置其他类型时抛出异常
         *
         * 这种错误机制在运行时是可以发现的
         * 但是如果我们使用泛型机制可以将这种错误在编译期间进行显示
         */
        try {
            fruit[0] = new Fruit();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            fruit[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
