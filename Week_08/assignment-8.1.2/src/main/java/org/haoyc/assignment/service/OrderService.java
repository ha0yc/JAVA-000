package org.haoyc.assignment.service;

import org.haoyc.assignment.db.dao.ProductOrderMapper;
import org.haoyc.assignment.db.object.ProductOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
public class OrderService {
    @Resource
    private ProductOrderMapper orderMapper;

    public void testInsert() {
        ProductOrder order = new ProductOrder();
        Random random = new Random(100000);
        int id = random.nextInt();
        order.setId(id);
        order.setUserId(id + "");
        order.setCreateTime(new Date());
        order.setOrderNo("xxxx");
        order.setSupplierId("xxxxx");
        order.setStatus(1);
        order.setProductNum(11);
        order.setProductAmountTotal(new BigDecimal("11.11"));
        order.setPayAmountTotal(new BigDecimal("11.11"));
        order.setPayTime(new Date());
        order.setProductId("xxxx");
        orderMapper.insert(order);
    }

    public void testSelect() {

    }
}
