package com.nju.classification.conditionSqlQuery;

import java.util.List;

/**
 * 条件构造器
 * 用于创建条件表达式
 */
public class ConditionFactory {

    /**
     * 等于
     * */
    public static SimpleCondition equal(String fieldName, Object value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.EQ);
    }

    /**
     * 不等于
     * */
    public static SimpleCondition notEqual(String fieldName, Object value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.NE);
    }

    /**
     * 模糊匹配
     * */
    public static SimpleCondition like(String fieldName, String value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.LIKE);
    }

    /**
     * 大于
     * */
    public static <T extends Comparable> SimpleCondition greatThan(String fieldName, T value) throws Exception{
        if (value == null)
            throw  new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.GT);
    }

    /**
     * 小于
     * */
    public static <T extends Comparable> SimpleCondition lessThan(String fieldName, T value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.LT);
    }

    /**
     * 大于等于
     * */
    public static <T extends Comparable> SimpleCondition greatThanEqualTo(String fieldName, T value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.GTE);
    }

    /**
     * 小于等于
     * */
    public static <T extends Comparable> SimpleCondition lessThanEqualTo(String fieldName, T value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.LTE);
    }

    /**
     * between
     * */
    public static <T extends Comparable> SimpleCondition between(String fieldName, T value, T value2) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, value2, Condition.Operator.BETWEEN);
    }

    /**
     * 存在于
     * */
    public static <T extends Comparable> SimpleCondition In(String fieldName, List<T> value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.IN);
    }

    /**
     * 不存在于
     * */
    public static <T extends Comparable> SimpleCondition NotIn(String fieldName, List<T> value) throws Exception {
        if (value == null)
            throw new Exception();
        return new SimpleCondition(fieldName, value, null, Condition.Operator.NOT_IN);
    }

    /**
     * 并且
     * */
    public static LogicalCondition and(List<Condition> conditions) throws Exception{
        if (conditions == null)
            throw new Exception();
        return new LogicalCondition(conditions, Condition.Operator.AND);
    }

    /**
     * 或者
     * */
    public static LogicalCondition or(List<Condition> conditions) throws Exception{
        if (conditions == null)
            throw new Exception();
        return new LogicalCondition(conditions, Condition.Operator.OR);
    }
}
