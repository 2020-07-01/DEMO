package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @ClassName : NettyServerHandle
 * @Author : yq
 * @Date: 2020-12-02
 * @Description : 自定义handler需要继承Netty规定的handler
 * <p>
 * 服务器端处理器
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送的消息
     *
     * @param ctx 上下文对象，含有pipeline、channel、地址
     * @param msg 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /*System.out.println("服务器读取线程：" + Thread.currentThread().getName());
        System.out.println("看看channel和pipeline的关系");
        *//**
         * pipeline和channel是相互对应的关系
         *//*
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();//底层是双向链表

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的消息是：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端发送的地址是：" + ctx.channel().remoteAddress());*/
    }

    /**
     * 数据读取完毕后进行反馈
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        /**
         * 讲数据写入到缓存并刷新
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 喵", CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
