package io.kimmking.rpcfx.client.protocol.http.netty;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.URI;

public class NettyClient {
    private static RpcfxResponse response;
    public static RpcfxResponse post(String url, RpcfxRequest rpcfxRequest) throws Exception {
        URI uri = new URI(url);
        String host = uri.getHost();
        int port = uri.getPort();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //包含编码器和解码器
                            socketChannel.pipeline().addLast(new HttpClientCodec());

                            //聚合
                            socketChannel.pipeline().addLast(new HttpObjectAggregator(1024 * 10 * 1024));

                            //解压
                            socketChannel.pipeline().addLast(new HttpContentDecompressor());
                            socketChannel.pipeline().addLast(new RpcHttpHandler(rpcfxRequest));
                        }
                    });
            ChannelFuture f = bootstrap.connect().sync();
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

        return response;
    }

    public static class RpcHttpHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
        private RpcfxRequest rpcfxRequest;

        public RpcHttpHandler(RpcfxRequest rpcfxRequest) {
            this.rpcfxRequest = rpcfxRequest;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            FullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_0, HttpMethod.POST, "/", Unpooled.wrappedBuffer(JSON.toJSONBytes(rpcfxRequest)));

            request.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8")
                    //开启长连接
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                    //设置传递请求内容的长度
                    .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());

            //发送数据
            ctx.writeAndFlush(request);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
            ByteBuf content = fullHttpResponse.content();
            String respJson = content.toString(CharsetUtil.UTF_8);
            response = JSON.parseObject(respJson, RpcfxResponse.class);
        }
    }
}
