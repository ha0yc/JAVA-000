package io.github.kimmking.gateway.outbound.httpclient4;


import io.github.kimmking.gateway.outbound.httpclient4.sync.SyncHttpToolkit;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler {
    
    private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyService;
    private String backendUrl;
    
    public HttpOutboundHandler(String backendUrl){
        this.backendUrl = backendUrl.endsWith("/")?backendUrl.substring(0,backendUrl.length()-1):backendUrl;

    }
    
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        fetchGet(fullRequest, ctx, url);
    }

    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String url) {
        FullHttpResponse response = null;
        Map<String, String> headers = new HashMap<>();
        try {
            fullRequest.headers().forEach((k) -> headers.put(k.getKey(), k.getValue()));
            byte[] bytes = SyncHttpToolkit.getInstance().doGet(url, headers).getBytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(bytes));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", bytes.length);

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }
    
//    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final HttpResponse endpointResponse) throws Exception {
//        FullHttpResponse response = null;
//        try {
////            String value = "hello,kimmking";
////            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
////            response.headers().set("Content-Type", "application/json");
////            response.headers().setInt("Content-Length", response.content().readableBytes());
//
//
//            byte[] body = EntityUtils.toByteArray(endpointResponse.getEntity());
////            System.out.println(new String(body));
////            System.out.println(body.length);
//
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));
//
////            for (Header e : endpointResponse.getAllHeaders()) {
////                //response.headers().set(e.getName(),e.getValue());
////                System.out.println(e.getName() + " => " + e.getValue());
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
//            exceptionCaught(ctx, e);
//        } finally {
//            if (fullRequest != null) {
//                if (!HttpUtil.isKeepAlive(fullRequest)) {
//                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//                } else {
//                    //response.headers().set(CONNECTION, KEEP_ALIVE);
//                    ctx.write(response);
//                }
//            }
//            ctx.flush();
//            //ctx.close();
//        }
//
//    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    
}
