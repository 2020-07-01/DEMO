package designMode.abstractFactory;

/**
 * @ClassName : XiaoMiPhone
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class XiaoMiPhone implements PhoneProduct{
    @Override
    public void turnOn() {
        System.out.println("小米手机开机");
    }

    @Override
    public void sendMessage() {
        System.out.println("小米手机发送短信");
    }

    @Override
    public void callUp() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void turnOff() {

        System.out.println("小米手机关机");
    }
}
