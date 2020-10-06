package designMode.abstractFactory;

/**
 * @ClassName : HuaWeiFactory
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class HuaWeiFactory implements ProductFactory {
    @Override
    public PhoneProduct getPhone() {
        return new HuaWeiPhone();
    }

    @Override
    public RouterProduct getRouter() {
        return new HuaWeiRouter();
    }


}
