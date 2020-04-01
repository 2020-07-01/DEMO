package net.url_uri;

import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author :qiang
 * @date :2019/10/13 下午10:22
 * @description : URLConnection
 * @other :
 */
public class StudyURLConnection {

    public static void main(String[] args) {

        URL url = null;

        try {
            //创建一个url地址的对象
            url = new URL("http://www.baidu.com");
            //获取链接对象
            URLConnection connection = url.openConnection();
            //链接远程资源
            connection.connect();//此时与服务器建立链接
            //获取输入流对象，然后进行资源的获取
            InputStream inputStream = connection.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
