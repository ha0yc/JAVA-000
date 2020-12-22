package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.server.annotation.Provider;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

@Provider
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
