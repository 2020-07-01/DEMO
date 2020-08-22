package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author yq
 * @date 2020/8/22 12:36
 * 反射
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {

        Class clazz = Class.forName("reflect.Dog");
        //构造实例
        Object object = clazz.newInstance();

        Field field = clazz.getDeclaredField("type");
        field.setAccessible(true);
        field.getName();
        System.out.println(field.get(object));

        /**
         * 获取构造器
         * 公共构造函数
         */
        Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{String.class,String.class});
        Object object1 = constructor.newInstance("哈士奇","神哈");
        System.out.println(object1);
        if(object1 instanceof Dog){
            System.out.println("YES");
        }




    }



}



