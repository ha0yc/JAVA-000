package io.haoyc.dubbo.hmily.account.db.object;

import java.math.BigDecimal;

public class BalanceAccount {
    private Integer id;

    private Integer userId;

    private BigDecimal rmbBalance;

    private BigDecimal usdBalance;

    private BigDecimal freezedRmbBalance;

    private BigDecimal freezedUsdBalance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getRmbBalance() {
        return rmbBalance;
    }

    public void setRmbBalance(BigDecimal rmbBalance) {
        this.rmbBalance = rmbBalance;
    }

    public BigDecimal getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(BigDecimal usdBalance) {
        this.usdBalance = usdBalance;
    }

    public BigDecimal getFreezedRmbBalance() {
        return freezedRmbBalance;
    }

    public void setFreezedRmbBalance(BigDecimal freezedRmbBalance) {
        this.freezedRmbBalance = freezedRmbBalance;
    }

    public BigDecimal getFreezedUsdBalance() {
        return freezedUsdBalance;
    }

    public void setFreezedUsdBalance(BigDecimal freezedUsdBalance) {
        this.freezedUsdBalance = freezedUsdBalance;
    }
}