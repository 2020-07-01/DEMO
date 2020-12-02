package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName : NettyClient
 * @Author : yq
 * @Date: 2020-12-02
 * @Description :
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        //时间循环组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        //创建客户端启动对象
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventExecutors);

        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new NettyClientHandler());
            }
        });

        System.out.println("客户端 Ok...");
        //ChannelFuture 涉及到异步模型
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668);

        //给关闭通道进行监听
        channelFuture.channel().closeFuture().sync();
    }
}
