package designMode.abstractFactory;

/**
 * @ClassName : XiaoMiFactory
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class XiaoMiFactory implements ProductFactory {
    @Override
    public PhoneProduct getPhone() {
        return new XiaoMiPhone();
    }

    @Override
    public RouterProduct getRouter() {
        return new XiaoMiRouter();
    }


}
