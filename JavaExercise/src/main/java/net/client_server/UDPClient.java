package net.client_server;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author :qiang
 * @date :2019/10/12 下午9:29
 * @description :
 * @other :
 */
public class UDPClient {

    public static void main(String[] args) {

        //创建服务端的套接字
        DatagramSocket socket = null;

        //创建数据包
        DatagramPacket packet = null;

        try {

            //要发送给服务端的数据
            byte[] buf = "hello world".getBytes();

            //创建InetAddress对象
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            //将要发送给服务端的数据封装为DatagramPacket对象
            packet = new DatagramPacket(buf, buf.length, inetAddress, 65001);

            //向服务端发送数据
            socket.send(packet);

            //客户端接受服务端发送过来的数据
            byte[] data = new byte[100];
            //创建datagrampacket对象用来存储服务端发送过来的数据
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);

            socket.receive(datagramPacket);
            //将服务端发送过来的数据打印到控制台
            String content = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            System.out.println(content);
        } catch (Exception e) {

        }
    }
}
