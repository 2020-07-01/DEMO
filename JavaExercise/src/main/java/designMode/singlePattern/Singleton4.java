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


    }
}
