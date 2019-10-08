package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author :qiang
 * @date :2019/10/8 下午7:48
 * @description :服务器套接字
 * @other :
 */
public class StudyServerSocket {

    public static void main(String[] args) throws IOException {


        //创建负责监控8189端口的服务器套接字
        ServerSocket s = new ServerSocket(8189);

        /**
         * accept()：告诉程序不停的等待,直到有客户端连接到这个端口
         * 一旦有人通过网络发送了正确的请求,连接到此端口上
         * 此方法返回一个表示连接建立的Socket对象
         * 可以使用这个对象获取输入流和输出流
         */
        Socket incoming = s.accept();

        /**
         * 服务器的所有输出流都会成为客户端的输入流
         * 客户端的所有输入流都会成为服务器的输出流
         */
        //获取输入流
        InputStream inputStream = incoming.getInputStream();

        //获取输出流
        OutputStream outputStream = incoming.getOutputStream();


        //创建扫描器和写入器
        Scanner in = new Scanner(inputStream, "UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

        //服务器向客户端发送问候信息
        out.println("hello world!");

        /**
         * 服务端副取客户端的信息
         * 每次只读取一行
         * 当信息为byte时退出
         */
        boolean done = false;
        while (!done && in.hasNextLine()) {

            String line = in.nextLine();
            out.println("Echo:" + line);
            if (line.trim().equals("byte")) {
                done = true;
                //关闭套接字
                s.close();
            }
        }


    }
}
