package proxy.springBootDemo;

import rmi.Hello;

/**
 * @ClassName : TestProxy
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class TestProxy {

    public static void main(String[] args) {

        HelloService helloService = new HelloServiceImpl();

        //按照约定获取代理对象
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());

        proxy.sayHello("world");
    }
}
