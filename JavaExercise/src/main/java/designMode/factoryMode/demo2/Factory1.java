package designMode.factoryMode.demo2;

/**
 * 工厂类
 * 生产类实例
 */
class Factory1 {
    //构造方法私有化
    private Factory1() {

    }

    /**
     * 通过类名用反射的方法获取类的实例
     *
     *
     * @param className
     * @return
     */
    public static <T> T getInstance(String className) throws Exception {
        T instance = null;
        instance = (T)Class.forName(className).getDeclaredConstructor().newInstance();
        return instance;
    }
}
