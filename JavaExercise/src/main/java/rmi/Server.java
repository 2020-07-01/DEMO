package rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author :qiang
 * @date :2019/11/1 下午6:49
 * @description :远程对象服务端的实现
 * @other :
 */
public class Server implements Hello {

    @Override
    public String sayHello() throws RemoteException {

        return "Hello RMI";
    }


    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        //创建远程对象
        Server server = new Server();

        //发布原创对象到RMI
        Hello hello = (Hello) UnicastRemoteObject.exportObject(server, 8080);

        //创建远程对象注册表，是放置所有服务器对象的命名空间表，只接受特定端口上的远程调用
        Registry registry = LocateRegistry.createRegistry(8080);

        //绑定映射地址,注册对象到注册表中
        registry.bind("hello", hello);

        System.out.println("服务端启动成功!");
    }
}
