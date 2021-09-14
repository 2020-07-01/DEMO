package enumsustom;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/19 下午2:40
 * @description : 测试values()方法
 * @other :
 */
public enum StudyValues {

    /**
     * 枚举类型继承自Enum
     * 在创建枚举类型的时候，编译器会自动创建特殊的静态方法values()来返回一个枚举类型的数组
     * 这个数组包含所有的枚举类型按照被定义的顺序
     */
    a, b, c, d;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(values()));


        System.out.println("第一个枚举实例："+values()[0]);
        System.out.println("最后一个枚举实例："+values()[3]);
    }
}
