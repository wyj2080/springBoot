package com.test.springBoot.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 导购
 * @Author: wangyinjia
 * @Date: 2020/3/11
 * @Version: 1.0
 */
@Data
public class SellerErpDO {
    @JSONField(name ="NAME")
    private String name;
    private String NO;
    private String C_PROVINCE_name;
    private String ADDRESS;
    private Date BIRTHDATE;
    private String HANDSET;
    private Integer SEX;
    private String C_STORE_code;
    private Date  BEGIN_DATE;
    private Date END_DATE;
    private Integer ISACTIVE;
    private Date MODIFIEDDATE;
    private Date Readtime;
    private Date Uptime;
    private Integer ediflag;
    private String RESULTS;
}
