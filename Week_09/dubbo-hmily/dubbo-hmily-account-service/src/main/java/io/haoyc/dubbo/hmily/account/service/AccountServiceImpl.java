package io.haoyc.dubbo.hmily.account.service;

import io.haoyc.dubbo.hmily.account.db.dao.BalanceAccountMapper;
import io.haoyc.dubbo.hmily.account.db.object.BalanceAccount;
import io.haoyc.dubbo.hmily.account.db.object.BalanceAccountExample;
import io.haoyc.dubbo.hmily.api.param.AccountTranctionParameter;
import io.haoyc.dubbo.hmily.api.param.AccountTransactionDTO;
import io.haoyc.dubbo.hmily.api.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@DubboService(retries = 0,version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    @Resource
    BalanceAccountMapper balanceAccountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public AccountTransactionDTO trade(AccountTranctionParameter atp) {
        return null;
    }

    @Override
    public AccountTransactionDTO list(AccountTranctionParameter atp) {
        BalanceAccountExample example = new BalanceAccountExample();
        BalanceAccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(1);
        try {
            List<BalanceAccount> list = balanceAccountMapper.selectByExample(example);
            return new AccountTransactionDTO(1, "ok", list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new AccountTransactionDTO(0, "error", ex.getMessage());
        }

    }

    public void confirm(AccountTranctionParameter atp) {
        System.out.println(" confirm hello world");
    }

    public void cancel(AccountTranctionParameter atp) {
        System.out.println(" cancel hello world");
    }
}
