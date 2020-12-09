package org.haoyc.assignment.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.haoyc.assignment.db.object.ProductOrder;
import org.haoyc.assignment.db.object.ProductOrderExample;

public interface ProductOrderMapper {
    long countByExample(ProductOrderExample example);

    int deleteByExample(ProductOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    List<ProductOrder> selectByExample(ProductOrderExample example);

    ProductOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductOrder record, @Param("example") ProductOrderExample example);

    int updateByExample(@Param("record") ProductOrder record, @Param("example") ProductOrderExample example);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);
}