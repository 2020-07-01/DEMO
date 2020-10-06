package enumPackage;

/**
 * @author :qiang
 * @date :2019/10/19 上午10:25
 * @description :枚举测试类
 * @other :
 */
public class EnumClass {

    public static void main(String[] args) {
        for (Shrubbery item : Shrubbery.values()) {
            System.out.println(item + " ordinal " + item.ordinal());
            //判断两个枚举常量是否有相同的枚举类型使用此方法
            System.out.println(item.getDeclaringClass());//返回枚举常量的枚举类型所对应的类对象
            System.out.println(item.name());//返回枚举名字
            System.out.println(item.equals(Shrubbery.GROIUBD));//判断两个枚举常量是否相等
            System.out.println("--------------------");
        }

        /**
         * valueOf()方法是Enum中定义的静态方法
         * 它可以根据给定的名字返回相应的enum实例
         */

        for (String item : "GROIUBD,VRAWLING,HANGING".split(",")) {
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, item);
            System.out.println(shrubbery.name());
        }



    }
}
