package io.haoyc.dubbo.hmily.api.param;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountTranctionParameter implements Serializable {
    private Integer userId;
    private String transactionType;
    private BigDecimal count;
    private String currency;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
