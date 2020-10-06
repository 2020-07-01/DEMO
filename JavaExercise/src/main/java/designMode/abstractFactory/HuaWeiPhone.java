package designMode.abstractFactory;

/**
 * @ClassName : HuaWeiPhone
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class HuaWeiPhone implements PhoneProduct {
    @Override
    public void turnOn() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void sendMessage() {
        System.out.println("华为手机发短信");
    }

    @Override
    public void callUp() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void turnOff() {
        System.out.println("华为手机关机");
    }
}
