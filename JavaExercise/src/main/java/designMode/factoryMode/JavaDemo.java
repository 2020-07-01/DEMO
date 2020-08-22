package designMode.factoryMode;

/**
 * @author yq
 * @date 2020/8/22 13:48
 *
 * 静态工厂模式的设计
 * 1、当接口要追加子类时，必须对工厂类Factory进行修改
 * 2、此种设计不具备单例特性
 *  new 关键字创建对象的弊端在于需要一个明确的类存在
 * 工厂设计模式主要解决子类与客户端的耦合问题
 *
 * 客户端：使用子类的类
 *
 */
public class JavaDemo {
    public static void main(String[] args) {

        IMessage message = Factory.getInstance("netMessage");
        message.send();
    }

}

//创建一个接口
interface IMessage{

    //声名动作,发送消息
    void send();
}

//实现类
class NetMessage implements IMessage{

    @Override
    public void send() {
        System.out.println("【网络消息发送】www.mldn.cn");
    }
}

/**
 * 工厂类
 * 生产类实例
 */
class Factory{
    //构造方法私有化
    private Factory(){

    }

    //获取NetMessage的实例
    public static IMessage getInstance(String className){

        if("NetMessage".equalsIgnoreCase(className)){
            return new NetMessage();//此时不具备单例模式
        }
        return null;
    }
}