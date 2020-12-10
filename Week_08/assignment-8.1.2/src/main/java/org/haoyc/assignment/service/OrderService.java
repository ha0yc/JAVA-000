package org.haoyc.assignment.service;

import org.haoyc.assignment.db.dao.ProductOrderMapper;
import org.haoyc.assignment.db.object.ProductOrder;
import org.haoyc.assignment.db.object.ProductOrderExample;


public class OrderService {
    private ProductOrderMapper orderMapper;

    public OrderService(ProductOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void testInsert(int id, int userId) {
        ProductOrder order = new ProductOrder();
        order.setId(id);
        order.setUserId(userId);
        orderMapper.insertSelective(order);
    }

    public ProductOrder testSelect(int id, int userId) {
        ProductOrderExample example = new ProductOrderExample();
        ProductOrderExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(id);
        criteria.andUserIdEqualTo(userId);

        return orderMapper.selectByExample(example).get(0);
    }

    public void testDelete(int id, int userId) {
        ProductOrderExample example = new ProductOrderExample();
        ProductOrderExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(id);
        criteria.andUserIdEqualTo(userId);

        orderMapper.deleteByExample(example);
    }
}
