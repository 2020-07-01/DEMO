package net.bio;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @ClassName : SocektDemo
 * @Author : yq
 * @Date: 2020-12-24
 * @Description : BIO模型
 */
public class SocketServerBioDemo {

    public static void main(String[] args) {

        SocketServerBioDemo main = new SocketServerBioDemo();
        try {
            main.bioThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * BIO模型
     * 同步阻塞
     */
    public void bio() {

        /**
         * BIO模型分析
         * 1.BIO模型存在两处阻塞问题
         *  可用多线程方式解决阻塞，但是为每个请求开启线程会造成资源的浪费，
         *  如果有大量请求进来，并且读数据阻塞时，会造成大量线程阻塞
         造成资源浪费
         */
        try {
            //服务端监听8080端口
            ServerSocket serverSocket = new ServerSocket(8089);
            /**
             * 监听连接到此套接字的连接
             * 获取socket
             * 当没有连接建立时此方法阻塞
             * serverSocket放弃CPU
             */
            Socket socket = serverSocket.accept();
            /**
             * 读取数据
             * 当没有数据进来时会发生阻塞
             */
            int b = socket.getInputStream().read();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("此方法阻塞了......");
    }


    /**
     * BIO模式采用多线程
     * 多个客户端进行连接时，为每一个客户端开启一个线程
     */
    public void bioThread() throws Exception {
        BlockingQueue queue = new DelayQueue();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5000, TimeUnit.SECONDS, queue, threadFactory);
        ServerSocket serverSocket = new ServerSocket(8078);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("客户端建立连接......");
            threadPoolExecutor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("服务端业务逻辑执行.....");
                }
            });
        }
    }

    public void bio2() throws Exception{

        ServerSocket serverSocket = new ServerSocket(89999);
        Socket socket = serverSocket.accept();

        while (true){
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
        }
    }

}
