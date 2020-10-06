package designMode.factoryMode.method;

/**
 * @ClassName : WuLingFactory
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class WuLingFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new WuLing();
    }
}
