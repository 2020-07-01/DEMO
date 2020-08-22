package reflect;

import java.lang.reflect.Constructor;

/**
 * @author yq
 * @date 2020/8/22 13:40
 */
public class ReflectDemo2 {

    public static void main(String[] args) throws Exception{

        Class<?> clazz = Class.forName("reflect.Person");
        /**
         * newInstance()方法调用非私有无参构造方法
         */
        Object object = clazz.newInstance();
        System.out.println(object);

        /**
         * 获取构造器  包括私有的
         */
        Constructor constructor = clazz.getDeclaredConstructor();
        Object object1 = constructor.newInstance();


    }
}
