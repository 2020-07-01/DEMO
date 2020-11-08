package proxy.springBootDemo;

/**
 * @ClassName : MyInterceptor
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class MyInterceptor implements Interceptor {
    @Override
    public Boolean before() {
        System.out.println("before......");
        return true;
    }

    @Override
    public Boolean after() {
        System.out.println("after......");
        return true;
    }

    @Override
    public Object around(Invocation invocation) throws Exception {

        System.out.println("around before ......");
        Object o = invocation.proceed();
        System.out.println("around after ......");

        return o;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning......");

    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing......");
    }


    @Override
    public Boolean userAround() {
        /**
         * 使用around方法来代替原有方法(目标方法)
         */
        return true;
    }
}
