package netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @ClassName : TestServerInitializer
 * @Author : yq
 * @Date: 2020-12-03
 * @Description :
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {



    @Override
    protected void initChannel(SocketChannel ch) {

        //

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("MyHttpServerCode",new HttpServerCodec());

        //增加自己的handler
        pipeline.addLast("MyTestServerHandler",new TestHttpServerHandler());
    }
}
