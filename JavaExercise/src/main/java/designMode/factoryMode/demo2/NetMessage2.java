package designMode.factoryMode.demo2;

/**
 * @author yq
 * @date 2020/8/22 14:20
 */ //实现类
class NetMessage2 implements IMessage2 {

    @Override
    public void receive() {
        System.out.println("【网络消息接受】www.mldn.cn");
    }
}
