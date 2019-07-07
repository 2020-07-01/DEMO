package coding.testInterface.test1;

public interface TestInterface {

    //接口中实现静态方法
    public static void test() {
        System.out.println("test接口");
    }

    //接口中的默认方法
    default void test2() {
        System.out.println("default method11");
    }


    //接口中的同名默认方法
    default void test3() {
        System.out.println("子接口中的同名默认方法");
    }


}
