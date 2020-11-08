package reflect.method;

/**
 * @ClassName : MobileServiceImpl
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class MobileServiceImpl implements MobileService {

    @Override
    public void turnOn() {
        System.out.println("开机......");
    }

    @Override
    public void makePhone() {
        System.out.println("打电话......");
    }

    @Override
    public void sendMessage() {
        System.out.println("发短信......");
    }

    @Override
    public void turnOff() {
        System.out.println("关机......");
    }
}
