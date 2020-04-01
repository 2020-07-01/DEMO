package exception;

import java.net.InetAddress;

/**
 * @author :qiang
 * @date :2019/10/9 下午4:49
 * @description :
 * @other :
 */
public class InheritingExceptions {

    public void f() throws SimpleException {

        System.out.println("throw simpleException from f()");
        throw new SimpleException();//抛出一个异常
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {//接受异常
            System.out.println("catch it!");
        }


    }
}
