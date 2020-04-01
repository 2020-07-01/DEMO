package rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author :qiang
 * @date :2019/11/1 下午7:01
 * @description :
 * @other :
 */
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // 获取注册表
        Registry registry = LocateRegistry.getRegistry(8080);
        // 通过远程对象的引用地址查找对象
        Hello hello = (Hello) registry.lookup("hello");
        //Naming.lookup()
        // 调用方法
        String content = hello.sayHello();

        System.out.printf("content:" + content);
    }
}
