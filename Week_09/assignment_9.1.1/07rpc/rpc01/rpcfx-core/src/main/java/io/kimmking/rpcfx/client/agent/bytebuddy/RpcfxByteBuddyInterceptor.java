package io.kimmking.rpcfx.client.agent.bytebuddy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import okhttp3.MediaType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static io.kimmking.rpcfx.client.Rpcfx.RpcfxInvocationHandler.post;

public class RpcfxByteBuddyInterceptor {
    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }
    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private final Class<?> serviceClass;
    private final String url;

    public RpcfxByteBuddyInterceptor(Class<?> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    @RuntimeType
    public Object stub(@AllArguments Object[] allArguments, @Origin Method method) throws IOException {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(allArguments);


        RpcfxResponse response = post(request, url);

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parseObject(response.getResult().toString(), (Type) method.getReturnType());
    }
}
