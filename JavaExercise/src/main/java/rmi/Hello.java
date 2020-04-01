package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author :qiang
 * @date :2019/11/1 下午6:41
 * @description : 定义远程对象接口Hello
 * @other :
 */
public interface Hello extends Remote {

    /**
     * rmi：指的是远程方法的调用
     * 允许运行在一个虚拟机上的对象调用运行在另一个虚拟机上的对象的方法，这两个虚拟机可以在统一台计算中，
     * 也可以在同一个网络中的不同计算机中
     *
     * 远程方法调用RMI是java的标准编程接口
     *
     * 基于注册表registry实现
     *
     * 运行过程：
     * 服务端创建并发布远程对象，注册表处理远程对象地址，客户端通过映射地址从注册表中获取远程对象，然后调用其方法
     */

    public String sayHello() throws RemoteException;
}
