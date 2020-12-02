package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName : NettyServer
 * @Author : yq
 * @Date: 2020-12-02
 * @Description :
 */
public class NettyServer {


    public static void main(String[] args) throws InterruptedException {


        /**
         * boosGroup 和 workGroup 包含的子线程（NioEventLoop）个数默认为cpu核数 * 2
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        /**
         * workGroup中的线程会进行轮询
         * 并且每个线程有自己的select
         */
        EventLoopGroup workGroup = new NioEventLoopGroup();

        /**
         * 创建服务器端启动参数
         */
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workGroup); //设置两个线程组

        serverBootstrap.channel(NioServerSocketChannel.class); //设置服务器端的通道

        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128); //设置线程队列得到的连接个数

        //给workGroup对应的管道设置处理器
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            //给管道设置处理器
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline().addLast(new NettyServerHandler());
            }
        });

        System.out.println("...服务器准备好了...");

        //启动服务器
        ChannelFuture future = serverBootstrap.bind(6668).sync();

        //对关闭通道进行监听
        future.channel().closeFuture().sync();
    }


}
