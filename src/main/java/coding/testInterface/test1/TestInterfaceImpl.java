package coding.testInterface.test1;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午7:31
 * @description :TestInterface接口的实现类
 * @other :
 */
public class TestInterfaceImpl implements TestInterface, ParentInterface {


    //接口中的同名默认方法
    public void test3() {
        System.out.println("实现类中的同名默认方法");
    }

    public void test(){
        ParentInterface.test();
    }

    public static void main(String[] args) {

        TestInterfaceImpl T = new TestInterfaceImpl();
        //此时实现的是
        T.test();

    }


}
