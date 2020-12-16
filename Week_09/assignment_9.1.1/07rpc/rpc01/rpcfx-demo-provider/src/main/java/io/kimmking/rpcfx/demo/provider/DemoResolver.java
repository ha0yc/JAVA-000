package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.annotation.Provider;
import io.kimmking.rpcfx.api.RpcfxResolver;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DemoResolver implements RpcfxResolver, InitializingBean {
    private final String providePackages;
    private Map<String, Object> providerMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Reflections reflections = new Reflections(providePackages);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Provider.class);

        classes.stream().forEach(c -> {
            Class[] interfaces = c.getInterfaces();
            try {
                if (interfaces.length == 0) {
                    providerMap.put(c.getName(), c.newInstance());
                } else {
                    providerMap.put(interfaces[0].getName(), c.newInstance());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public DemoResolver(String providePackages) {
        this.providePackages = providePackages;
    }

    @Override
    public Object resolve(String serviceClass) {
        return this.providerMap.get(serviceClass);
    }

}
