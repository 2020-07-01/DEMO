package net.url_uri;

import java.net.URL;

/**
 * @author :qiang
 * @date :2019/10/12 下午10:51
 * @description : 学习URL类
 * @other :
 */
public class StudyURL {

    public static void main(String[] args) {

        URL url = null;

        try {
            //创建一个url地址的对象
            url = new URL("http://www.baidu.com");

            System.out.println("获取url的端口号：" + url.getPort());
            System.out.println("获取url的协议：" + url.getProtocol());
            System.out.println("获取url的文件名：" + url.getFile());
            System.out.println("获取url的主机名：" + url.getHost());
            System.out.println("获取url的内容：" + url.getContent());
            System.out.println("获取文件路径：" + url.getPath());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
