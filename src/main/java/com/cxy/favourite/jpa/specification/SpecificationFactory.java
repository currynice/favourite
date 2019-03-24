package com.cxy.favourite.jpa.specification;

import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.Path;

/**
 * 针对Specification的工具类
 * 方法隐式修饰为final
 */

public final class SpecificationFactory {
    /**
     * 模糊匹配
     * attribute like value
     * @return
     */
    public static Specification containsLike(String attribute,String value){
        return (root,query,cb) -> cb.like(root.get(attribute),"%"+value+"%");
    }

    /**
     * 属性在两值间
     * @param attribute
     * @param min
     * @param max
     * @return
     */
    public static Specification isBetween(String attribute,Long min,Long max){
        return (root,query,cb) ->cb.between(root.get(attribute),min,max);
    }

    /**
     * 枚举值匹配
     * @param <T>
     * @return
     */
    public static  <T extends Enum<T>>  Specification  EnumMatch(String attribute,T queriedValue){
        return (root,query,cb) -> {
            Path<T> actualValue = root.get(attribute);

            if (queriedValue == null) {
                return null;
            }
            return cb.equal(actualValue,queriedValue);
        };
    }
}
