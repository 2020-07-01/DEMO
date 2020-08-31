package designMode.abstractFactory;

/**
 * @ClassName : PhoneFactory
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public interface ProductFactory {

    PhoneProduct getPhone();

    RouterProduct getRouter();
}
