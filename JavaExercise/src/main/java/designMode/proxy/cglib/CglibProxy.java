package designMode.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @ClassName : CglibProxy
 * @Author : yq
 * @Date: 2020-11-08
 * @Description : cglib 测试
 */
public class CglibProxy {


    public static void main(String[] args) {

        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();

        Enhancer enhancer = new Enhancer();
        //设置要代理的类
        enhancer.setSuperclass(Dog.class);
        //设置回调对象
        enhancer.setCallback(myMethodInterceptor);
        //生成代理对象
        Dog dog = (Dog) enhancer.create();

        dog.eat();

    }
}
