package designMode.abstractFactory;

/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class JavaDemo {

    public static void main(String[] args) {

        HuaWeiFactory huaWeiFactory = new HuaWeiFactory();
        PhoneProduct huaWeiPhone = huaWeiFactory.getPhone();
        RouterProduct huaWeiRouter = huaWeiFactory.getRouter();

        XiaoMiFactory xiaoMiFactory = new XiaoMiFactory();
        PhoneProduct xiaoMiPhone = xiaoMiFactory.getPhone();
        RouterProduct xiaoMiRouter = xiaoMiFactory.getRouter();

        huaWeiPhone.callUp();
        xiaoMiPhone.callUp();
    }

    /**
     * 抽象工厂
     * 围绕超级工厂创建其他的工厂
     * 1、工厂创建具体产品工厂
     * 2、具体产品工厂再创建具体产品
     *
     * 产品族无法难以进行扩展
     */
}


