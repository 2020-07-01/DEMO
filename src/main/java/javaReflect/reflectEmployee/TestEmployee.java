package javaReflect.reflectEmployee;

import java.io.FilenameFilter;
import java.lang.reflect.Field;

/**
 * @author :qiang
 * @date :2019/10/22 下午6:56
 * @description :
 * @other :
 */
public class TestEmployee {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Employee yq = new Employee("yq", 35000, 2019, 10, 3);

        Class c1 = yq.getClass();
        Field f = c1.getDeclaredField("name");//返回指定名称的公有域
        f.setAccessible(true);
        Object v = f.get(yq);

    }
}
