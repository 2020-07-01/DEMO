package reflect.method;

/**
 * @ClassName : MobileService
 * @Author : yq
 * @Date: 2020-11-01
 * @Description : 手机服务
 */
public interface MobileService {

    /**
     * 开机
     */
    void turnOn();

    /**
     * 打电话
     */
    void makePhone();

    /**
     * 发短信
     */
    void sendMessage();

    /**
     * 关机
     */
    void turnOff();
}
