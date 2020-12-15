package org.haoyc.assignment.service;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.haoyc.assignment.db.dao.ProductOrderMapper;
import org.haoyc.assignment.db.object.ProductOrder;
import org.haoyc.assignment.db.object.ProductOrderExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@EnableTransactionManagement
@Service
public class XaOrderService {
    @Resource
    private ProductOrderMapper orderMapper;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public TransactionType insertSucc(int id, int userId) {
        doInsert(id, userId);
        TransactionType tp = TransactionTypeHolder.get();
        return tp;
    }

    private void doInsert(int id, int userId) {
        ProductOrder order = new ProductOrder();
        order.setId(id);
        order.setUserId(userId);
        orderMapper.insertSelective(order);
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insertFailed(int id, int userId) throws SQLException{
        System.out.println("==================开始插入第1条数据================");
        doInsert(id, userId);
        System.out.println("==================开始插入第2条数据================");
        doInsert(id, userId);
    }

    public void testDelete(int id, int userId) {
        ProductOrderExample example = new ProductOrderExample();
        ProductOrderExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(id);
        criteria.andUserIdEqualTo(userId);

        orderMapper.deleteByExample(example);
    }

    public ProductOrder testSelect(int id, int userId) {
        ProductOrderExample example = new ProductOrderExample();
        ProductOrderExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(id);
        criteria.andUserIdEqualTo(userId);
        List<ProductOrder> list = orderMapper.selectByExample(example);

        return list.isEmpty() ? null : list.get(0);
    }
}
