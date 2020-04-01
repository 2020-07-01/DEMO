package dataStructure.performanceTest;

/**
 * @param : C指的是被测容器
 * @author :qiang
 * @date :2019/10/18 下午3:21
 * @description :
 * @other :
 */
public abstract class Test<C> {

    String name;

    public Test(String name) {
        this.name = name;
    }

    abstract int test(C container,TestParam tp);
}
