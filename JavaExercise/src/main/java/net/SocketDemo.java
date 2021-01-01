package net;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName : SocketDemo
 * @Author : yq
 * @Date: 2020-12-23
 * @Description :
 */
public class SocketDemo {

    public static void main(String[] args) {
        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞模式，accept会快速返回
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));

            Selector selector = Selector.open();

            //设置为可接受状态
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                //阻塞1秒钟，返回已就绪的key数量
                if (selector.select(1000) == 0) {
                    continue;
                }
                //已就绪的键集合
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //如果是接受事件
                    if (selectionKey.isAcceptable()) {
                        /**
                         * 此处客户端连接成功，生成socketChannel，进行selector注册即可
                         */
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    //如果时读取事件
                    if (selectionKey.isReadable()) {
                        //逻辑处理
                    }
                    //如果是写事件
                    if (selectionKey.isWritable()) {
                        //逻辑处理
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
