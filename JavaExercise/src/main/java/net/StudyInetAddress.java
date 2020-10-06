package net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/8 下午1:04
 * @description :ip地址获取测试
 * @other :
 * <p>
 * InetAddress：表示ip地址协议
 */
public class StudyInetAddress {

    public static void main(String[] args) throws UnknownHostException {

        /**
         * InetAddress的作用是主机名和ip地址之间的转换,包括
         */

        //为给定的主机名创建一个对象
        InetAddress inetAddress = InetAddress.getByName("time-a.nist.gov");

        //获取ip地址
        byte[] address = inetAddress.getAddress();

        for (byte item : address) {
            System.out.print(item);
        }

        System.out.println("\n本地ip地址:" + inetAddress.getHostAddress());


        //获取主机名
        System.out.println("获取取主机名：" + inetAddress.getHostName());

        /**
         * 创建本机地址的InetAddress对象
         */
        InetAddress localhostAddress = InetAddress.getLocalHost();

        //获取本地的ip和主机名
        System.out.println("本地ip地址：" + localhostAddress.getHostAddress() + "\n本地主机名：" + localhostAddress.getHostName());

        //返回一个包含ip地址的字节数组
        byte[] ipByte = localhostAddress.getAddress();
        System.out.println("ip地址字节数组：" + Arrays.toString(ipByte));
    }

}
