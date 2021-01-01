package net;

import com.sun.javafx.image.ByteToBytePixelConverter;
import io.netty.buffer.ByteBuf;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;

/**
 * @author :qiang
 * @date :2019/10/8 下午1:04
 * @description :ip地址获取测试
 * @other :
 * <p>
 * InetAddress：表示ip地址协议
 */
public class SocketNioDemo {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞模式，accept会快速返回
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8090));
        serverSocketChannel.configureBlocking(false);
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);

        System.out.println("步阻塞......");
    }

}
