package designMode.abstractFactory;

/**
 * @ClassName : XiaoMiRuter
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class XiaoMiRouter implements RouterProduct{
    @Override
    public void turnOn() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void setting() {
        System.out.println("小米路由器设置");
    }

    @Override
    public void onWifi() {
        System.out.println("小米路由器连接");
    }

    @Override
    public void turnOff() {

        System.out.println("小米路由器关机");
    }
}
