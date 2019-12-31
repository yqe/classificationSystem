package com.nju.classification.conditionSqlQuery;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.criteria.*;

@Data
@AllArgsConstructor
/**
 * 简单条件表达式
 */
public class SimpleCondition implements Condition {

    private String fieldName;

    /**
     * 用于比较的第一个参数
     */
    private Object value;

    /**
     * 用于比较的第二个参数，between的时候用,其他时候为null
     */
    private Object value2;

    private Operator operator;

    @Override
    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path condition = root.get(fieldName);
        switch (operator) {
            case EQ:
                return builder.equal(condition, value);
            case NE:
                return builder.notEqual(condition, value);
            case LIKE:
//                if (!(value instanceof String))
//                    throw new Exception(); //抛String类型错误 exception #todo
                return builder.like(condition, fuzzyStringSplicing((String) value));
            case LT:
                return builder.lessThan(condition, (Comparable) value);
            case GT:
                return builder.greaterThan(condition, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(condition, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(condition, (Comparable) value);
            case BETWEEN:
                return builder.between(condition, (Comparable) value, (Comparable) value2);
            case IN:
                return builder.isMember(value, condition);
            case NOT_IN:
                return builder.isNotMember(value, condition);
        }
        return null;
    }

    private static String fuzzyStringSplicing(String str) {
        String var = "%%%s%%";
        return String.format(var, str);
    }
}
