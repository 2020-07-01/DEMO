package designMode.factoryMode.demo2;

/**
 * @author yq
 * @date 2020/8/22 13:58
 * 动态工厂模式
 * 通过反射的方法来获取实列
 * 子类增加和接口增加时无需修改工厂类
 * 使用泛型为多个接口提供服务
 */
public class JavaDemo2 {

    public static void main(String[] args) throws Exception{

        IMessage1 message1 = Factory1.getInstance("designMode.factoryMode.demo2.NetMessage1");
        message1.send();

        IMessage2 message2 = Factory1.getInstance("designMode.factoryMode.demo2.NetMessage2");
        message2.receive();


    }
}


