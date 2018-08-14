package com.lgp.domain;

import java.util.ArrayList;
import java.util.List;

public class ConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeIsNull() {
            addCriterion("default_collect_type is null");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeIsNotNull() {
            addCriterion("default_collect_type is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeEqualTo(String value) {
            addCriterion("default_collect_type =", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeNotEqualTo(String value) {
            addCriterion("default_collect_type <>", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeGreaterThan(String value) {
            addCriterion("default_collect_type >", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("default_collect_type >=", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeLessThan(String value) {
            addCriterion("default_collect_type <", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeLessThanOrEqualTo(String value) {
            addCriterion("default_collect_type <=", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeLike(String value) {
            addCriterion("default_collect_type like", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeNotLike(String value) {
            addCriterion("default_collect_type not like", value, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeIn(List<String> values) {
            addCriterion("default_collect_type in", values, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeNotIn(List<String> values) {
            addCriterion("default_collect_type not in", values, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeBetween(String value1, String value2) {
            addCriterion("default_collect_type between", value1, value2, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultCollectTypeNotBetween(String value1, String value2) {
            addCriterion("default_collect_type not between", value1, value2, "defaultCollectType");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesIsNull() {
            addCriterion("default_favorties is null");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesIsNotNull() {
            addCriterion("default_favorties is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesEqualTo(String value) {
            addCriterion("default_favorties =", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesNotEqualTo(String value) {
            addCriterion("default_favorties <>", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesGreaterThan(String value) {
            addCriterion("default_favorties >", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesGreaterThanOrEqualTo(String value) {
            addCriterion("default_favorties >=", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesLessThan(String value) {
            addCriterion("default_favorties <", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesLessThanOrEqualTo(String value) {
            addCriterion("default_favorties <=", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesLike(String value) {
            addCriterion("default_favorties like", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesNotLike(String value) {
            addCriterion("default_favorties not like", value, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesIn(List<String> values) {
            addCriterion("default_favorties in", values, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesNotIn(List<String> values) {
            addCriterion("default_favorties not in", values, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesBetween(String value1, String value2) {
            addCriterion("default_favorties between", value1, value2, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultFavortiesNotBetween(String value1, String value2) {
            addCriterion("default_favorties not between", value1, value2, "defaultFavorties");
            return (Criteria) this;
        }

        public Criteria andDefaultModelIsNull() {
            addCriterion("default_model is null");
            return (Criteria) this;
        }

        public Criteria andDefaultModelIsNotNull() {
            addCriterion("default_model is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultModelEqualTo(String value) {
            addCriterion("default_model =", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelNotEqualTo(String value) {
            addCriterion("default_model <>", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelGreaterThan(String value) {
            addCriterion("default_model >", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelGreaterThanOrEqualTo(String value) {
            addCriterion("default_model >=", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelLessThan(String value) {
            addCriterion("default_model <", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelLessThanOrEqualTo(String value) {
            addCriterion("default_model <=", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelLike(String value) {
            addCriterion("default_model like", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelNotLike(String value) {
            addCriterion("default_model not like", value, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelIn(List<String> values) {
            addCriterion("default_model in", values, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelNotIn(List<String> values) {
            addCriterion("default_model not in", values, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelBetween(String value1, String value2) {
            addCriterion("default_model between", value1, value2, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andDefaultModelNotBetween(String value1, String value2) {
            addCriterion("default_model not between", value1, value2, "defaultModel");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNull() {
            addCriterion("last_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNotNull() {
            addCriterion("last_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeEqualTo(Long value) {
            addCriterion("last_modify_time =", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotEqualTo(Long value) {
            addCriterion("last_modify_time <>", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThan(Long value) {
            addCriterion("last_modify_time >", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("last_modify_time >=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThan(Long value) {
            addCriterion("last_modify_time <", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThanOrEqualTo(Long value) {
            addCriterion("last_modify_time <=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIn(List<Long> values) {
            addCriterion("last_modify_time in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotIn(List<Long> values) {
            addCriterion("last_modify_time not in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeBetween(Long value1, Long value2) {
            addCriterion("last_modify_time between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotBetween(Long value1, Long value2) {
            addCriterion("last_modify_time not between", value1, value2, "lastModifyTime");
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

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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