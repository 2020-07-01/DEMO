package netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @ClassName : TestHttpServerHandler
 * @Author : yq
 * @Date: 2020-12-03
 * @Description :
 */
/*
HttpObject：客户端和服务端通讯数据的封装体
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpObject){
            System.out.println("msg类型：" + msg.getClass());

            System.out.println("客户端地址：" + ctx.channel().remoteAddress());

            //过滤特定资源
            HttpRequest httpRequest = (HttpRequest) msg;

            //回复信息给浏览器，许满足http协议，需要进行封装
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);

            //构造response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
