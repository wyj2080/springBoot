package com.test.springBoot.common.tools;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.BiFunction;

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

    public static <T> Page<T> createPage(String range) {
        return (Page)resolveRange(range, (pageNo, pageSize) -> {
            return Page.of((long)pageNo + 1L, (long)pageSize);
        });
    }

    private static <R> R resolveRange(String range, BiFunction<Integer, Integer, R> bf) {
        int pageNo = 0;
        int pageSize = 24;

        try {
            int i = range.lastIndexOf("-");
            String str1 = range.substring(range.lastIndexOf("=") + 1, i);
            String str2 = range.substring(i + 1);
            int firstNum = Integer.parseInt(str1);
            int lastNum = Integer.parseInt(str2);
            pageSize = lastNum - firstNum + 1;
            pageNo = firstNum / pageSize;
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return bf.apply(pageNo, pageSize);
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
