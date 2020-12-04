package netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName : TestServer
 * @Author : yq
 * @Date: 2020-12-03
 * @Description :
 */
public class TestServer {

    public static void main(String[] args) {

        try {
            EventLoopGroup boosGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup);

            serverBootstrap.channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());

            ChannelFuture future = serverBootstrap.bind(6678).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
