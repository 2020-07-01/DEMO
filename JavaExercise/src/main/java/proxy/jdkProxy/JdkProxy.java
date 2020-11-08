package proxy.jdkProxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName : JdkProxy
 * @Author : yq
 * @Date: 2020-11-03
 * @Description : jdk 动态代理  动态生成代理对象，并为代理对象指定目标对象
 */
public class JdkProxy implements InvocationHandler {

    Object target = null;

    /**
     * 静态代理就是在程序运行前就已经存在编译好的代理类
     * 动态代理就是动态生成代理类
     */

    /**
     * 在目标方法执行前后进行增强
     * 此时 不同的目标类需要不同的增加方法
     * 在aop中将增强方法抽象出来 建立一种约定 ，在代码中通俗写前后增强方法
     *
     * @param proxy  代理对象
     * @param method 目标对象方法
     * @param args   参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理开始......");
        Object o = method.invoke(target, args);
        System.out.println("jdk动态代理结束......");

        return o;
    }

    /**
     * 由目标对象获取代理对象
     *
     * @param targetObject
     * @return
     */
    public Object getProxyObject(Object targetObject) {

        this.target = targetObject;
        //动态生成代理对象，点进去看源码
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);

    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        /**
         * 对电影进行增强
         */
        UserService userService = (UserService) jdkProxy.getProxyObject(new UserServiceImpl());

        userService.addUser();
        userService.deleteUser();
        userService.selectUser();



    }
}
