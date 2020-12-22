package io.kimmking.rpcfx.demo.consumer;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.client.agent.bytebuddy.RpcFxByteBuddyAgent;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user
	//

	public static void main(String[] args) throws Exception{
		String url = "http://localhost:8080/";
		UserService userService = new RpcFxByteBuddyAgent().create(UserService.class, url);
		User user = userService.findById(1);
		System.out.println(JSON.toJSONString(user));

		OrderService orderService = new RpcFxByteBuddyAgent().create(OrderService.class, url);
		Order order = orderService.findOrderById(1992129);
		System.out.println(JSON.toJSONString(order));

	}

}
