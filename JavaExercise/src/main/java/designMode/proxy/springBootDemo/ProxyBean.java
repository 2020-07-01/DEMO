package designMode.proxy.springBootDemo;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName : ProxyBean
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class ProxyBean implements InvocationHandler {

    //目标对象
    private Object target = null;

    //拦截器
    private Interceptor interceptor = null;


    /**
     * 生成代理对象
     *
     * @param target
     * @param interceptor
     * @return
     */
    public static Object getProxyBean(Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();

        //保存目标对象
        proxyBean.target = target;

        //保存拦截器
        proxyBean.interceptor = interceptor;

        //生成代理对象
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);

        return proxy;
    }

    /**
     * 处理代理对象的方法逻辑
     *
     * @param proxy  代理对象
     * @param method 当前方法
     * @param args   参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Invocation invocation = new Invocation(target, method, args);

        Boolean exceptionFlag = false;

        Object retObj = null;

        try {
            if (this.interceptor.userAround()) {
                //用around方法代替目标方法
                retObj = this.interceptor.around(invocation);
            } else {
                retObj = method.invoke(target, args);
            }

        } catch (Exception e) {
            exceptionFlag = true;
        }

        this.interceptor.after();

        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
