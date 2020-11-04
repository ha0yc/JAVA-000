package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8));
        
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        System.out.println("");
        
    }
}