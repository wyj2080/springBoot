package com.test.springBoot.common.tools;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author wangyinjia
 * @description 工具类
 * @date 2021/8/9
 */
public class WeUtil {
    public static boolean isBlank(CharSequence cs){
        return StringUtils.isBlank(cs);
    }

    public static boolean isNotBlank(CharSequence cs){
        return StringUtils.isBlank(cs);
    }

    public static boolean isEmpty(Collection coll){
        return CollectionUtils.isEmpty(coll);
    }

    public static boolean isNotEmpty(Collection coll){
        return CollectionUtils.isNotEmpty(coll);
    }

    public static BigDecimal devide(BigDecimal a, BigDecimal b){
        if(a == null || b == null || b.equals(BigDecimal.ZERO)){
            return null;
        }else{
            return a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static BigDecimal devide(BigDecimal a, BigDecimal b, Integer num, Integer pec){
        if(a == null || b == null || b.equals(BigDecimal.ZERO)){
            return null;
        }else{
            return a.divide(b, num, pec);
        }
    }



}
