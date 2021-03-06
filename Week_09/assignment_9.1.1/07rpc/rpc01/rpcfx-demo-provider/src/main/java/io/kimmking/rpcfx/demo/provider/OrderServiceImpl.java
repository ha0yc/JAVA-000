package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.server.annotation.Provider;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;

@Provider
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
