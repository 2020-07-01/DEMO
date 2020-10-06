package designMode.factoryMode.method;

/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class JavaDemo {
    public static void main(String[] args) {

        Car wuLing = new WuLingFactory().getCar();

        Car tesla = new TeslaFactory().getCar();


        wuLing.name();
        tesla.name();


        /**
         * 工厂方法模式
         * 1、符合开闭原则
         * 2、可横向进行扩展
         *
         * 简单工厂
         * 1、扩展时破坏开闭原则
         * 2、代码简单
         *
         *
         */
    }
}
