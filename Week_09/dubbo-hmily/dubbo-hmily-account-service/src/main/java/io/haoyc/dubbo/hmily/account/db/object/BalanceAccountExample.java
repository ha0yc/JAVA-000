package io.haoyc.dubbo.hmily.account.db.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BalanceAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BalanceAccountExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceIsNull() {
            addCriterion("rmb_balance is null");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceIsNotNull() {
            addCriterion("rmb_balance is not null");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceEqualTo(BigDecimal value) {
            addCriterion("rmb_balance =", value, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceNotEqualTo(BigDecimal value) {
            addCriterion("rmb_balance <>", value, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceGreaterThan(BigDecimal value) {
            addCriterion("rmb_balance >", value, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rmb_balance >=", value, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceLessThan(BigDecimal value) {
            addCriterion("rmb_balance <", value, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rmb_balance <=", value, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceIn(List<BigDecimal> values) {
            addCriterion("rmb_balance in", values, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceNotIn(List<BigDecimal> values) {
            addCriterion("rmb_balance not in", values, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rmb_balance between", value1, value2, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andRmbBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rmb_balance not between", value1, value2, "rmbBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceIsNull() {
            addCriterion("usd_balance is null");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceIsNotNull() {
            addCriterion("usd_balance is not null");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceEqualTo(BigDecimal value) {
            addCriterion("usd_balance =", value, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceNotEqualTo(BigDecimal value) {
            addCriterion("usd_balance <>", value, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceGreaterThan(BigDecimal value) {
            addCriterion("usd_balance >", value, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("usd_balance >=", value, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceLessThan(BigDecimal value) {
            addCriterion("usd_balance <", value, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("usd_balance <=", value, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceIn(List<BigDecimal> values) {
            addCriterion("usd_balance in", values, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceNotIn(List<BigDecimal> values) {
            addCriterion("usd_balance not in", values, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usd_balance between", value1, value2, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andUsdBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usd_balance not between", value1, value2, "usdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceIsNull() {
            addCriterion("freezed_rmb_balance is null");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceIsNotNull() {
            addCriterion("freezed_rmb_balance is not null");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceEqualTo(BigDecimal value) {
            addCriterion("freezed_rmb_balance =", value, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceNotEqualTo(BigDecimal value) {
            addCriterion("freezed_rmb_balance <>", value, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceGreaterThan(BigDecimal value) {
            addCriterion("freezed_rmb_balance >", value, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freezed_rmb_balance >=", value, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceLessThan(BigDecimal value) {
            addCriterion("freezed_rmb_balance <", value, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freezed_rmb_balance <=", value, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceIn(List<BigDecimal> values) {
            addCriterion("freezed_rmb_balance in", values, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceNotIn(List<BigDecimal> values) {
            addCriterion("freezed_rmb_balance not in", values, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezed_rmb_balance between", value1, value2, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedRmbBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezed_rmb_balance not between", value1, value2, "freezedRmbBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceIsNull() {
            addCriterion("freezed_usd_balance is null");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceIsNotNull() {
            addCriterion("freezed_usd_balance is not null");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceEqualTo(BigDecimal value) {
            addCriterion("freezed_usd_balance =", value, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceNotEqualTo(BigDecimal value) {
            addCriterion("freezed_usd_balance <>", value, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceGreaterThan(BigDecimal value) {
            addCriterion("freezed_usd_balance >", value, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freezed_usd_balance >=", value, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceLessThan(BigDecimal value) {
            addCriterion("freezed_usd_balance <", value, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freezed_usd_balance <=", value, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceIn(List<BigDecimal> values) {
            addCriterion("freezed_usd_balance in", values, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceNotIn(List<BigDecimal> values) {
            addCriterion("freezed_usd_balance not in", values, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezed_usd_balance between", value1, value2, "freezedUsdBalance");
            return (Criteria) this;
        }

        public Criteria andFreezedUsdBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezed_usd_balance not between", value1, value2, "freezedUsdBalance");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}