package designMode.factoryMode.method;

/**
 * @ClassName : TeslaFactory
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class TeslaFactory implements CarFactory{

    @Override
    public Car getCar() {
        return new Tesla();
    }
}
