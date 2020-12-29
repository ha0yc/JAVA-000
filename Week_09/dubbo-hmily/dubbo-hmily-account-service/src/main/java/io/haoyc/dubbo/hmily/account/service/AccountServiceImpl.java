package io.haoyc.dubbo.hmily.account.service;

import io.haoyc.dubbo.hmily.account.db.dao.BalanceAccountMapper;
import io.haoyc.dubbo.hmily.account.db.object.BalanceAccount;
import io.haoyc.dubbo.hmily.account.db.object.BalanceAccountExample;
import io.haoyc.dubbo.hmily.api.param.AccountTranctionParameter;
import io.haoyc.dubbo.hmily.api.param.AccountTransactionDTO;
import io.haoyc.dubbo.hmily.api.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@DubboService(retries = 0,version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    @Resource
    BalanceAccountMapper balanceAccountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public AccountTransactionDTO trade(AccountTranctionParameter atp) {
        log.info("start to trade transaction:{}", atp);
        BalanceAccount account = getAccountFromDb(atp);
        if ("USD".equals(atp.getCurrency())) {
            account.getUsdBalance().subtract(atp.getCount());
            account.getFreezedUsdBalance().subtract(atp.getCount());
            account.setRmbBalance(account.getRmbBalance().add(atp.getCount().multiply(new BigDecimal(7))));
            balanceAccountMapper.updateByPrimaryKey(account);
            return new AccountTransactionDTO(1, "ok", account);
        } else {
            account.getRmbBalance().subtract(atp.getCount());
            account.getFreezedRmbBalance().subtract(atp.getCount());
            account.setUsdBalance(account.getUsdBalance().add(atp.getCount().divide(new BigDecimal(7))));
            balanceAccountMapper.updateByPrimaryKey(account);
            return new AccountTransactionDTO(1, "ok", account);
        }
    }

    @Override
    public AccountTransactionDTO list(AccountTranctionParameter atp) {
        BalanceAccountExample example = new BalanceAccountExample();
        BalanceAccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(atp.getUserId());
        try {
            List<BalanceAccount> list = balanceAccountMapper.selectByExample(example);
            return new AccountTransactionDTO(1, "ok", list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new AccountTransactionDTO(0, "error", ex.getMessage());
        }
    }

    public boolean confirm(AccountTranctionParameter atp) {
        log.info("start to confirm transaction:{}", atp);
        BalanceAccount account = getAccountFromDb(atp);
        if ("USD".equals(atp.getCurrency())) {
            if (account.getUsdBalance().subtract(account.getFreezedUsdBalance()).subtract(atp.getCount()).compareTo(new BigDecimal(0)) > 0) {
                account.setFreezedUsdBalance(account.getFreezedUsdBalance().add(atp.getCount()));
                return balanceAccountMapper.updateByPrimaryKey(account) > 0;
            } else {
                return false;
            }
        } else {
            if (account.getRmbBalance().subtract(account.getFreezedRmbBalance()).subtract(atp.getCount()).compareTo(new BigDecimal(0)) > 0) {
                account.setFreezedRmbBalance(account.getFreezedRmbBalance().add(atp.getCount()));
                return balanceAccountMapper.updateByPrimaryKey(account) > 0;
            } else {
                return false;
            }
        }
    }

    private BalanceAccount getAccountFromDb(AccountTranctionParameter atp) {
        BalanceAccountExample example = new BalanceAccountExample();
        BalanceAccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(atp.getUserId());
        return balanceAccountMapper.selectByExample(example).get(0);
    }

    public boolean cancel(AccountTranctionParameter atp) {
        log.info("start to cancel transaction:{}", atp);
        BalanceAccount account = getAccountFromDb(atp);
        if ("USD".equals(atp.getCurrency())) {
            account.setFreezedUsdBalance(account.getFreezedUsdBalance().subtract(atp.getCount()));
            return balanceAccountMapper.updateByPrimaryKey(account) > 0;
        } else {
            account.setFreezedRmbBalance(account.getFreezedRmbBalance().subtract(atp.getCount()));
            return balanceAccountMapper.updateByPrimaryKey(account) > 0;
        }
    }
}
