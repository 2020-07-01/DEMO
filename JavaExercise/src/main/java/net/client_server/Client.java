package net.client_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author :qiang
 * @date :2019/10/12 上午10:41
 * @description :创建客户端
 * @other :
 *
 * Socket类：实现客户端的网络通信
 */
public class Client {

    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        //创建客户端
        Socket socket = null;

        try {
            //创建客户端并链接指定主机上的端口号
            socket = new Socket("localhost", 2000);
            //获取输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取输出流
            pw = new PrintWriter(socket.getOutputStream(), true);

            //向服务端发送消息
            pw.println("hello world");

            //接受服务端返回的消息
            String s = null;
            while (true) {
                s = br.readLine();
                if (s != null) {
                    break;
                }
            }
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭链接
                if (br != null) br.close();
                if (pw != null) pw.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
