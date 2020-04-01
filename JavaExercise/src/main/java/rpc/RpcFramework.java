package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author :qiang
 * @date :2019/11/8 下午4:17
 * @description : RPC框架
 * @other :
 */
public class RpcFramework {

    public static void publish(final Object service, int port) throws Exception {
        if (service == null)
            throw new IllegalAccessException("发布的服务不能为空!");
        if (port <= 0 || port > 65535) {
            throw new IllegalAccessException("端口不合法 " + port);
        }

        //创建服务端socket
        ServerSocket server = new ServerSocket(port);
        while (true) {
            try {
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                try {
                                    String methodName = input.readUTF();
                                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                                    Object[] arguments = (Object[]) input.readObject();
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    try {
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        Object result = method.invoke(service, arguments);
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    //动态代理机制
    public static <T> T  call(final Class<?> interfaceClass,String host,int port){
        if(interfaceClass==null){
            throw new IllegalArgumentException("调用服务为空");
        }
        if(host==null||host.length()==0){
            throw new IllegalArgumentException("主机不能为null");
        }
        if(port<=0||port>65535){
            throw new IllegalArgumentException("端口不合法"+port);
        }

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[]{interfaceClass},new CallerHandler(host,port));
    }


    static class CallerHandler implements InvocationHandler {
        private String host;
        private int port;
        public CallerHandler(String host, int port) {
            this.host = host;
            this.port = port;
            //SERVER = new InetSocketAddress(host, port);
        }

        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {

            Socket socket = new Socket(host, port);
            try {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                try {
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(arguments);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    try {
                        Object result = input.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;

                    } finally {

                    }

                } finally {

                }

            } finally {

            }
        }
    }

}
