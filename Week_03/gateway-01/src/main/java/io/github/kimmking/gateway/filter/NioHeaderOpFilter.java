package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class NioHeaderOpFilter implements HttpRequestFilter{
    @Override
    public void filter(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        fullRequest.headers().add("nio", "ha0yc");
    }
}
