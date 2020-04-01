package exception;

/**
 * @author :qiang
 * @date :2019/10/10 下午2:46
 * @description :
 * @other :
 */
public class TestException {

    public static void f() throws Exception {
        System.out.println("这个方法会抛出异常");
        throw new Exception();
    }


    public static void main(String[] args) throws Exception {

        f();


    }
}
