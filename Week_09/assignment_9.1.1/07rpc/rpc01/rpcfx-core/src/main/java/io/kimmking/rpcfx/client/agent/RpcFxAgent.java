package io.kimmking.rpcfx.client.agent;

public interface RpcFxAgent {
    public <T> T create(Class<T> superClass, String url) throws Exception ;
}
