package io.kimmking.rpcfx.client.agent.bytebuddy;

import io.kimmking.rpcfx.client.agent.RpcFxAgent;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class RpcFxByteBuddyAgent implements RpcFxAgent {
    @Override
    public <T> T create(Class<T> superClass, String url) throws Exception {
        Class<T> dynamicType = (Class<T>) new ByteBuddy()
                .subclass(superClass)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(new RpcfxByteBuddyInterceptor(superClass, url)))
                .make()
                .load(RpcFxByteBuddyAgent.class.getClassLoader())
                .getLoaded();
        return dynamicType.newInstance();
    }
}
