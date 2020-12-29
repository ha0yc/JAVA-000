package io.haoyc.dubbo.hmily.api.service;

import io.haoyc.dubbo.hmily.api.param.AccountTranctionParameter;
import io.haoyc.dubbo.hmily.api.param.AccountTransactionDTO;

public interface AccountService {
    AccountTransactionDTO trade(AccountTranctionParameter atp);

    AccountTransactionDTO list(AccountTranctionParameter atp);
}
