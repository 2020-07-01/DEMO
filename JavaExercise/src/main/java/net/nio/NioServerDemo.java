/*
package net.nio.net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.nio.channels.SelectionKey;
import java.net.nio.channels.Selector;
import java.net.nio.channels.ServerSocketChannel;
import java.net.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

*/
/**
 * @ClassName : NioServerDemo
 * @Author : yq
 * @Date: 2020-12-23
 * @Description :
 *//*

public class NioServerDemo {

    public static void main(String[] args) {




        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(8889));

        Selector selector = new Selector();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
         if(selector.select(1000 * 4) == 0){
             System.out.println("服务端在等待。。。。。。");
             continue;
         }
         Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
        }

    }
}
*/
