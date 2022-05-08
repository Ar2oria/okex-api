package cc.w0rm.crypto.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
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

        public Criteria andBizIdIsNull() {
            addCriterion("biz_id is null");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNotNull() {
            addCriterion("biz_id is not null");
            return (Criteria) this;
        }

        public Criteria andBizIdEqualTo(String value) {
            addCriterion("biz_id =", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotEqualTo(String value) {
            addCriterion("biz_id <>", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThan(String value) {
            addCriterion("biz_id >", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThanOrEqualTo(String value) {
            addCriterion("biz_id >=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThan(String value) {
            addCriterion("biz_id <", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThanOrEqualTo(String value) {
            addCriterion("biz_id <=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLike(String value) {
            addCriterion("biz_id like", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotLike(String value) {
            addCriterion("biz_id not like", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdIn(List<String> values) {
            addCriterion("biz_id in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotIn(List<String> values) {
            addCriterion("biz_id not in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdBetween(String value1, String value2) {
            addCriterion("biz_id between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotBetween(String value1, String value2) {
            addCriterion("biz_id not between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNull() {
            addCriterion("inst_id is null");
            return (Criteria) this;
        }

        public Criteria andInstIdIsNotNull() {
            addCriterion("inst_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstIdEqualTo(String value) {
            addCriterion("inst_id =", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotEqualTo(String value) {
            addCriterion("inst_id <>", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThan(String value) {
            addCriterion("inst_id >", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("inst_id >=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThan(String value) {
            addCriterion("inst_id <", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLessThanOrEqualTo(String value) {
            addCriterion("inst_id <=", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdLike(String value) {
            addCriterion("inst_id like", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotLike(String value) {
            addCriterion("inst_id not like", value, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdIn(List<String> values) {
            addCriterion("inst_id in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotIn(List<String> values) {
            addCriterion("inst_id not in", values, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdBetween(String value1, String value2) {
            addCriterion("inst_id between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andInstIdNotBetween(String value1, String value2) {
            addCriterion("inst_id not between", value1, value2, "instId");
            return (Criteria) this;
        }

        public Criteria andBarIsNull() {
            addCriterion("bar is null");
            return (Criteria) this;
        }

        public Criteria andBarIsNotNull() {
            addCriterion("bar is not null");
            return (Criteria) this;
        }

        public Criteria andBarEqualTo(String value) {
            addCriterion("bar =", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotEqualTo(String value) {
            addCriterion("bar <>", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarGreaterThan(String value) {
            addCriterion("bar >", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarGreaterThanOrEqualTo(String value) {
            addCriterion("bar >=", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarLessThan(String value) {
            addCriterion("bar <", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarLessThanOrEqualTo(String value) {
            addCriterion("bar <=", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarLike(String value) {
            addCriterion("bar like", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotLike(String value) {
            addCriterion("bar not like", value, "bar");
            return (Criteria) this;
        }

        public Criteria andBarIn(List<String> values) {
            addCriterion("bar in", values, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotIn(List<String> values) {
            addCriterion("bar not in", values, "bar");
            return (Criteria) this;
        }

        public Criteria andBarBetween(String value1, String value2) {
            addCriterion("bar between", value1, value2, "bar");
            return (Criteria) this;
        }

        public Criteria andBarNotBetween(String value1, String value2) {
            addCriterion("bar not between", value1, value2, "bar");
            return (Criteria) this;
        }

        public Criteria andBeginIsNull() {
            addCriterion("begin is null");
            return (Criteria) this;
        }

        public Criteria andBeginIsNotNull() {
            addCriterion("begin is not null");
            return (Criteria) this;
        }

        public Criteria andBeginEqualTo(Long value) {
            addCriterion("begin =", value, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginNotEqualTo(Long value) {
            addCriterion("begin <>", value, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginGreaterThan(Long value) {
            addCriterion("begin >", value, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginGreaterThanOrEqualTo(Long value) {
            addCriterion("begin >=", value, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginLessThan(Long value) {
            addCriterion("begin <", value, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginLessThanOrEqualTo(Long value) {
            addCriterion("begin <=", value, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginIn(List<Long> values) {
            addCriterion("begin in", values, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginNotIn(List<Long> values) {
            addCriterion("begin not in", values, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginBetween(Long value1, Long value2) {
            addCriterion("begin between", value1, value2, "begin");
            return (Criteria) this;
        }

        public Criteria andBeginNotBetween(Long value1, Long value2) {
            addCriterion("begin not between", value1, value2, "begin");
            return (Criteria) this;
        }

        public Criteria andEndIsNull() {
            addCriterion("end is null");
            return (Criteria) this;
        }

        public Criteria andEndIsNotNull() {
            addCriterion("end is not null");
            return (Criteria) this;
        }

        public Criteria andEndEqualTo(Long value) {
            addCriterion("end =", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotEqualTo(Long value) {
            addCriterion("end <>", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThan(Long value) {
            addCriterion("end >", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThanOrEqualTo(Long value) {
            addCriterion("end >=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThan(Long value) {
            addCriterion("end <", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThanOrEqualTo(Long value) {
            addCriterion("end <=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndIn(List<Long> values) {
            addCriterion("end in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotIn(List<Long> values) {
            addCriterion("end not in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndBetween(Long value1, Long value2) {
            addCriterion("end between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotBetween(Long value1, Long value2) {
            addCriterion("end not between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andTaskLimitIsNull() {
            addCriterion("task_limit is null");
            return (Criteria) this;
        }

        public Criteria andTaskLimitIsNotNull() {
            addCriterion("task_limit is not null");
            return (Criteria) this;
        }

        public Criteria andTaskLimitEqualTo(Integer value) {
            addCriterion("task_limit =", value, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitNotEqualTo(Integer value) {
            addCriterion("task_limit <>", value, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitGreaterThan(Integer value) {
            addCriterion("task_limit >", value, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_limit >=", value, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitLessThan(Integer value) {
            addCriterion("task_limit <", value, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitLessThanOrEqualTo(Integer value) {
            addCriterion("task_limit <=", value, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitIn(List<Integer> values) {
            addCriterion("task_limit in", values, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitNotIn(List<Integer> values) {
            addCriterion("task_limit not in", values, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitBetween(Integer value1, Integer value2) {
            addCriterion("task_limit between", value1, value2, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("task_limit not between", value1, value2, "taskLimit");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsIsNull() {
            addCriterion("task_threads is null");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsIsNotNull() {
            addCriterion("task_threads is not null");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsEqualTo(Integer value) {
            addCriterion("task_threads =", value, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsNotEqualTo(Integer value) {
            addCriterion("task_threads <>", value, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsGreaterThan(Integer value) {
            addCriterion("task_threads >", value, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_threads >=", value, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsLessThan(Integer value) {
            addCriterion("task_threads <", value, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsLessThanOrEqualTo(Integer value) {
            addCriterion("task_threads <=", value, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsIn(List<Integer> values) {
            addCriterion("task_threads in", values, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsNotIn(List<Integer> values) {
            addCriterion("task_threads not in", values, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsBetween(Integer value1, Integer value2) {
            addCriterion("task_threads between", value1, value2, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskThreadsNotBetween(Integer value1, Integer value2) {
            addCriterion("task_threads not between", value1, value2, "taskThreads");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalIsNull() {
            addCriterion("task_interval is null");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalIsNotNull() {
            addCriterion("task_interval is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalEqualTo(Long value) {
            addCriterion("task_interval =", value, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalNotEqualTo(Long value) {
            addCriterion("task_interval <>", value, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalGreaterThan(Long value) {
            addCriterion("task_interval >", value, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalGreaterThanOrEqualTo(Long value) {
            addCriterion("task_interval >=", value, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalLessThan(Long value) {
            addCriterion("task_interval <", value, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalLessThanOrEqualTo(Long value) {
            addCriterion("task_interval <=", value, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalIn(List<Long> values) {
            addCriterion("task_interval in", values, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalNotIn(List<Long> values) {
            addCriterion("task_interval not in", values, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalBetween(Long value1, Long value2) {
            addCriterion("task_interval between", value1, value2, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andTaskIntervalNotBetween(Long value1, Long value2) {
            addCriterion("task_interval not between", value1, value2, "taskInterval");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(Date value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(Date value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(Date value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(Date value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<Date> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<Date> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(Date value1, Date value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
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