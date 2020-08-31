package designMode.abstractFactory;

/**
 * @ClassName : HuaWeiRuter
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class HuaWeiRouter implements RouterProduct{
    @Override
    public void turnOn() {
        System.out.println("华为路由器开机");
    }

    @Override
    public void setting() {
        System.out.println("华为路由器设置");
    }

    @Override
    public void onWifi() {
        System.out.println("华为路由器俩连接");
    }

    @Override
    public void turnOff() {
        System.out.println("华为路由器关机");
    }
}
