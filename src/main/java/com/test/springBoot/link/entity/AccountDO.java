package com.test.springBoot.link.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/9/27
 * @Version: 1.0
 */
@Data
@TableName("privilage_account")
public class AccountDO {
    private Long id;
    private String login;
    private String name;
    private String password;
}
