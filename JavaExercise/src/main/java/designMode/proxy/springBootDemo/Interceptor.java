package designMode.proxy.springBootDemo;

/**
 * @ClassName : Interceptor
 * @Author : yq
 * @Date: 2020-11-01
 * @Description : 定义AOP拦截器
 */
public interface Interceptor {


    //事前方法
    public Boolean before();


    //事后方法
    public Boolean after();


    public Object around(Invocation invocation) throws Exception;


    //事后返回方法
    public void afterReturning();


    //事后异常返回方法
    public void afterThrowing();

    //是否使用around方法取代原有方法
    Boolean userAround();
}
