package designMode.singlePattern;

import java.lang.reflect.Constructor;

/**
 * @Author : yq
 * @Date: 2020-08-30
 * @Description : 枚举方式
 */
public enum Singleton4 {

    INSTANCE;

    public Singleton4 getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws Exception{

        Singleton4 instance1 = Singleton4.INSTANCE;
        Constructor<Singleton4> constructor = Singleton4.class.getDeclaredConstructor(null);

        constructor.setAccessible(true);
        Singleton4 instance2 = constructor.newInstance(String.class,int.class);
        System.out.println(instance1.hashCode());


    }
}
