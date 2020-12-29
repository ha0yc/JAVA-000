package io.haoyc.dubbo.hmily.account.db.dao;

import io.haoyc.dubbo.hmily.account.db.object.BalanceAccount;
import io.haoyc.dubbo.hmily.account.db.object.BalanceAccountExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BalanceAccountMapper {
    long countByExample(BalanceAccountExample example);

    int deleteByExample(BalanceAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BalanceAccount record);

    int insertSelective(BalanceAccount record);

    List<BalanceAccount> selectByExample(BalanceAccountExample example);

    BalanceAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BalanceAccount record, @Param("example") BalanceAccountExample example);

    int updateByExample(@Param("record") BalanceAccount record, @Param("example") BalanceAccountExample example);

    int updateByPrimaryKeySelective(BalanceAccount record);

    int updateByPrimaryKey(BalanceAccount record);
}