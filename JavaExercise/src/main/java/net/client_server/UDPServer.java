package net.client_server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author :qiang
 * @date :2019/10/12 下午9:12
 * @description :基于UDP进行通信
 * @other :
 */
public class UDPServer {

    public static void main(String[] args) {

        //创建服务端的套接字
        DatagramSocket datagramSocket = null;

        //创建数据包
        DatagramPacket datagramPacket = null;

        try {
            datagramSocket = new DatagramSocket(65001);

            //创建字节数组存储从客户端发送过来的内容
            byte[] buff = new byte[100];

            datagramPacket = new DatagramPacket(buff, buff.length);

            //接受从客户端发送过来的内容，并将其封装到datagramPacket对象中
            datagramSocket.receive(datagramPacket);

            //获取输出
            byte[] data = datagramPacket.getData();
            //将字节数组转换为字符串形式
            String string = new String(data);

            System.out.println(data);

            //将要发送给客户端的字符串转换为字节数组
            byte[] sendToClient = string.getBytes();
            //将接受到的字符串发送给客户端
            DatagramPacket packetToClient = new DatagramPacket(sendToClient, sendToClient.length, datagramPacket.getAddress(), datagramPacket.getPort());


            datagramSocket.send(packetToClient);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
