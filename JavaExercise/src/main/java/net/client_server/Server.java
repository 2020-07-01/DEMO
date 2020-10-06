package net.client_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author :qiang
 * @date :2019/10/12 上午10:28
 * @description ：创建服务端
 * @other :
 * <p>
 * ServerSocket:实现服务端的网络通信
 */
public class Server {

    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;

        //创建服务端
        ServerSocket server = null;

        try {
            //创建监听2000端口的服务器
            server = new ServerSocket(2000);
            //监听2000端口，等待链接
            Socket socket = server.accept();
            //获取输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取输出流
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //获取接受的数据
            String s = br.readLine();
            //将接受到的数据发送给客户端
            pw.println(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //关闭链接
                if (br != null) br.close();
                if (pw != null) pw.close();
                if (server != null) server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
