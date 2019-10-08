package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.SocketHandler;

/**
 * @author :qiang
 * @date :2019/10/8 下午7:09
 * @description :学习Scoket类
 * @other :
 */
public class StudySocket {

    public static void main(String[] args) throws IOException {

        /**
         * 套接字Socket由ip地址和端口号组成
         *
         */
        //创建一个未被连接的套接字
        Socket socket = new Socket();

        //将该套接字连接到给定的地址
        socket.connect(new InetSocketAddress("time-a.nist.gov", 13));

        /*
         * 设置套接字的超时值
         * 在设置超时值之后,如果读操作和写操作在没有完成之前就超时了,则会抛出一个异常
         */
        socket.setSoTimeout(10000);

        /**
         * 判断套接字是否连接和关闭
         */
        System.out.println("套接字是否关闭:" + socket.isClosed());
        System.out.println("套接字是否连接:" + socket.isConnected());

        /**
         * 在套接字连接之后,可以获取inputStream对象
         */
        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF8");
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            System.out.println(string);
        }

    }
}
