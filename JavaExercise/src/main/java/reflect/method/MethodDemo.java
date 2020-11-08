package reflect.method;

import java.lang.reflect.Method;

/**
 * @ClassName : MethodDemo
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class MethodDemo {

    public static void main(String[] args) {

        Class clazz = MobileServiceImpl.class;
        try {
            Method method = clazz.getDeclaredMethod("turnOn");
            //实现动态调用类的方法
            method.invoke(clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
