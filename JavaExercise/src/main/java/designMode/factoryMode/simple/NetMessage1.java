package designMode.factoryMode.simple;

/**
 * @author yq
 * @date 2020/8/22 14:20
 */
class NetMessage1 implements IMessage1 {

    @Override
    public void send() {
        System.out.println("【网络消息发送】www.mldn.cn");
    }
}
