package designMode.proxy.springBootDemo;

/**
 * @ClassName : HelloServiceImpl
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        if (name == null || name.trim().equals("")) {
            throw new RuntimeException("parameter is null!!!");
        } else {
            System.out.println("hello " + name);
        }
    }
}
