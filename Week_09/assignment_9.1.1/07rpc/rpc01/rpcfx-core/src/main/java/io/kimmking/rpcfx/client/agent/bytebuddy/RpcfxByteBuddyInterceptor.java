package io.kimmking.rpcfx.client.agent.bytebuddy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.protocol.http.netty.NettyClient;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class RpcfxByteBuddyInterceptor {
    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }
    private final Class<?> serviceClass;
    private final String url;

    public RpcfxByteBuddyInterceptor(Class<?> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    @RuntimeType
    public Object stub(@AllArguments Object[] allArguments, @Origin Method method) throws Exception {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(allArguments);


        //okhttp client
//        RpcfxResponse response = post(request, url);
        //netty client
        RpcfxResponse response = NettyClient.post(url, request);

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parseObject(response.getResult().toString(), (Type) method.getReturnType());
    }
}
